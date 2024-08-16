package com.example.schedule.service;

import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.enums.PositionEnum;
import com.example.schedule.model.enums.StatusEnum;
import com.example.schedule.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeEntity getEmployee(String inEmployeeId) {
        return employeeRepository.findFirstById(inEmployeeId);
    }

    public List<EmployeeEntity> getEmployeeAll() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity postEmployee (String inName, String inStatus, String inPosition) {
        EmployeeEntity employeeEntity = new EmployeeEntity(
                UUID.randomUUID().toString().replace("-","").toLowerCase(),
                inName,
                StatusEnum.valueOf(inStatus),
                PositionEnum.valueOf(inPosition),
                List.of(),
                List.of()
        );
        employeeRepository.save(employeeEntity);
        return employeeEntity;
    }


}
