package com.footballmanager.service;

import com.footballmanager.model.Player;
import java.util.List;

public interface PlayerService {
    Player save(Player player);

    List<Player> getAll();

    Player getByName(String name);

    Player update(Player player);

    void delete(Long id);
}
