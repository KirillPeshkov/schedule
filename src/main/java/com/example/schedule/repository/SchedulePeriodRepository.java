package com.example.schedule.repository;

import com.example.schedule.model.entity.SchedulePeriodEntity;
import com.example.schedule.model.enums.SlotTypeEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulePeriodRepository extends JpaRepository<SchedulePeriodEntity, String> {

    SchedulePeriodEntity findFirstById(String id);

    @Query (value = """
            SELECT * FROM schedule_period
            WHERE (id = :id or :id is null)
            AND (slot_id = :slotId or :slotId is null)
            AND (schedule_id = :scheduleId or :scheduleId is null)
            AND (slot_type = :slotType or :slotType is null)
            AND (administrator_id = :administratorId or :administratorId is null)
            AND (executor_id = :executorId or :executorId is null)
            LIMIT :limit OFFSET :offset
            """, nativeQuery = true)
    List<SchedulePeriodEntity> getAllNew(
            String id,
            String slotId,
            String scheduleId,
            SlotTypeEnum slotType,
            String administratorId,
            String executorId,
            Integer limit,
            Integer offset
    );
}