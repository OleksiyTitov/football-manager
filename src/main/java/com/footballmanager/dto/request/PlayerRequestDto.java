package com.footballmanager.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerRequestDto {
    @Min(value = 0, message = "The player's experience must not be less than 0")
    private int experience;
    @Min(value = 18, message = "The player's age must not be less than 18")
    private int age;
    @NotBlank(message = "Name may not be blank")
    private String name;
    private String teamName;
}
