package com.example.schedule.service;

import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.entity.ScheduleTemplateEntity;
import com.example.schedule.model.enums.PositionEnum;
import com.example.schedule.model.enums.StatusEnum;
import com.example.schedule.repository.ScheduleTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleTemplateService {

    private final ScheduleTemplateRepository scheduleTemplateRepository;

    public ScheduleTemplateEntity getScheduleTemplate(String inScheduleTemplateId) {
        return scheduleTemplateRepository.findFirstById(inScheduleTemplateId);
    }

    public List<ScheduleTemplateEntity> getScheduleTemplateAll() {
        return scheduleTemplateRepository.findAll();
    }

    public ScheduleTemplateEntity postScheduleTemplate (String inName) {
        ScheduleTemplateEntity scheduleTemplateEntity = new ScheduleTemplateEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                inName,
                LocalDate.now(),
                List.of()
        );
        scheduleTemplateRepository.save(scheduleTemplateEntity);
        return scheduleTemplateEntity;
    }

}
