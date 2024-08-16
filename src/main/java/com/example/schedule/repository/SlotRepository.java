package com.example.schedule.repository;

import com.example.schedule.model.entity.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, String> {

    SlotEntity findFirstById(String id);
}