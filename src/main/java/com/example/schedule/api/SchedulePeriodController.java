package com.example.schedule.api;

import com.example.schedule.model.api.MainResponse;
import com.example.schedule.model.api.SchedulePeriodResponse;
import com.example.schedule.model.entity.*;
import com.example.schedule.model.enums.ResponseEnum;
import com.example.schedule.model.enums.SlotTypeEnum;
import com.example.schedule.repository.SchedulePeriodRepository;
import com.example.schedule.service.*;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")

@Component
public class SchedulePeriodController {

    private final SchedulePeriodService schedulePeriodService;
    private SchedulePeriodEntity schedulePeriodEntity;
    private SchedulePeriodResponse schedulePeriodResponse;

    private final SlotService slotService;
    private SlotEntity slotEntity;

    private final ScheduleService scheduleService;
    private ScheduleEntity scheduleEntity;

    private final EmployeeService employeeService;
    private EmployeeEntity administrator;
    private EmployeeEntity executor;

    private final SchedulePeriodRepository schedulePeriodRepository;
    private final EntityManagerFactory entityManagerFactory;

    @GetMapping("/getSchedulePeriod")
    public ResponseEntity<SchedulePeriodEntity> getSchedulePeriod (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getSchedulePeriod");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryParam.get("id") != null) {
            return ResponseEntity.ok(schedulePeriodService.getSchedulePeriod(queryParam.get("id").toString()));
        } else {
            return null;
        }
    }

    @GetMapping("/getSchedulePeriodAll")
    public ResponseEntity<List<SchedulePeriodEntity>> getSchedulePeriodAll (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getSchedulePeriodAll");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        return ResponseEntity.ok(schedulePeriodService.getSchedulePeriodAll());
    }

    @PostMapping("/postSchedulePeriod")
    public SchedulePeriodResponse postSchedulePeriod (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/postSchedulePeriod");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if ((queryBody.get("slotType") != null) && (queryBody.get("slotId") != null) && (queryBody.get("scheduleId") != null) && (queryBody.get("administratorId") != null)) {

            slotEntity = slotService.getSlot(queryBody.get("slotId").toString());
            scheduleEntity = scheduleService.getSchedule(queryBody.get("scheduleId").toString());
            administrator = employeeService.getEmployee(queryBody.get("administratorId").toString());

            if (slotEntity == null){
                schedulePeriodResponse = new SchedulePeriodResponse(
                        new MainResponse(
                                ResponseEnum.ERROR,
                                "Не найден слот с id=" + queryBody.get("slotId").toString()
                        ),
                        null
                );
            } else if (scheduleEntity == null) {
                schedulePeriodResponse = new SchedulePeriodResponse(
                        new MainResponse(
                                ResponseEnum.ERROR,
                                "Не найдено расписание с id=" + queryBody.get("scheduleId").toString()
                        ),
                        null
                );
            } else if (administrator == null) {
                schedulePeriodResponse = new SchedulePeriodResponse(
                        new MainResponse(
                                ResponseEnum.ERROR,
                                "Не найден сотрудник с id=" + queryBody.get("administratorId").toString()
                        ),
                        null
                );
            } else {
                if (queryBody.get("executorId") != null) {
                    executor = employeeService.getEmployee(queryBody.get("executorId").toString());
                }
                schedulePeriodEntity = schedulePeriodService.postSchedulePeriod(
                        queryBody.get("slotType").toString(),
                        slotEntity,
                        scheduleEntity,
                        administrator,
                        executor
                );
                schedulePeriodResponse = new SchedulePeriodResponse(
                        new MainResponse(
                                ResponseEnum.SUCCESS,
                                null
                        ),
                        schedulePeriodEntity
                );
            }
        } else {
            schedulePeriodResponse = new SchedulePeriodResponse(
                    new MainResponse(
                            ResponseEnum.ERROR,
                            "Переданы не все параметры"
                    ),
                    null
            );
        }
        return schedulePeriodResponse;
    }

    @PutMapping("/getSchedulePeriodSmart")
    public ResponseEntity<List<SchedulePeriodEntity>> getSchedulePeriodSmart (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getSchedulePeriodSmart");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        String filterId = null;
        String filterSlotId = null;
        String filterScheduleId = null;
        SlotTypeEnum filterSlotType = null;
        String filterAdministratorId = null;
        String filterExecutorId = null;

        String sortDirection = "ASC";;
        Comparator comparator = null;

        Integer page = null;
        Integer size = null;

        if (queryBody.get("filter.id") != null) {
            filterId = queryBody.get("filter.id").toString();
        }
        if (queryBody.get("filter.slotId") != null) {
            filterSlotId = queryBody.get("filter.slotId").toString();
        }
        if (queryBody.get("filter.slotType") != null) {
            filterSlotType = SlotTypeEnum.valueOf(queryBody.get("filter.slotType").toString());
        }
        if (queryBody.get("filter.scheduleId") != null) {
            filterScheduleId = queryBody.get("filter.scheduleId").toString();
        }
        if (queryBody.get("filter.administratorId") != null) {
            filterAdministratorId = queryBody.get("filter.administratorId").toString();
        }
        if (queryBody.get("filter.executorId") != null) {
            filterExecutorId = queryBody.get("filter.executorId").toString();
        }

        if (queryBody.get("sort.direction") != null) {
            System.out.println("queryBody.get(sort.direction).toString()=" + queryBody.get("sort.direction").toString());
            if (queryBody.get("sort.direction").toString().equals("DESC")) {
                sortDirection = "DESC";
            }
        }

        if (queryBody.get("sort.field") != null) {
            switch (queryBody.get("sort.field").toString()) {
                case  ("id"):
                    if (sortDirection.equals("ASC")) {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o2.getId().compareTo(o1.getId());
                            }
                        };
                    } else {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o1.getId().compareTo(o2.getId());
                            }
                        };
                    }
                    break;
                case ("slotId"):
                    if (sortDirection.equals("ASC")) {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o2.getSlot().getId().compareTo(o1.getSlot().getId());
                            }
                        };
                    } else {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o1.getSlot().getId().compareTo(o2.getSlot().getId());
                            }
                        };
                    }
                    break;
                case ("scheduleId"):
                    if (sortDirection.equals("ASC")) {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o2.getSchedule().getId().compareTo(o1.getSchedule().getId());
                            }
                        };
                    } else {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o1.getSchedule().getId().compareTo(o2.getSchedule().getId());
                            }
                        };
                    }
                    break;
                case ("slotType"):
                    if (sortDirection.equals("ASC")) {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o2.getSlotType().compareTo(o1.getSlotType());
                            }
                        };
                    } else {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o1.getSlotType().compareTo(o2.getSlotType());
                            }
                        };
                    }
                    break;
                case ("administratorId"):
                    if (sortDirection.equals("ASC")) {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o2.getAdministrator().getId().compareTo(o1.getAdministrator().getId());
                            }
                        };
                    } else {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o1.getAdministrator().getId().compareTo(o2.getAdministrator().getId());
                            }
                        };
                    }
                    break;
                case ("executorId"):
                    if (sortDirection.equals("ASC")) {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o2.getExecutor().getId().compareTo(o1.getExecutor().getId());
                            }
                        };
                    } else {
                        comparator = new Comparator<SchedulePeriodEntity>() {
                            public int compare(SchedulePeriodEntity o1, SchedulePeriodEntity o2) {
                                return o1.getExecutor().getId().compareTo(o2.getExecutor().getId());
                            }
                        };
                    }
                    break;
            }

        }

        if (queryBody.get("size") != null) {
            size = Integer.valueOf(queryBody.get("size").toString());
        } else {
            size = 100;
        }
        if (queryBody.get("page") != null) {
            page = Integer.valueOf(queryBody.get("page").toString());
            if (page > 1) {
                page = (page - 1) * size;
            } else {
                page = 0;
            }
        } else {
            page = 0;
        }

        List<SchedulePeriodEntity> schedulePeriodEntityList = schedulePeriodRepository.getAllNew(
                filterId,
                filterSlotId,
                filterScheduleId,
                filterSlotType,
                filterAdministratorId,
                filterExecutorId,
                size,
                page
       );

       if (comparator != null) {
            Collections.sort(schedulePeriodEntityList,comparator);
       };

       return ResponseEntity.ok(schedulePeriodEntityList);
    }

}
