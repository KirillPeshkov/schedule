package com.example.schedule.model.entity;

import com.example.schedule.model.enums.SlotTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "schedule_period")
public class SchedulePeriodEntity {
    @Id
    private String id;
    private SlotTypeEnum slotType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private SlotEntity slot;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private ScheduleEntity schedule;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private EmployeeEntity administrator;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "executor_id", referencedColumnName = "id")
    private EmployeeEntity executor;
}