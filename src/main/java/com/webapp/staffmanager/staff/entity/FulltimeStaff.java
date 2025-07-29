package com.webapp.staffmanager.staff.entity;

import java.math.BigDecimal;

import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffType;

public class FulltimeStaff extends Staff{
    private BigDecimal salary;

    public FulltimeStaff() {}

    public FulltimeStaff(int staffId, StaffType type, String name, int age, BigDecimal salary, Gender gender, int departmentId) {
        this.id = staffId;
        this.type = type;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender=" + gender +
                ", department=" + departmentId +
                '}';
    }

    @Override
    public void doWork() {
        System.out.println(String.format("%s is working as a FULL time employee",name));
    }
}
