package com.rotable.util;

import com.rotable.dto.ReminderDto;
import com.rotable.dto.UserDto;
import com.rotable.entity.ReminderEntity;
import com.rotable.entity.UserEntity;

import java.util.UUID;

public class Mapper {
    public static UserEntity mapUserDto(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId() != null ? userDto.getId() : UUID.randomUUID())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .injury(userDto.getInjury())
                .build();
    }

    public static UserDto mapUserEntity(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId() != null ? userEntity.getId() : UUID.randomUUID())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .injury(userEntity.getInjury())
                .build();
    }

    public static ReminderEntity mapReminderDto(ReminderDto reminderDto) {
        return ReminderEntity.builder()
                .id(reminderDto.getId() != null ? reminderDto.getId() : UUID.randomUUID())
                .description(reminderDto.getDescription())
                .startDate(reminderDto.getStartDate())
                .endDate(reminderDto.getEndDate())
                .userIds(reminderDto.getUserIds())
                .build();
    }

    public static ReminderDto mapReminderEntity(ReminderEntity reminderEntity) {
        return ReminderDto.builder()
                .id(reminderEntity.getId() != null ? reminderEntity.getId() : UUID.randomUUID())
                .description(reminderEntity.getDescription())
                .startDate(reminderEntity.getStartDate())
                .endDate(reminderEntity.getEndDate())
                .userIds(reminderEntity.getUserIds())
                .build();
    }
}

