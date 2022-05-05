package com.footballmanager.controller;

import com.footballmanager.dto.mapper.PlayersDtoMapper;
import com.footballmanager.dto.request.PlayerRequestDto;
import com.footballmanager.dto.response.PlayerResponseDto;
import com.footballmanager.service.PlayerService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayersController {
    private final PlayerService playerService;
    private final PlayersDtoMapper playersDtoMapper;

    @GetMapping
    List<PlayerResponseDto> getAllPlayers() {
        return playerService.getAll()
                .stream()
                .map(playersDtoMapper::mapToDto)
                .toList();
    }

    @PostMapping
    PlayerResponseDto create(@RequestBody @Valid PlayerRequestDto requestDto) {
        return playersDtoMapper.mapToDto(
                playerService.save(playersDtoMapper.mapToModel(requestDto)));
    }

    @PutMapping
    PlayerResponseDto update(@RequestBody @Valid PlayerRequestDto requestDto) {
        return playersDtoMapper.mapToDto(
                playerService.update(playersDtoMapper.mapToModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
