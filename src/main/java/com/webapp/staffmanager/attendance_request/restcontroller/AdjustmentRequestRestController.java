package com.webapp.staffmanager.attendance_request.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.attendance_request.service.AdjustmentRequestService;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance-adjustment-request")
public class AdjustmentRequestRestController {
    private final AdjustmentRequestService service;

    @GetMapping()
    public HttpResponse getList() {
        var data = service.getList();
        return HttpResponse.ok(data);
    }
    @GetMapping("/search")
    public HttpResponse search(@RequestParam(name="staff_id", required = false, defaultValue = "") int staffId) {
        var result = service.searchOnStaffId(staffId);
        return HttpResponse.ok(result);
    }

    // @GetMapping("/detail/{id}")
    // public HttpResponse getDetail(@PathVariable("id") int id) {
    //     var result = service.getDetail(id);
    //     return HttpResponse.ok(result);
    // }

    // @PostMapping()
    // public HttpResponse add(@Validated @RequestBody AttendanceAddRequestDto data) {
    //     service.addAttendance(data);
    //     return HttpResponse.created();
    // }

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
