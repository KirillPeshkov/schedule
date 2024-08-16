package com.example.schedule.service;

import com.example.schedule.model.entity.*;
import com.example.schedule.model.enums.PositionEnum;
import com.example.schedule.model.enums.SlotTypeEnum;
import com.example.schedule.model.enums.StatusEnum;
import com.example.schedule.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {

    private final EmployeeRepository employeeRepository;
    private final SlotRepository slotRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleTemplateRepository scheduleTemplateRepository;
    private final SchedulePeriodRepository schedulePeriodRepository;


    public void createData() {

        EmployeeEntity employeeAlex = new EmployeeEntity(
                UUID.randomUUID().toString().replace("-", "").toLowerCase(),
                "Alex",
                StatusEnum.WORKING,
                PositionEnum.MANAGER,
                List.of(),
                List.of()
        );
        employeeRepository.save(employeeAlex);

        EmployeeEntity employeeVova = new EmployeeEntity(
                UUID.randomUUID().toString().replace("-", "").toLowerCase(),
                "Vova",
                StatusEnum.WORKING,
                PositionEnum.EMPLOYEE,
                List.of(),
                List.of()
        );
        employeeRepository.save(employeeVova);

        ScheduleEntity scheduleWeekdays = new ScheduleEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                "Weekdays",
                LocalDate.now(),
                List.of()
        );
        scheduleRepository.save(scheduleWeekdays);

        ScheduleEntity scheduleWeekends = new ScheduleEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                "Weekends",
                LocalDate.now(),
                List.of()
        );
        scheduleRepository.save(scheduleWeekends);

        ScheduleTemplateEntity scheduleTemplateHourly = new ScheduleTemplateEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                "Hourly",
                LocalDate.now(),
                List.of()
        );
        scheduleTemplateRepository.save(scheduleTemplateHourly);

        ScheduleTemplateEntity scheduleTemplateHalfHourly = new ScheduleTemplateEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                "Half-hourly",
                LocalDate.now(),
                List.of()
        );
        scheduleTemplateRepository.save(scheduleTemplateHalfHourly);

        SlotEntity slotEntityH10 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("10:00:00"),
                LocalTime.parse("11:00:00"),
                scheduleTemplateHourly,
                List.of()
        );
        slotRepository.save(slotEntityH10);

        SlotEntity slotEntityH11 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("11:00:00"),
                LocalTime.parse("12:00:00"),
                scheduleTemplateHourly,
                List.of()
        );
        slotRepository.save(slotEntityH11);

        SlotEntity slotEntityH12 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("12:00:00"),
                LocalTime.parse("13:00:00"),
                scheduleTemplateHourly,
                List.of()
        );
        slotRepository.save(slotEntityH12);

        SlotEntity slotEntityH14 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("14:00:00"),
                LocalTime.parse("15:00:00"),
                scheduleTemplateHourly,
                List.of()
        );
        slotRepository.save(slotEntityH14);

        SlotEntity slotEntityH15 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("15:00:00"),
                LocalTime.parse("16:00:00"),
                scheduleTemplateHourly,
                List.of()
        );
        slotRepository.save(slotEntityH15);

        SlotEntity slotEntityHH1000 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("10:00:00"),
                LocalTime.parse("10:30:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1000);

        SlotEntity slotEntityHH1030 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("10:30:00"),
                LocalTime.parse("11:00:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1030);

        SlotEntity slotEntityHH1100 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("11:00:00"),
                LocalTime.parse("11:30:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1100);

        SlotEntity slotEntityHH1130 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("11:30:00"),
                LocalTime.parse("12:00:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1130);

        SlotEntity slotEntityHH1200 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("12:00:00"),
                LocalTime.parse("12:30:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1200);

        SlotEntity slotEntityHH1230 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("12:30:00"),
                LocalTime.parse("13:00:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1230);

        SlotEntity slotEntityHH1400 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("14:00:00"),
                LocalTime.parse("14:30:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1400);

        SlotEntity slotEntityHH1430 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("14:30:00"),
                LocalTime.parse("15:00:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1430);

        SlotEntity slotEntityHH1500 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("15:00:00"),
                LocalTime.parse("15:30:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1500);

        SlotEntity slotEntityHH1530 = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                LocalTime.parse("15:30:00"),
                LocalTime.parse("16:00:00"),
                scheduleTemplateHalfHourly,
                List.of()
        );
        slotRepository.save(slotEntityHH1530);

        SchedulePeriodEntity schedulePeriodEntity = new SchedulePeriodEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                SlotTypeEnum.LOCAL,
                slotEntityH10,
                scheduleWeekdays,
                employeeAlex,
                employeeVova
        );
        schedulePeriodRepository.save(schedulePeriodEntity);

        schedulePeriodEntity = new SchedulePeriodEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                SlotTypeEnum.FROM_HOME,
                slotEntityH11,
                scheduleWeekdays,
                employeeAlex,
                null
        );
        schedulePeriodRepository.save(schedulePeriodEntity);
    }
}
