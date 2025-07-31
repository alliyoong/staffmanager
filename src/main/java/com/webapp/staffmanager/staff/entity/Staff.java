package com.webapp.staffmanager.staff.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class Staff {
    protected int id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("age")
    protected int age;

    @JsonProperty("gender")
    protected Gender gender;

    @JsonProperty("type")
    protected StaffType type;

    @JsonProperty("departmentId")
    protected int departmentId;

    public abstract void doWork();
    
    @Getter
    public static class InternStaffBuilder{
        private int id;
        private String name;
        private int age;
        private Gender gender;
        private StaffType type;
        private int departmentId;
        private int duration;
        private BigDecimal salary;

        public InternStaffBuilder(int id, String name, StaffType type, int departmentId, int duration){
            this.id = id;
            this.name = name;
            this.type = type;
            this.departmentId = departmentId;
            this.duration = duration;
            this.salary = BigDecimal.valueOf(0);
        }
        
        public InternStaffBuilder gender(Gender gender){
            this.gender = gender;
            return this;
        }

        public InternStaffBuilder age(int age){
            this.age = age;
            return this;
        }
        
        public InternStaff build(){
            return new InternStaff(this);
        }
    }
}
