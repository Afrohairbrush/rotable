package com.rotable.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TypeEnum {
    HEAD("Kopf"),
    BODY("Koerper"),
    FEET("Fuesse");

    private final String dtoValue;
}
