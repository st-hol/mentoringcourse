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
public class Bet {

    private SportEvent sportEvent;

    private String description;

    private BetType type;

    private List<Outcome> outcomes;

    @Override
    public String toString() {
        return "\nBet = \'" + description + "\' , type = " + type +
                "\n\toutcomes=" + outcomes +
                "\n}" +
                "\n----------------------------------------------------";
    }

    public enum BetType {
        WINNER, GOALS, PLAYERS_SCORE
    }
}
