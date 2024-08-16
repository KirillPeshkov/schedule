package com.example.schedule.api;

import com.example.schedule.model.api.EmployeeResponse;
import com.example.schedule.model.api.MainResponse;
import com.example.schedule.model.api.ScheduleResponse;
import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.entity.ScheduleEntity;
import com.example.schedule.model.enums.ResponseEnum;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")

@Component
public class ScheduleController {

    private final ScheduleService scheduleService;
    private ScheduleEntity scheduleEntity;
    private ScheduleResponse scheduleResponse;

    @GetMapping("/getScheduleService")
    public ResponseEntity<ScheduleEntity> getScheduleService (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getScheduleService");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryParam.get("id") != null) {
            return ResponseEntity.ok(scheduleService.getSchedule(queryParam.get("id").toString()));
        } else {
            return null;
        }
    }

    @GetMapping("/getScheduleAll")
    public ResponseEntity<List<ScheduleEntity>> getScheduleAll (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getScheduleAll");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        return ResponseEntity.ok(scheduleService.getScheduleAll());
    }

    @PostMapping("/postSchedule")
    public ScheduleResponse postSchedule (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/postSchedule");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryBody.get("name") != null) {
            scheduleEntity = scheduleService.postSchedule(
                    queryBody.get("name").toString()
            );
            scheduleResponse = new ScheduleResponse(
                    new MainResponse(
                            ResponseEnum.SUCCESS,
                            null
                    ),
                    scheduleEntity
            );
        } else {
            scheduleResponse = new ScheduleResponse(
                    new MainResponse(
                            ResponseEnum.ERROR,
                            "Переданы не все параметры"
                    ),
                    null
            );
        }
        return scheduleResponse;
    }

}
