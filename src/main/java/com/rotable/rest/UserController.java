package com.rotable.rest;

import com.rotable.dto.UserDto;
import com.rotable.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/*
 * Normalerweise auchmit Status codes arbeiten usw, aber dazu sind zu wenig informationen vorhanden im Ticket usw.
 * */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UUID> saveOrUpdate(@RequestBody UserDto userDto) {
        // Aauch nicht optimal implementiert. Exception Handler an sich wäre nicht schlecht. Ist halt alles jettz auch nicht 100% nullsafe usw. Mit richtiger DB
        // wäre es sicher etwas angenehmer. UUid stuff nur jetzt mal für simplicity so eingebaut alles.
        return ResponseEntity.ok(userService.saveOrUpdateUser(userDto).getId());

    }

    @GetMapping("/{type}")
    public ResponseEntity<List<UserDto>> getUsersByType(@PathVariable String type) {
        return ResponseEntity.ok(userService.getUsersByType(type));
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam String id) {
        return ResponseEntity.ok(userService.getUserById(UUID.fromString(id)));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUserById(@RequestParam String id) {
        return ResponseEntity.ok(userService.deleteUserById(UUID.fromString(id)));
    }
}
