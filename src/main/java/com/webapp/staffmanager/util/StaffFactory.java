package com.webapp.staffmanager.util;

import com.webapp.staffmanager.staff.entity.InternStaff;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;

public class StaffFactory {
    public static Staff createStaff(StaffAddRequestDto staff){
        return switch(staff.type()) {
            case StaffType.INTERN -> new InternStaff()
        }
    }
}
