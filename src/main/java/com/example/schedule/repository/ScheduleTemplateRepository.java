package com.example.schedule.repository;

import com.example.schedule.model.entity.ScheduleTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTemplateRepository extends JpaRepository<ScheduleTemplateEntity, String> {

    ScheduleTemplateEntity findFirstById(String id);
}