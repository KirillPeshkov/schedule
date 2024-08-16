package com.example.schedule.model.api;

import com.example.schedule.model.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainResponse {
    private ResponseEnum status;
    private String errorMessage;
}
