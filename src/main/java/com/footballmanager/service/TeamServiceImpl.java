package com.footballmanager.service;

import com.footballmanager.model.Team;
import com.footballmanager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team getById(Long id) {
        return teamRepository.getById(id);
    }

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    public Team update(Long id, Team team) {
        return null;
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void delete(Long id) {

    }
}
