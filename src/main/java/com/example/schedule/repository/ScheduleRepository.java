package com.example.schedule.repository;

import com.example.schedule.model.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, String> {

    ScheduleEntity findFirstById(String id);
}