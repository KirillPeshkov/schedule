package com.example.schedule.api;

import com.example.schedule.model.api.EmployeeResponse;
import com.example.schedule.model.api.MainResponse;
import com.example.schedule.model.api.ScheduleResponse;
import com.example.schedule.model.api.ScheduleTemplateResponse;
import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.entity.ScheduleTemplateEntity;
import com.example.schedule.model.enums.ResponseEnum;
import com.example.schedule.repository.ScheduleTemplateRepository;
import com.example.schedule.service.ScheduleTemplateService;
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
public class ScheduleTemplateController {

    private final ScheduleTemplateService scheduleTemplateService;
    private ScheduleTemplateEntity scheduleTemplateEntity;
    private ScheduleTemplateResponse scheduleTemplateResponse;


    @GetMapping("/getScheduleTemplate")
    public ResponseEntity<ScheduleTemplateEntity> getScheduleTemplate (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getScheduleTemplate");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryParam.get("id") != null) {
            return ResponseEntity.ok(scheduleTemplateService.getScheduleTemplate(queryParam.get("id").toString()));
        } else {
            return null;
        }
    }

    @GetMapping("/getScheduleTemplateAll")
    public ResponseEntity<List<ScheduleTemplateEntity>> getScheduleTemplateAll (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getScheduleTemplateAll");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        return ResponseEntity.ok(scheduleTemplateService.getScheduleTemplateAll());
    }

    @PostMapping("/postScheduleTemplate")
    public ScheduleTemplateResponse postScheduleTemplate (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/postScheduleTemplate");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryBody.get("name") != null) {
            scheduleTemplateEntity = scheduleTemplateService.postScheduleTemplate(
                    queryBody.get("name").toString()
            );
            scheduleTemplateResponse = new ScheduleTemplateResponse(
                    new MainResponse(
                            ResponseEnum.SUCCESS,
                            null
                    ),
                    scheduleTemplateEntity
            );
        } else {
            scheduleTemplateResponse = new ScheduleTemplateResponse(
                    new MainResponse(
                            ResponseEnum.ERROR,
                            "Переданы не все параметры"
                    ),
                    null
            );
        }

        return scheduleTemplateResponse;
    }

}
