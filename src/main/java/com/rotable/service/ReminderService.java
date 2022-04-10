package com.rotable.service;

import com.rotable.dto.ReminderDto;
import com.rotable.entity.ReminderEntity;
import com.rotable.repository.FakeRepository;
import com.rotable.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final FakeRepository fakeRepository;

    public ReminderDto saveOrUpdate(ReminderDto reminderDto) {

        if (!validateReminder(reminderDto)) {
            throw new IllegalArgumentException("Es besteht mit zu mindest einer Erinnerung eine Überschneidung"); //Könnnte man noch sehr viel verfeinern usw
        }

        if (fakeRepository.getReminder(reminderDto.getId()).isPresent()) {
            //UUUUUUUpdate
            return fakeRepository.updateReminder(reminderDto)
                    .map(Mapper::mapReminderEntity)
                    .orElseThrow();
        } else {
            //Saaaaaaave
            return fakeRepository.saveReminder(reminderDto)
                    .map(Mapper::mapReminderEntity)
                    .orElseThrow();
        }
    }

    public List<ReminderDto> getRemindersByUserId(UUID uuid) {
        return fakeRepository.getRemindersByUserId(uuid).stream()
                .map(Mapper::mapReminderEntity)
                .collect(Collectors.toList());
    }

    public ReminderDto getReminderById(UUID uuid) {
        return fakeRepository.getReminder(uuid)
                .map(Mapper::mapReminderEntity)
                .orElseThrow();
    }

    public boolean deleteReminderById(UUID uuid) {
        return fakeRepository.deleteReminderById(uuid);
    }

    private boolean validateReminder(ReminderDto reminderDto) {
        List<ReminderEntity> reminderEntitiesToCheck = new ArrayList<>();

        reminderDto.getUserIds().forEach(uuid ->
                reminderEntitiesToCheck.addAll(fakeRepository.getRemindersByUserId(uuid))
        );

        return reminderEntitiesToCheck.stream()
                .noneMatch(toCheck -> (toCheck.getStartDate().isBefore(reminderDto.getEndDate()) && toCheck.getEndDate().isAfter(reminderDto.getEndDate())) ||
                        (toCheck.getStartDate().isBefore(reminderDto.getStartDate()) && toCheck.getEndDate().isAfter(reminderDto.getStartDate())));
    }
}
