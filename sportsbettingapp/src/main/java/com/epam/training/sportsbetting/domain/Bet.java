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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Objects.equals(sportEvent, bet.sportEvent) &&
                Objects.equals(description, bet.description) &&
                type == bet.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportEvent, description, type);
    }

    public enum BetType {
        WINNER, GOALS, PLAYERS_SCORE
    }
}
