package com.footballmanager.dto.request;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequestDto {
    @Min(value = 0, message = "Team id can't be lass then 0")
    private Long fromId;
    @NotNull(message = "You need to buy at least 1 player")
    private List<Long> playerIds;
}
