package com.example.schedule.service;

import com.example.schedule.model.entity.ScheduleTemplateEntity;
import com.example.schedule.model.entity.SlotEntity;
import com.example.schedule.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;

    public SlotEntity getSlot(String inSlotId) {
        return slotRepository.findFirstById(inSlotId);
    }

    public List<SlotEntity> getSlotAll() {
        return slotRepository.findAll();
    }

    public SlotEntity postSlot (LocalTime inBeginTime, LocalTime inEndTime, ScheduleTemplateEntity inScheduleTemplate) {
        SlotEntity slotEntity = new SlotEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                inBeginTime,
                inEndTime,
                inScheduleTemplate,
                List.of()
        );
        slotRepository.save(slotEntity);
        return slotEntity;
    }

}
