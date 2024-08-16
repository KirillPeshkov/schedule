package com.example.schedule.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "slot")
public class SlotEntity {
    @Id
    private String id;
    private LocalTime beginTime;
    private LocalTime endTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "schedule_template_id", referencedColumnName = "id")
    private ScheduleTemplateEntity scheduleTemplate;

    @OneToMany(mappedBy = "slot")
    private List<SchedulePeriodEntity> schedulePeriodList;
}