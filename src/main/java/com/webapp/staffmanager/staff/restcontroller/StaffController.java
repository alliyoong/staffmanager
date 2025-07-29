package com.webapp.staffmanager.staff.restcontroller;

import java.util.Map;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.service.StaffService;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService service;

    @GetMapping()
    public ResponseEntity<HttpResponse> getList() {
        var data = service.getStaffList();
        return ResponseEntity.ok(HttpResponse.builder()
        .httpStatus(OK)
        .httpStatusCode(OK.value())
        .data(Map.of("staffList",data))
        .build());
    }
    @GetMapping("/{staffName}")
    public ResponseEntity<HttpResponse> search(@PathVariable("staffName") String staffName) {
        var result = service.searchStaff(staffName);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatus(OK)
                        .httpStatusCode(OK.value())
                        .data(Map.of("staff", result))
                        .build()
        );
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> add(@RequestBody StaffAddRequestDto data) {
        service.addStaff(data);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatusCode(CREATED.value())
                        .httpStatus(CREATED)
                        .message("Staff has been added successfully")
                        .build()
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<HttpResponse> edit(@PathVariable("id") int id, 
                                            @RequestBody StaffAddRequestDto data) {
        service.editStaff(id, data);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatusCode(CREATED.value())
                        .httpStatus(CREATED)
                        .message("Staff has been updated successfully")
                        .build()
        );
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<HttpResponse> deleteAccount(@PathVariable int id) {
        service.deleteStaff(id);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatusCode(NO_CONTENT.value())
                        .httpStatus(NO_CONTENT)
                        .message("Staff successfully deleted.")
                        .build()
        );
    }


}
