package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

import java.util.List;

public class OutcomeBuilder {
    private Bet bet;
    private String description;
    private List<OutcomeOdd> outcomeOdds;

    private OutcomeListBuilder outcomeListBuilder;

    public OutcomeBuilder() {
    }

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

    public Outcome createOutcome() {
        return new Outcome(bet,
                description,
                outcomeOdds);
    }

    public OutcomeListBuilder addOutcomeToList() {
        Outcome outcome = createOutcome();
        this.outcomeListBuilder.addOutcome(outcome);
        return this.outcomeListBuilder;
    }
}
