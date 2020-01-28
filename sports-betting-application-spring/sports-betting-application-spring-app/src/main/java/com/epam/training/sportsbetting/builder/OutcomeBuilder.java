package com.epam.training.sportsbetting.builder;

import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

public class OutcomeBuilder {
    private Bet bet;
    private String description;
    private List<OutcomeOdd> outcomeOdds;

    private OutcomeListBuilder outcomeListBuilder;

    public OutcomeBuilder(OutcomeListBuilder outcomeListBuilder) {
        this.outcomeListBuilder = outcomeListBuilder;
    }

    public OutcomeBuilder setBet(Bet bet) {
        this.bet = bet;
        return this;
    }

    public OutcomeBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public OutcomeBuilder setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
        return this;
    }

    private Outcome createOutcome() {
        return new Outcome(bet, description, outcomeOdds);
    }

    public OutcomeListBuilder addOutcomeToList() {
        Outcome outcome = createOutcome();
        this.outcomeListBuilder.addOutcome(outcome);
        return this.outcomeListBuilder;
    }
}
