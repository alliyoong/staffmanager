package com.webapp.staffmanager.attendance.restcontroller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.attendance.entity.dto.AttendanceAddRequestDto;
import com.webapp.staffmanager.attendance.service.AttendanceService;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto.OnCreate;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto.OnUpdate;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
@Validated
@Slf4j
public class AttendanceRestController {
     private final AttendanceService service;

    // @GetMapping()
    // public HttpResponse getList() {
    //     var data = service.getList();
    //     return HttpResponse.ok(data);
    // }
    // @GetMapping("/search")
    // public HttpResponse search(@RequestParam(name="staff_id", required = false, defaultValue = "") int staffId) {
    //     var result = service.searchOnStaffId(staffId);
    //     return HttpResponse.ok(result);
    // }

    @GetMapping("/{id}")
    public HttpResponse getAttendance(@PathVariable("id") int id) {
        var result = service.getAttendance(id);
        return HttpResponse.ok(result);
    }

    @GetMapping("/check-in/{staffId}")
    public HttpResponse checkIn(@PathVariable("staffId") int staffId) {
        service.checkIn(staffId);
        return HttpResponse.created();
    }

    @GetMapping("/check-out/{staffId}")
    public HttpResponse checkOut(@PathVariable("staffId") int staffId) {
        service.checkOut(staffId);
        return HttpResponse.created();
    }

    // @PutMapping(path = "/{id}")
    // public HttpResponse edit(@PathVariable("id") int id, 
    //                         @Validated @RequestBody AttendanceAddRequestDto data) {
    //     service.editAttendance(id, data);
    //     return HttpResponse.created();
    // }

    // @DeleteMapping (path = "/{id}")
    // public HttpResponse delete(@PathVariable int id) {
    //     service.deleteAttendance(id);
    //     return HttpResponse.noContent();
    // }
   
}
