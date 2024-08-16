package com.example.schedule.model.api;

import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.entity.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleResponse {
    private MainResponse result;
    private ScheduleEntity schedule;
}