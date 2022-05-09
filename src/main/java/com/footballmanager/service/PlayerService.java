package com.footballmanager.service;

import com.footballmanager.model.Player;
import java.util.List;

public interface PlayerService {
    Player save(Player player);

    List<Player> saveAll(List<Player> players);

    List<Player> findPlayersWithNoTeam();

    List<Player> getAll();

    Player getById(Long id);

    List<Player> getAllByIds(List<Long> ids);

    Player update(Long id, Player player);

    void delete(Long id);
}
