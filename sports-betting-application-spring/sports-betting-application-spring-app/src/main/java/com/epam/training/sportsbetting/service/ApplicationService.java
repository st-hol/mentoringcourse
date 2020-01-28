package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.sportsbetting.builder.WagerBuilder;
import com.epam.training.sportsbetting.db.BettingDataPoolHolder;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.ui.util.ApplicationView;


@Service
public class ApplicationService {

    @Autowired
    private BettingDataPoolHolder bettingDataPoolHolder;
    @Autowired
    private ApplicationView applicationView;
    @Autowired
    private Random random;

    public List<SportEvent> getSportEventsData() {
        return bettingDataPoolHolder.getSportEventsData();
    }

    public OutcomeOdd findOutcomeOddByNumber(int choice) {
        return bettingDataPoolHolder.getOutcomeOddsData().get(choice);
    }

    public Wager createWager(Player player, BigDecimal wagerAmount, OutcomeOdd userOutcomeOdd) {
        return new WagerBuilder()
                .setPlayer(player)
                .setAmount(wagerAmount)
                .setOutcomeOdd(userOutcomeOdd)
                .setCurrency(player.getCurrency())
                .setTimestampCreated(LocalDateTime.now())
                .createWager();
    }

    /**
     * one winner per each bet  -> one outcome possible
     *
     * @param sportEvents
     * @return
     */
    public Result generateResult(List<SportEvent> sportEvents, List<Wager> userWagers, Player player) {
        Result result = new Result();
        List<Outcome> winnerOutcomes = new ArrayList<>();

        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                int randomOutcomeIndex = generateRandomIntBounded(bet.getOutcomes().size() - 1);
                winnerOutcomes.add(bet.getOutcomes().get(randomOutcomeIndex));
            }
        }
        result.setWinnerOutcomes(winnerOutcomes);
        calculateResults(result, userWagers, player);
        return result;
    }

    public BigDecimal calculateTotalWonSum(BigDecimal startBalance, BigDecimal finalBalance) {
        return finalBalance.subtract(startBalance);
    }

    private int generateRandomIntBounded(int max) {
        return random.nextInt(max);
    }

    private void calculateResults(Result result, List<Wager> userWagers, Player player) {
        List<Outcome> winnerOutcomes = result.getWinnerOutcomes();
        userWagers.forEach(userWager -> {
            userWager.getOutcomeOdd().getOutcome()
                    .getBet()
                    .getSportEvent().setResult(result);
            if (isWinnerWager(winnerOutcomes, userWager)) {
                userWager.setWinner(true);
                updatePlayerBalance(player, userWager);
            }
        });
    }

    private void updatePlayerBalance(Player player, Wager wager) {
        player.setBalance(
                player.getBalance()
                        .add(wager.getAmount()
                                .multiply(wager.getOutcomeOdd()
                                        .getValue())));
    }

    private boolean isWinnerWager(List<Outcome> winnerOutcomes, Wager userWager) {
        return winnerOutcomes.contains(userWager.getOutcomeOdd().getOutcome());
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
