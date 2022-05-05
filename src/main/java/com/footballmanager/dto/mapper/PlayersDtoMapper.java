package com.footballmanager.dto.mapper;

import com.footballmanager.dto.request.PlayerRequestDto;
import com.footballmanager.dto.response.PlayerResponseDto;
import com.footballmanager.model.Player;

public class PlayersDtoMapper {
    public Player mapToModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setName(dto.getName());
        player.setAge(dto.getAge());
        player.setExperience(dto.getExperience());
        return player;
    }

    public PlayerResponseDto mapToDto(Player player) {
        PlayerResponseDto responseDto = new PlayerResponseDto();
        responseDto.setId(player.getId());
        responseDto.setName(player.getName());
        responseDto.setAge(player.getAge());
        responseDto.setExperience(player.getExperience());
        return responseDto;
    }
}
