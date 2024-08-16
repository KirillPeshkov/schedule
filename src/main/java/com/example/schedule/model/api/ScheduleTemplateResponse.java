package com.example.schedule.model.api;

import com.example.schedule.model.entity.ScheduleTemplateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleTemplateResponse {
    private MainResponse result;
    private ScheduleTemplateEntity schedule;
}