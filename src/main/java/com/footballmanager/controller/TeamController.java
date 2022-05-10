package com.footballmanager.controller;

import com.footballmanager.dto.mapper.TeamDtoMapper;
import com.footballmanager.dto.request.TeamRequestDto;
import com.footballmanager.dto.request.TransferRequestDto;
import com.footballmanager.dto.response.TeamResponseDto;
import com.footballmanager.service.TeamService;
import com.footballmanager.service.TransferService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamDtoMapper teamDtoMapper;
    private final TransferService transferService;

    @GetMapping
    List<TeamResponseDto> getAll() {
        return teamService.getAll()
                .stream()
                .map(teamDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    TeamResponseDto getById(@PathVariable Long id) {
        return teamDtoMapper.mapToDto(teamService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TeamResponseDto save(@RequestBody @Valid TeamRequestDto requestDto) {
        return teamDtoMapper.mapToDto(
                teamService.save(teamDtoMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    TeamResponseDto update(@PathVariable Long id,
                          @RequestBody @Valid TeamRequestDto requestDto) {
        return teamDtoMapper.mapToDto(
                teamService.update(id, teamDtoMapper.mapToModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        teamService.delete(id);
    }

    @PostMapping("/{toId}/players")
    @ResponseStatus(HttpStatus.ACCEPTED)
    TeamResponseDto buyPlayers(@PathVariable Long toId, @RequestBody TransferRequestDto requestDto) {
        transferService.transfer(requestDto.getFromId(), toId, requestDto.getPlayerIds());
        return teamDtoMapper.mapToDto(teamService.getById(toId));
    }
}
