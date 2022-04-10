package com.rotable.entity;

import com.rotable.dto.TypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/*
 * Das wäre rein theoretisch die Entity die wir aus der Repo class bekommen
 * */
@Builder
@Getter
@Setter
public class UserEntity {
    private UUID id;
    private String firstName;
    private String lastName;
    private TypeEnum injury;
}
