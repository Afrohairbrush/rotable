package com.rotable.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private TypeEnum injury;

    @JsonCreator
    public UserDto(@JsonProperty("id") UUID id,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("injury") TypeEnum injury) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.injury = injury;
    }
}
