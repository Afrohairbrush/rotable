package com.rotable.util;

import com.rotable.dto.TypeEnum;
import com.rotable.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    public static List<UserEntity> buildTestUserList() {
        List<UserEntity> tempList = new ArrayList<>();
        tempList.add(buildTestUser("Spongebob", "Schwammkopf", TypeEnum.BODY));
        tempList.add(buildTestUser("Patrick", "Star", TypeEnum.FEET));
        tempList.add(buildTestUser("Tom", "Turbo", TypeEnum.FEET));
        tempList.add(buildTestUser("Dominik", "Rossmanith", TypeEnum.HEAD));
        return tempList;
    }

    private static UserEntity buildTestUser(String firstName, String lastName, TypeEnum type) {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .injury(type)
                .build();
    }
}
