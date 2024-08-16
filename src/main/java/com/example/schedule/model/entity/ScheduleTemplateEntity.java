package com.example.schedule.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "schedule_template")
public class ScheduleTemplateEntity {
    @Id
    private String id;
    private String name;
    private LocalDate creationDate;

    @OneToMany(mappedBy = "scheduleTemplate")
    private List<SlotEntity> slotList;
}