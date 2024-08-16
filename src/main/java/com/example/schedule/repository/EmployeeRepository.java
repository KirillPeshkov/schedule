package com.example.schedule.repository;

import com.example.schedule.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    EmployeeEntity findFirstById(String id);
}