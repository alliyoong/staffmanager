package com.webapp.staffmanager.staff.entity;

import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.util.StaffType;

public class StaffFactory {
    public static Staff createStaff(StaffAddRequestDto staff, int id){
        // return switch (staff.type()) {
        //     case StaffType.FULLTIME -> {
        //         yield new FulltimeStaff(id, 
        //                                 staff.type(), 
        //                                 staff.name(), 
        //                                 staff.age(), 
        //                                 staff.salary(), 
        //                                 staff.gender(), 
        //                                 staff.departmentId());
        //     }
        //     case StaffType.INTERN -> {
        //         yield new InternStaff(id,
        //                                 staff.type(), 
        //                                 staff.name(), 
        //                                 staff.age(), 
        //                                 staff.duration(), 
        //                                 staff.gender(), 
        //                                 staff.departmentId());
        //     }
        // };
        return null;
    }
}
