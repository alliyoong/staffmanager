package com.webapp.staffmanager.staff.entity;

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
    @JsonProperty("id")
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
}
