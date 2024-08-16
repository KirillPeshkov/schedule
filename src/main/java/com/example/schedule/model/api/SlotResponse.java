package com.example.schedule.model.api;

import com.example.schedule.model.entity.SlotEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlotResponse {
    private MainResponse result;
    private SlotEntity schedule;
}