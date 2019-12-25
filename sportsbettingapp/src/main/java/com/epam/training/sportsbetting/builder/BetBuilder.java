
package com.epam.training.sportsbetting.builder;


import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.SportEvent;


public class BetBuilder {

    private SportEvent sportEvent;
    private String description;
    private Bet.BetType type;
    private List<Outcome> outcomes;

    private BetListBuilder betListBuilder;

    public BetBuilder() {
    }

    public BetBuilder(BetListBuilder betListBuilder) {
        this.betListBuilder = betListBuilder;
    }

    public BetBuilder setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
        return this;

    }

    public BetBuilder setDescription(String description) {
        this.description = description;
        return this;

    }

    public BetBuilder setType(Bet.BetType type) {
        this.type = type;
        return this;

    }

    public BetBuilder setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        return this;

    }

    public BetBuilder setBetListBuilder(BetListBuilder betListBuilder) {
        this.betListBuilder = betListBuilder;
        return this;
    }

    public Bet createBet() {
        return new Bet(
                sportEvent,
                description,
                type,
                outcomes);
    }

    public BetListBuilder addBetToList() {
        Bet bet = createBet();
        this.betListBuilder.addBet(bet);
        return this.betListBuilder;
    }

}
