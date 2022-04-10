package com.rotable.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ReminderDto {
    @NonNull
    private UUID id;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private List<UUID> userIds;

    @JsonCreator
    public ReminderDto(@JsonProperty("id") UUID id,
                       @JsonProperty("description") String description,
                       @JsonProperty("startDate") LocalDate startDate,
                       @JsonProperty("endDate") LocalDate endDate,
                       @JsonProperty("userIds")  List<UUID> userIds) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userIds = userIds;
    }
}
