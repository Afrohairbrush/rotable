package com.rotable.repository;

import com.rotable.dto.ReminderDto;
import com.rotable.dto.TypeEnum;
import com.rotable.dto.UserDto;
import com.rotable.entity.ReminderEntity;
import com.rotable.entity.UserEntity;
import com.rotable.util.Mapper;
import com.rotable.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/*
 * Fake Repo -> repo ist im prinzip einfach eine List lol. Hab auch gleich reminder dazu gehaut, ansonsten würde ich es sehr wahrschienlich in zwei aufteilen,
 * die File wäre mir atm etwas zu groooooß
 * Arbeite mit OPtionals nur weil wenn ich mich jetzt nicht irre ist bei den db Abfragen immer return wert mit optional
 * */
@Component
@Slf4j
public class FakeRepository {

    private List<UserEntity> userEntities = TestUtils.buildTestUserList();
    private List<ReminderEntity> reminderEntities = new ArrayList<>();

    /*USER REPO*/

    public Optional<UserEntity> saveUser(UserDto userDto) {
        // Speichern. Mappt halt jetzt nur auf eine UserEntity um
        UserEntity userEntity = Mapper.mapUserDto(userDto);
        userEntity.setId(UUID.randomUUID());
        userEntities.add(userEntity);
        return Optional.ofNullable(userEntity);
    }

    public Optional<UserEntity> updateUser(UserDto userDto) {
        // Update uuuuser
        Optional<UserEntity> userEntity = getUser(userDto.getId()); //Sollte gleiche Speicherreferenz sein
        if (userEntity.isPresent()) {
            UserEntity temp = userEntity.get();
            temp.setFirstName(userDto.getFirstName());
            temp.setLastName(userDto.getLastName());
            temp.setInjury(userDto.getInjury());
        }
        return userEntity;
    }

    public List<UserEntity> getUsersByType(TypeEnum type) {
        // Ja gut, hier würde er sich alle holen die den passenden Typen zb haben
        return userEntities.stream()
                .filter(userEntity -> userEntity.getInjury().equals(type))
                .collect(Collectors.toList());
    }

    public Optional<UserEntity> getUser(UUID uuid) {
        // Suchi suchi user
        return userEntities.stream()
                .filter(userEntity -> userEntity.getId().equals(uuid))
                .findFirst();
    }

    public boolean deleteUserById(UUID uuid) {
        Optional<UserEntity> toDelete = userEntities.stream()
                .filter(userEntity -> userEntity.getId().equals(uuid))
                .findFirst();//Nicht nullsafe usw. Hier kann man orElseThrow oder was auch immer machen
        if (toDelete.isPresent()) {
            userEntities.remove(toDelete.get());
        }
        return toDelete.isPresent();
    }

    /*REMINDER REPO*/

    public Optional<ReminderEntity> saveReminder(ReminderDto reminderDto) {
        // Speichern. Mappt halt jetzt nur auf eine UserEntity um
        ReminderEntity reminderEntity = Mapper.mapReminderDto(reminderDto);
        reminderEntity.setId(UUID.randomUUID());
        reminderEntities.add(reminderEntity);
        return Optional.ofNullable(reminderEntity);
    }

    public Optional<ReminderEntity> updateReminder(ReminderDto reminderDto) {
        //UPPPPPPDATE
        Optional<ReminderEntity> reminderEntity = getReminder(reminderDto.getId()); //Sollte gleiche Speicherreferenz sein
        if (reminderEntity.isPresent()) {
            ReminderEntity temp = reminderEntity.get();
            temp.setDescription(reminderDto.getDescription());
            temp.setEndDate(reminderDto.getEndDate());
            temp.setStartDate(reminderDto.getStartDate());
            temp.setUserIds(reminderDto.getUserIds());
        }
        return reminderEntity;
    }

    public List<ReminderEntity> getRemindersByUserId(UUID uuid) {
        // Alle reminder holen wo die idd in der n beziehung ist
        return reminderEntities.stream()
                .filter(reminderEntity -> reminderEntity.getUserIds().contains(uuid))
                .collect(Collectors.toList());
    }

    public Optional<ReminderEntity> getReminder(UUID uuid) {
        // Suchi suchi reminder
        return reminderEntities.stream()
                .filter(reminderEntity -> reminderEntity.getId().equals(uuid))
                .findFirst();
    }

    public boolean deleteReminderById(UUID uuid) {
        Optional<ReminderEntity> toDelete = reminderEntities.stream()
                .filter(reminderEntity -> reminderEntity.getId().equals(uuid))
                .findFirst();
        if (toDelete.isPresent()) {
            reminderEntities.remove(toDelete.get());
        }
        return toDelete.isPresent();
    }
}
