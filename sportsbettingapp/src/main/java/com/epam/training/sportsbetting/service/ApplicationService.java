package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.training.sportsbetting.builder.WagerBuilder;
import com.epam.training.sportsbetting.db.BettingDataPoolHolder;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.user.Player;
import lombok.Setter;


public class ApplicationService {

    private static ApplicationService instance;
    private BettingDataPoolHolder bettingDataPoolHolder;
    private Random random;

    private ApplicationService() {
        this.random = new Random();
        this.bettingDataPoolHolder = BettingDataPoolHolder.getInstance();
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            synchronized (ApplicationService.class) {
                if (instance == null) {
                    instance = new ApplicationService();
                }
            }
        }
        return instance;
    }

    public List<SportEvent> getSportEventsData() {
        return bettingDataPoolHolder.getSportEventsData();
    }

    public OutcomeOdd findOutcomeOddByNumber(int choice) {
        return bettingDataPoolHolder.getOutcomeOddsData()
                .stream()
                .filter(outcomeOdd -> outcomeOdd.getId() == choice)
                .findFirst().orElse(null);
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

    public int generateRandomIntBounded(int max) {
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

    public void setBettingDataPoolHolder(BettingDataPoolHolder bettingDataPoolHolder) {
        this.bettingDataPoolHolder = bettingDataPoolHolder;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
