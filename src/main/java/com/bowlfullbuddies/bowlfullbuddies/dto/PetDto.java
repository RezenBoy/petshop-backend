package com.bowlfullbuddies.bowlfullbuddies.dto;

import lombok.Data;

@Data
public class PetDto {
    private Long id;
    private String name;
    private String type;
    private String breed;
    private Integer age;
    private String color;
}
