package com.example.schedule.api;

import com.example.schedule.model.api.EmployeeResponse;
import com.example.schedule.model.api.MainResponse;
import com.example.schedule.model.api.SlotResponse;
import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.entity.ScheduleTemplateEntity;
import com.example.schedule.model.entity.SlotEntity;
import com.example.schedule.model.enums.ResponseEnum;
import com.example.schedule.service.ScheduleTemplateService;
import com.example.schedule.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")

@Component
public class SlotController {

    private final SlotService slotService;
    private SlotEntity slotEntity;
    private SlotResponse slotResponse;

    private final ScheduleTemplateService scheduleTemplateService;
    private ScheduleTemplateEntity scheduleTemplateEntity;

    @GetMapping("/getSlot")
    public ResponseEntity<SlotEntity> getSlot (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getSlot");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryParam.get("id") != null) {
            return ResponseEntity.ok(slotService.getSlot(queryParam.get("id").toString()));
        } else {
            return null;
        }
    }

    @GetMapping("/getSlotAll")
    public ResponseEntity<List<SlotEntity>> getSlotAll (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getSlotAll");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        return ResponseEntity.ok(slotService.getSlotAll());
    }

    @PostMapping("/postSlot")
    public SlotResponse postSlot (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/postSlot");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if ((queryBody.get("beginTime") != null) && (queryBody.get("endTime") != null) && (queryBody.get("scheduleTemplateId") != null)) {
            scheduleTemplateEntity = scheduleTemplateService.getScheduleTemplate(queryBody.get("scheduleTemplateId").toString());
            if (scheduleTemplateEntity != null){
                slotEntity = slotService.postSlot(
                        LocalTime.parse(queryBody.get("beginTime").toString()),
                        LocalTime.parse(queryBody.get("endTime").toString()),
                        scheduleTemplateEntity
                );
                slotResponse = new SlotResponse(
                        new MainResponse(
                                ResponseEnum.SUCCESS,
                                null
                        ),
                        slotEntity
                );
            } else {
                slotResponse = new SlotResponse(
                        new MainResponse(
                                ResponseEnum.ERROR,
                                "Не найден шаблон расписания с id=" + queryBody.get("scheduleTemplateId").toString()
                        ),
                        null
                );
            }
        } else {
            slotResponse = new SlotResponse(
                    new MainResponse(
                            ResponseEnum.ERROR,
                            "Переданы не все параметры"
                    ),
                    null
            );
        }
        return slotResponse;
    }

}
