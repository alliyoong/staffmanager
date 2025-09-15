package com.webapp.staffmanager.attendance_request.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webapp.staffmanager.attendance_request.entity.DayOffRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.DayOffAddRequestDto;
import com.webapp.staffmanager.attendance_request.entity.mapper.DayOffRequestMapper;
import com.webapp.staffmanager.attendance_request.repository.AttendanceRequestRepository;
import com.webapp.staffmanager.attendance_request.repository.DayOffRequestRepository;
import com.webapp.staffmanager.attendance_request.service.DayOffRequestService;
import com.webapp.staffmanager.constant.AttendanceRequestType;

import jakarta.transaction.Transactional;

import static com.webapp.staffmanager.constant.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DayOffRequestServiceImpl implements DayOffRequestService{
    private final DayOffRequestRepository dayOffRepo;
    private final AttendanceRequestRepository requestRepo;
    private final DayOffRequestMapper mapper;

    @Transactional
    @Override
    public void add(DayOffAddRequestDto dto, MultipartFile document) {
        log.info("data receiced: {}", dto);
        var toAddDayOff = mapper.fromAddDto(dto);
        var toAddRequest = mapper.fromDtoToAttendanceRequest(dto);

        var attendanceRequest = requestRepo.save(toAddRequest);

        // toAddDayOff.setRequestId(attendanceRequest.getRequestId());
        toAddDayOff.setAttendanceRequest(attendanceRequest);
        var dayOff = dayOffRepo.save(toAddDayOff);
        log.info("Day off added : {}", dayOff);
        log.info("Request added : {}", attendanceRequest);
        saveWithDocument(dayOff, document);
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(int id, DayOffAddRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<DayOffRequest> searchOnStaffId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchOnStaffId'");
    }

    private void saveWithDocument(DayOffRequest request, MultipartFile document) {
        if (document != null) {
            try {
                Path userFolder = Paths.get(USER_FOLDER + request.getRequestId()).toAbsolutePath().normalize();
                if (!Files.exists(userFolder)) {
                    Files.createDirectories(userFolder);
                    log.info(DIRECTORY_CREATED + userFolder);
                }
                Files.deleteIfExists(Paths.get(userFolder + FILE_PREFIX + String.valueOf(request.getRequestId()) + DOT + JPG_EXTENSION));
                Files.copy(document.getInputStream(), userFolder.resolve(FILE_PREFIX + String.valueOf(request.getRequestId()) + DOT + JPG_EXTENSION), REPLACE_EXISTING);
                String imagePath = generateDocumentUrl(request.getRequestId());
                request.setDocumentUrl(imagePath);
                log.info(FILE_SAVED_IN_FILE_SYSTEM + document.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // account.setImageUrl(setProfileImageUrl(null));
            // messAccountRepository.save(account);
        }
    }

    private String generateDocumentUrl(int id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DAY_OFF_REQUEST_URL + String.valueOf(id) + FORWARD_SLASH + FILE_PREFIX + String.valueOf(id) + DOT + JPG_EXTENSION).toUriString();
    }
}
