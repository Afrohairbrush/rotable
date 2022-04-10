package com.rotable.rest;

import com.rotable.dto.ReminderDto;
import com.rotable.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping
    public ResponseEntity<UUID> saveOrUpdate(@RequestBody ReminderDto reminderDto) {
        return ResponseEntity.ok(reminderService.saveOrUpdate(reminderDto).getId());
    }

    @GetMapping("/allByUserId")
    public ResponseEntity<List<ReminderDto>> getRemindersByUserId(@RequestParam String id) {
        return ResponseEntity.ok(reminderService.getRemindersByUserId(UUID.fromString(id)));
    }

    @GetMapping
    public ResponseEntity<ReminderDto> getReminderById(@RequestParam String id) {
        return ResponseEntity.ok(reminderService.getReminderById(UUID.fromString(id)));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUserById(@RequestParam String id) {
        return ResponseEntity.ok(reminderService.deleteReminderById(UUID.fromString(id)));
    }
}
