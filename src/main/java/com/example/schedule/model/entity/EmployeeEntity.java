package com.example.schedule.model.entity;

import com.example.schedule.model.enums.PositionEnum;
import com.example.schedule.model.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String name;
    private StatusEnum status;
    private PositionEnum position;

    @OneToMany(mappedBy = "administrator")
    private List<SchedulePeriodEntity> schedulePeriodAdministratorList;

    @OneToMany(mappedBy = "executor")
    private List<SchedulePeriodEntity> schedulePeriodExecutorList;
}
