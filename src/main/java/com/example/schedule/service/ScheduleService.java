package com.example.schedule.service;

import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.entity.ScheduleEntity;
import com.example.schedule.model.enums.PositionEnum;
import com.example.schedule.model.enums.StatusEnum;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleEntity getSchedule(String inScheduleId) {
        return scheduleRepository.findFirstById(inScheduleId);
    }

    public List<ScheduleEntity> getScheduleAll() {
        return scheduleRepository.findAll();
    }

    public ScheduleEntity postSchedule (String inName) {
        ScheduleEntity scheduleEntity = new ScheduleEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                inName,
                LocalDate.now(),
                List.of()
        );

        scheduleRepository.save(scheduleEntity);
        return scheduleEntity;
    }

}
