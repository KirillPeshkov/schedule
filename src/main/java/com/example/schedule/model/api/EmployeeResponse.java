package com.example.schedule.model.api;

import com.example.schedule.model.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private MainResponse result;
    private EmployeeEntity employee;
}
