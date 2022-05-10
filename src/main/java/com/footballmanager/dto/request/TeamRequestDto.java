package com.footballmanager.dto.request;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamRequestDto {
    @Min(value = 0, message = "Minimum commission is 0%")
    @Max(value = 10, message = "Maximum commission is 10%")
    private int commission;
    @Min(value = 0, message = "Balance can't be less than 0")
    private BigDecimal balance;
    @NotBlank(message = "Team name can't be blank")
    private String name;
    @NotNull(message = "There must be at least one player in the team")
    private List<Long> players;
}
