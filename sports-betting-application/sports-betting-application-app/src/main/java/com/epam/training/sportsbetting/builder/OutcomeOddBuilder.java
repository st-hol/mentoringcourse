package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOddBuilder {
    private int id;
    private BigDecimal value;
    private Outcome outcome;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;

    private OutcomeOddListBuilder outcomeOddListBuilder;

    public OutcomeOddBuilder() {
    }

    public OutcomeOddBuilder(OutcomeOddListBuilder outcomeOddListBuilder) {
        this.outcomeOddListBuilder = outcomeOddListBuilder;
    }

    public OutcomeOddBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public OutcomeOddBuilder setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public OutcomeOddBuilder setOutcome(Outcome outcome) {
        this.outcome = outcome;
        return this;
    }

    public OutcomeOddBuilder setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public OutcomeOddBuilder setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
        return this;
    }

    public OutcomeOddBuilder setOutcomeOddListBuilder(OutcomeOddListBuilder outcomeOddListBuilder) {
        this.outcomeOddListBuilder = outcomeOddListBuilder;
        return this;
    }

    public OutcomeOdd createOutcomeOdd() {
        return new OutcomeOdd(
                id,
                value,
                outcome,
                validFrom,
                validUntil);
    }

    public OutcomeOddListBuilder addOutcomeOddToList() {
        OutcomeOdd outcomeOdd = createOutcomeOdd();
        this.outcomeOddListBuilder.addOutcomeOdd(outcomeOdd);
        return this.outcomeOddListBuilder;
    }
}
