
package com.epam.training.sportsbetting.builder;

import java.util.ArrayList;

import com.epam.training.sportsbetting.domain.Bet;


public class BetListBuilder {

    private ArrayList listOfBets;

    public BetListBuilder addList() {
        this.listOfBets = new ArrayList();
        return this;
    }

    public BetListBuilder addBet(Bet bet) {
        listOfBets.add(bet);
        return this;
    }

    public BetBuilder addBet() {
        return new BetBuilder(this);
    }

    public ArrayList buildList() {
        return listOfBets;
    }

}