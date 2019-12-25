package com.epam.training.sportsbetting.domain;

import java.util.List;

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
}
