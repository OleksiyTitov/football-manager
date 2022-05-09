package com.footballmanager.repository;

import com.footballmanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PLayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeamName(String teamName);
}
