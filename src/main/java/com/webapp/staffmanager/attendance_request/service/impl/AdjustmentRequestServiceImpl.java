package com.webapp.staffmanager.attendance_request.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.attendance_request.entity.AdjustmentRequest;
import com.webapp.staffmanager.attendance_request.entity.AttendanceRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.AdjustmentAddRequestDto;
import com.webapp.staffmanager.attendance_request.repository.AdjustmentRequestRepository;
import com.webapp.staffmanager.attendance_request.repository.AttendanceRequestRepository;
import com.webapp.staffmanager.attendance_request.service.AdjustmentRequestService;
import com.webapp.staffmanager.constant.RequestType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdjustmentRequestServiceImpl implements AdjustmentRequestService{
    private final AdjustmentRequestRepository adjustmentRepository;
    private final AttendanceRequestRepository requestRepository;

    @Override
    public List<AttendanceRequest> getList() {
        // return requestRepository.findAllByRequestType(RequestType.ADJUSTMENT);
        return null;
    }

    @Override
    public void add(AdjustmentAddRequestDto dto) {
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void edit(int id, AdjustmentAddRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }

    @Override
    public List<AdjustmentRequest> searchOnStaffId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchOnStaffId'");
    }
}
