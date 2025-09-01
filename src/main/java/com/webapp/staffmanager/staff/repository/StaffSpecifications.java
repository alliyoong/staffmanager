package com.webapp.staffmanager.staff.repository;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.JoinType;

import com.webapp.staffmanager.staff.entity.Staff;

public class StaffSpecifications {
 public static Specification<Staff> hasAccount() {
        return (root, query, criteriaBuilder) ->{
            root.join("account", JoinType.LEFT);
            return criteriaBuilder.conjunction();
        };
    }   
 public static Specification<Staff> hasAccount(String term) {
        return (root, query, criteriaBuilder) ->{
            root.join("account", JoinType.LEFT);
            return criteriaBuilder.like(root.get("name"), "%" + term + "%");
        };
    }   
}
