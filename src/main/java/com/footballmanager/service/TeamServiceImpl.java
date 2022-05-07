package com.footballmanager.service;

import com.footballmanager.model.Team;
import com.footballmanager.repository.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return teamRepository.findAll();
    }

    @Override
    public Team update(Long id, Team team) {
        Team teamFromDb = teamRepository.getById(id);
        teamFromDb.setBalance(teamFromDb.getBalance());
        teamFromDb.setCommission(team.getCommission());
        teamFromDb.setPlayers(team.getPlayers());
        return teamRepository.save(teamFromDb);
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
