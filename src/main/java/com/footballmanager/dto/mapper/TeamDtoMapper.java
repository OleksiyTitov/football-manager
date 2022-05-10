package com.footballmanager.dto.mapper;

import com.footballmanager.dto.request.TeamRequestDto;
import com.footballmanager.dto.response.TeamResponseDto;
import com.footballmanager.model.Team;
import com.footballmanager.service.PlayerService;
import org.springframework.stereotype.Component;

@Component
public class TeamDtoMapper {
    private PlayerService playerService;

    public TeamDtoMapper(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Team mapToModel(TeamRequestDto requestDto) {
        Team team = new Team();
        team.setBalance(requestDto.getBalance());
        team.setCommission(requestDto.getCommission());
        team.setName(requestDto.getName());
        team.setPlayers(playerService.getAllByIds(requestDto.getPlayers()));
        return team;
    }

    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto responseDto = new TeamResponseDto();
        responseDto.setId(team.getId());
        responseDto.setName(team.getName());
        responseDto.setBalance(team.getBalance());
        responseDto.setCommission(team.getCommission());
        responseDto.setPlayers(team.getPlayers());
        return responseDto;
    }
}
