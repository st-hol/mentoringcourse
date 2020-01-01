package com.epam.training.sportsbetting.domain;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Outcome {

    private Bet bet;

    private String description;

    private List<OutcomeOdd> outcomeOdds;

    @Override
    public String toString() {
        return "\n\t\tOutcome = " + description +
                "\n\t\t\t" + outcomeOdds +
                "\n\t\t}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return Objects.equals(bet, outcome.bet) &&
                Objects.equals(description, outcome.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet, description);
    }
}
