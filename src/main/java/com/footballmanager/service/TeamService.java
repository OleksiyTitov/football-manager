package com.footballmanager.service;

import com.footballmanager.model.Team;
import java.util.List;

public interface TeamService {
    Team getById(Long id);

    List<Team> getAll();

    Team update(Long id, Team team);

    Team save(Team team);

    void delete(Long id);
}
