package com.footballmanager.service;

import com.footballmanager.exception.TransferException;
import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TeamService teamService;
    private final PlayerService playerService;

    @Override
    public void transfer(Long fromId, Long toId, List<Long> playerIds) {
        Team from = teamService.getById(fromId);
        Team to = teamService.getById(toId);
        List<Player> players = playerService.getAllByIds(playerIds);
        BigDecimal transferPrice = getFullPrice(from.getCommission(), players);
        if (to.getBalance().max(transferPrice).equals(transferPrice)) {
            throw new TransferException("Team `" + to.getName() + "` doesn't have enough money on the balance");
        }
        from.setBalance(from.getBalance().add(transferPrice));
        to.setBalance(to.getBalance().subtract(transferPrice));

        List<Player> playersFromTeam = from.getPlayers();
        playersFromTeam.removeAll(players);
        from.setPlayers(playersFromTeam);

        List<Player> playersToTeam = to.getPlayers();
        playersToTeam.addAll(players);
        to.setPlayers(playersToTeam);

        teamService.update(fromId, from);
        teamService.update(toId, to);
    }

    private BigDecimal getFullPrice(int commission, List<Player> players) {
        BigDecimal fullPrice = new BigDecimal(0);
        for (Player player : players) {
            BigDecimal playerPrice = BigDecimal.valueOf((double) player.getExperience() * 100000 / player.getAge());
            fullPrice = fullPrice.add(playerPrice);
        }
        fullPrice = fullPrice.add(
                fullPrice.multiply(BigDecimal.valueOf((double) commission / 100)));
        return fullPrice;
    }
}
