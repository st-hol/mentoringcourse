package com.epam.training.sportsbetting.db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.training.sportsbetting.builder.BetListBuilder;
import com.epam.training.sportsbetting.builder.OutcomeListBuilder;
import com.epam.training.sportsbetting.builder.OutcomeOddListBuilder;
import com.epam.training.sportsbetting.builder.SportEventBuilder;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.google.common.collect.Lists;

import lombok.Getter;

@Getter
public class BettingDataPoolHolder {

    private static final int MAX_ODD = 10;
    private static int outcomeOddId;

    private static BettingDataPoolHolder instance;
    private List<SportEvent> sportEvents;
    private List<OutcomeOdd> outcomeOdds;

    private BettingDataPoolHolder() {
        outcomeOdds = new ArrayList<>();
        populateSportEvents();
    }

    /**
     * singleton
     */
    public static BettingDataPoolHolder getInstance() {
        if (instance == null) {
            synchronized (BettingDataPoolHolder.class) {
                if (instance == null) {
                    instance = new BettingDataPoolHolder();
                }
            }
        }
        return instance;
    }

    public List<SportEvent> getSportEventsData() {
        return sportEvents;
    }

    public List<OutcomeOdd> getOutcomeOddsData() {
        return outcomeOdds;
    }


    /**
     * generating EVENT
     */
    private void populateSportEvents() {
        sportEvents = new ArrayList<>();

        SportEvent event = new SportEventBuilder()
                .setTitle("Arsenal vs Chelsea")
                .setStartDate(LocalDateTime.of(2016, 2, 3, 0, 0, 0))
                .setEndDate(LocalDateTime.of(2016, 2, 5, 0, 0, 0))
                .createFootballSportEvent();

        event.setBets(populateFootballBets(event));
        sportEvents.add(event);
    }

    /**
     * generating BETS for certain EVENT
     */
    private List<Bet> populateFootballBets(SportEvent sportEvent) {

        List<Bet> bets = new BetListBuilder().addList()
                .addBet()
                .setSportEvent(sportEvent)
                .setDescription("Oliver Giroud score")
                .setType(Bet.BetType.PLAYERS_SCORE).addBetToList()
                .addBet()
                .setSportEvent(sportEvent)
                .setDescription("number of scored goals")
                .setType(Bet.BetType.GOALS).addBetToList()
                .addBet()
                .setSportEvent(sportEvent)
                .setDescription("winner")
                .setType(Bet.BetType.WINNER).addBetToList()
                .buildList();


        List<Outcome> possibleOutc = populateOutcomeOddsByDescriptions(bets.get(0), Lists.newArrayList("1", "2"));
        bets.get(0).setOutcomes(possibleOutc);

        possibleOutc = populateOutcomeOddsByDescriptions(bets.get(1), Lists.newArrayList("0", "3"));
        bets.get(1).setOutcomes(possibleOutc);

        possibleOutc = populateOutcomeOddsByDescriptions(bets.get(2), Lists.newArrayList("Arsenal", "Chelsea"));
        bets.get(2).setOutcomes(possibleOutc);

        return bets;
    }

    /**
     * generating OUTCOMES for certain BET
     */
    private List<Outcome> populateOutcomeOddsByDescriptions(Bet bet, List<String> outcomeDescriptions) {
        OutcomeListBuilder builder = new OutcomeListBuilder().addList();
        outcomeDescriptions.forEach(outcomeDescription -> builder.addOutcome()
                .setBet(bet)
                .setDescription(outcomeDescription)
                .setOutcomeOdds(populateRandomOutcomeOdds()).addOutcomeToList());
        List<Outcome> possibleOutcomes = builder.buildList();
        assignOutcomeToEachOdd(possibleOutcomes);
        return possibleOutcomes;
    }

    /**
     * cyclic dependency...Outcome has OutcomeOdd and vice versa
     */
    private void assignOutcomeToEachOdd(List<Outcome> outcomes) {
        outcomes.forEach(outcome -> outcome.getOutcomeOdds()
                .forEach(outcomeOdd -> outcomeOdd.setOutcome(outcome)));
    }

    /**
     * generating OUTCOME_ODD for certain OUTCOME
     */
    private List<OutcomeOdd> populateRandomOutcomeOdds() {
        List<OutcomeOdd> odds = new OutcomeOddListBuilder().addList()
                .addOutcomeOdd()
                .setId(++outcomeOddId)
                .setValidFrom(LocalDateTime.MIN)
                .setValidUntil(LocalDateTime.MAX)
                .setValue(BigDecimal.valueOf(new Random().nextInt(MAX_ODD)))
                .addOutcomeOddToList()
                .buildList();
        outcomeOdds.addAll(odds);
        return odds;
    }

}
