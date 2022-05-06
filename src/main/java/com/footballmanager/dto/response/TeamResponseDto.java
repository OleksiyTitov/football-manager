package com.footballmanager.dto.response;

import com.footballmanager.model.Player;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class TeamResponseDto {
    private Long id;
    private int commission;
    private BigDecimal balance;
    private List<Player> players;
}
