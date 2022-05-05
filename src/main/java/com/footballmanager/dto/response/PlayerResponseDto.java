package com.footballmanager.dto.response;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private int experience;
    private int age;
    private String name;
}
