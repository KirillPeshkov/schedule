package com.example.schedule.model.api;

import com.example.schedule.model.entity.SchedulePeriodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SchedulePeriodResponse {
    private MainResponse result;
    private SchedulePeriodEntity schedule;
}