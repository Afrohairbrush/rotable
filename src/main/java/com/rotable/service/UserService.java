package com.rotable.service;

import com.rotable.dto.TypeEnum;
import com.rotable.dto.UserDto;
import com.rotable.repository.FakeRepository;
import com.rotable.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final FakeRepository fakeRepository;

    public UserDto saveOrUpdateUser(UserDto userDto) {
        if (fakeRepository.getUser(userDto.getId()).isPresent()) {
            //UUUUUUUpdate
            return fakeRepository.updateUser(userDto)
                    .map(Mapper::mapUserEntity)
                    .orElseThrow();
        } else {
            //Saaaaaaave
            return fakeRepository.saveUser(userDto)
                    .map(Mapper::mapUserEntity)
                    .orElseThrow();
        }
    }

    public List<UserDto> getUsersByType(String type) {
        return fakeRepository.getUsersByType(TypeEnum.valueOf(type)).stream()
                .map(Mapper::mapUserEntity)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(UUID uuid) {
        return fakeRepository.getUser(uuid)
                .map(Mapper::mapUserEntity)
                .orElseThrow();
    }

    public boolean deleteUserById(UUID uuid) {
        return fakeRepository.deleteUserById(uuid);
    }
}
