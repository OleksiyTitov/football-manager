package com.footballmanager.service;

import com.footballmanager.model.Team;
import com.footballmanager.repository.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private static final String DEFAULT_PLAYER_TEAM_NAME = "No team";
    private final TeamRepository teamRepository;
    private final PlayerService playerService;

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
        teamFromDb.setName(team.getName());
        teamFromDb.setBalance(team.getBalance());
        teamFromDb.setCommission(team.getCommission());
        teamFromDb.getPlayers().forEach(p -> p.setTeamName(DEFAULT_PLAYER_TEAM_NAME));
        team.getPlayers().forEach(p -> p.setTeamName(team.getName()));
        teamFromDb.setPlayers(team.getPlayers());
        return teamRepository.save(teamFromDb);
    }

    @Override
    public Team save(Team team) {
        team.getPlayers().forEach(p -> p.setTeamName(team.getName()));
        playerService.saveAll(team.getPlayers());
        return teamRepository.save(team);
    }

    @Override
    public void delete(Long id) {
        Team teamToDelete = teamRepository.getById(id);
        teamToDelete.getPlayers().forEach(p -> {
            p.setTeamName(DEFAULT_PLAYER_TEAM_NAME);
            playerService.update(p.getId(), p);
        });
        teamRepository.deleteById(id);
    }
}
