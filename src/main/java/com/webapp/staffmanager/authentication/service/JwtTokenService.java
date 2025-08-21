package com.webapp.staffmanager.authentication.service;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static com.webapp.staffmanager.constant.SecurityConstant.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.webapp.staffmanager.authentication.entity.UserPrincipal;
import com.webapp.staffmanager.authentication.repository.AccountRepository;
import com.webapp.staffmanager.exception.GeneralException;
import static com.webapp.staffmanager.constant.AppResponseStatus.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenService implements InitializingBean {
    private final AccountRepository accountRepository;

    @Value("${security.jwt.secret}") // Base64-encoded secret (≥ 256 bits for HS256)
    private String base64Secret;
    @Value("${security.jwt.access-token-lifetime-seconds}")
    private Long tokenLifetime;

    private SecretKey signingKey;
    private JwtParser parser;

    // Build expensive objects only once
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.parser = Jwts.parser()
                .requireIssuer(ISSUER)
                .verifyWith(signingKey)
                // .setAllowedClockSkewSeconds(clockSkewSeconds)
                .build();
    }

    // =====================
    // Creation
    // =====================
    public String generateAccessToken(UserPrincipal user) {
        return Jwts.builder()
                .issuer(ISSUER)
                .subject(user.getUsername())
                // .issuedAt(Date.from(System.currentTimeMillis()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenLifetime))
                .audience().add(AUDIENCE)
                .and()
                .claims(Map.of(AUTHORITIES, getClaimsFromUser(user)))
                .signWith(signingKey)
                .compact();
    }

    // =====================
    // Parsing / Claims
    // =====================

    /** Parse claims (throws if invalid). */
    public Claims parseClaims(String token) throws JwtException {
        return parser.parseSignedClaims(token).getPayload();
    }

    private String[] getClaimsFromToken(String token) {
        return (String[]) parseClaims(token).get(AUTHORITIES);
    }

    private String[] getClaimsFromUser(UserPrincipal user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        return Arrays.stream(getClaimsFromToken(token)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /** Safe parse: returns Optional.empty() if invalid. */
    public Optional<Claims> tryParseClaims(String token) {
        try {
            return Optional.of(parseClaims(token));
        } catch (JwtException ex) {
            return Optional.empty();
        }
    }

    /** Extract any claim with a resolver function. */
    public <T> T getClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(parseClaims(token));
    }

    public String getSubject(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /** Quick validity check: signature, issuer, expiration, skew. */
    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    // =====================
    // Spring Security glue
    // =====================

    public Authentication getAuthentication(String token) {
        String username = getSubject(token);
        var account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(APP_404_ACCOUNT));
        return new UsernamePasswordAuthenticationToken(new UserPrincipal(account), token, getAuthorities(token));
    }


    /** Extract bearer token from Authorization header. */
    // public Optional<String> resolveToken(HttpServletRequest request) {
    //     String header = request.getHeader("Authorization");
    //     if (header != null && header.startsWith("Bearer ")) {
    //         String token = header.substring(7).trim();
    //         return token.isEmpty() ? Optional.empty() : Optional.of(token);
    //     }
    //     return Optional.empty();
    // }

    /** Validate a token belongs to username and isn’t expired. */
    public boolean isTokenValidForUser(String token, String expectedUsername) {
        return isValid(token) && expectedUsername.equalsIgnoreCase(getSubject(token));
    }

}
