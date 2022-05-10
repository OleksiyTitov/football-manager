package com.footballmanager.service;

import com.footballmanager.model.Player;
import com.footballmanager.repository.PLayerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PLayerRepository playerRepository;

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> saveAll(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    @Override
    public List<Player> findPlayersWithNoTeam() {
        return playerRepository.findAllByTeamName("No team");
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player getById(Long id) {
        return playerRepository.getById(id);
    }

    @Override
    public List<Player> getAllByIds(List<Long> ids) {
        return playerRepository.findAllById(ids);
    }

    @Override
    public Player update(Long id, Player player) {
        Player playerFromDb = playerRepository.getById(id);
        playerFromDb.setExperience(player.getExperience());
        playerFromDb.setAge(player.getAge());
        playerFromDb.setName(player.getName());
        playerFromDb.setTeamName(player.getTeamName());
        return playerRepository.save(playerFromDb);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
