package com.webapp.staffmanager.attendance_request.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.staffmanager.attendance_request.entity.dto.DayOffAddRequestDto;
import com.webapp.staffmanager.attendance_request.service.DayOffRequestService;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/day-off-request")
@RequiredArgsConstructor
public class DayOffRequestRestController {
    private final DayOffRequestService dayOffRequestService;
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResponse postMethodName(
        @RequestPart(name = "dayOffRequest") DayOffAddRequestDto data, 
        @RequestPart(name = "document", required = false) MultipartFile document) {
        dayOffRequestService.add(data, document);
        return HttpResponse.created();
    }
    
}
