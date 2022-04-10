package com.rotable.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/*
 * Das wäre rein theoretisch die Entity die wir aus der Repo class bekommen
 * */
@Builder
@Getter
@Setter
public class ReminderEntity {
    private UUID id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<UUID> userIds; //1 zu n Beziehung auf Arm implementiert lel
}
