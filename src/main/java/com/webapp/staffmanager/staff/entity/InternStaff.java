package com.webapp.staffmanager.staff.entity;

import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffType;

public class InternStaff extends Staff{
    private int internDuration;

    public InternStaff() {}

    public InternStaff(int staffId, StaffType type, String name, int age, int internDuration , Gender gender, int departmentId) {
        this.id = staffId;
        this.type = type;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.departmentId = departmentId;
        this.internDuration = internDuration;
    }

    public InternStaff(InternStaffBuilder builder) {
        this.id = builder.getId();
        this.type = builder.getType();
        this.age = builder.getAge();
        this.name = builder.getName();
        this.gender = builder.getGender();
        this.departmentId = builder.getDepartmentId();
        this.internDuration = builder.getDuration();
    }

    public int getInternDuration() {
        return internDuration;
    }

    public void setInternDuration(int internDuration) {
        this.internDuration = internDuration;
    }

    @Override
    public void doWork() {
        System.out.println(String.format("%s is working as a PART time employee",name));
    }
}
