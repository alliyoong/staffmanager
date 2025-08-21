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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
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

    // @GetMapping("/detail/{id}")
    // public HttpResponse getDetail(@PathVariable("id") int id) {
    //     var result = service.getDetail(id);
    //     return HttpResponse.ok(result);
    // }

    @GetMapping("/check-in/{staffId}")
    public HttpResponse checkIn(@PathVariable int staffId) {
        service.checkIn(staffId);
        return HttpResponse.created();
    }

    @GetMapping("/check-out/{staffId}")
    public HttpResponse checkOut(@PathVariable int staffId) {
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
