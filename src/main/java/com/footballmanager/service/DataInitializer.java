package com.footballmanager.service;

import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitializer {
    private final static int PLAYER_AGE = 20;
    private final static int PLAYER_EXPERIENCE = 10;
    private final static int TEAM_COMMISSION = 10;
    private final static BigDecimal TEAM_BALANCE = BigDecimal.valueOf(10_000_000);
    private final TeamService teamService;
    private final PlayerService playerService;

    @PostConstruct
    public void inject() {
        List<Player> firstTeamPlayers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Player player = new Player();
            player.setAge(PLAYER_AGE);
            player.setExperience(PLAYER_EXPERIENCE);
            player.setName("Player of the first team #" + (i + 1));
            firstTeamPlayers.add(player);
            playerService.save(player);
        }

        List<Player> secondTeamPlayers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Player player = new Player();
            player.setAge(PLAYER_AGE);
            player.setExperience(PLAYER_EXPERIENCE);
            player.setName("Player of the second team #" + (i + 1));
            secondTeamPlayers.add(player);
            playerService.save(player);
        }

        Team firstTeam = new Team();
        firstTeam.setName("Firs team");
        firstTeam.setBalance(TEAM_BALANCE);
        firstTeam.setCommission(TEAM_COMMISSION);
        firstTeam.setPlayers(firstTeamPlayers);
        teamService.save(firstTeam);

        Team secondTeam = new Team();
        secondTeam.setName("Second team");
        secondTeam.setBalance(TEAM_BALANCE);
        secondTeam.setCommission(TEAM_COMMISSION);
        secondTeam.setPlayers(secondTeamPlayers);
        teamService.save(secondTeam);
    }
}
