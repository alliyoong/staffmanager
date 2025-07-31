package com.webapp.staffmanager.staff.restcontroller;

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
public class StaffRestController {
    private final StaffService service;

    // @GetMapping("/hello")
    // public HttpResponse hello(){
    //     return HttpResponse.ok(tranService.translate("WELCOME"));
    // }
    // public HttpResponse hello() {
    //     return HttpResponse.ok(service.translate(Locale.of("vn")));
    // }
    @GetMapping()
    public HttpResponse getList() {
        var data = service.getStaffList();
        return HttpResponse.ok(data);
    }
    @GetMapping("/{staffName}")
    public HttpResponse search(@PathVariable("staffName") String staffName) {
        var result = service.searchStaff(staffName);
        return HttpResponse.ok(result);
    }

    @PostMapping()
    public HttpResponse add(@RequestBody StaffAddRequestDto data) {
        service.addStaff(data);
        return HttpResponse.created();
    }

    @PutMapping(path = "/{id}")
    public HttpResponse edit(@PathVariable("id") int id, 
                            @RequestBody StaffAddRequestDto data) {
        service.editStaff(id, data);
        return HttpResponse.created();
    }

    @DeleteMapping ("/{id}")
    public HttpResponse deleteAccount(@PathVariable int id) {
        service.deleteStaff(id);
        return HttpResponse.noContent();
    }


}
