package com.example.schedule.service;

import com.example.schedule.model.entity.*;
import com.example.schedule.model.enums.SlotTypeEnum;
import com.example.schedule.model.enums.StatusEnum;
import com.example.schedule.repository.SchedulePeriodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulePeriodService {

    private final SchedulePeriodRepository schedulePeriodRepository;

    public SchedulePeriodEntity getSchedulePeriod(String inSchedulePeriodId) {
        return schedulePeriodRepository.findFirstById(inSchedulePeriodId);
    }

    public List<SchedulePeriodEntity> getSchedulePeriodAll() {
        return schedulePeriodRepository.findAll();
    }

    public SchedulePeriodEntity postSchedulePeriod (
            String inSlotType,
            SlotEntity inSlot,
            ScheduleEntity inSchedule,
            EmployeeEntity inAdministrator,
            EmployeeEntity inExecutor
    ) {
        SchedulePeriodEntity schedulePeriodEntity;
        schedulePeriodEntity = new SchedulePeriodEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                SlotTypeEnum.valueOf(inSlotType),
                inSlot,
                inSchedule,
                inAdministrator,
                inExecutor
        );
        schedulePeriodRepository.save(schedulePeriodEntity);
        return schedulePeriodEntity;
    }

}
