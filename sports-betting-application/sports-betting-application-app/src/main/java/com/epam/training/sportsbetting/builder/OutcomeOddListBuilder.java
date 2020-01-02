
package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.OutcomeOdd;

import java.util.ArrayList;


public class OutcomeOddListBuilder {

    private ArrayList listOfOutcomeOdds;

    public OutcomeOddListBuilder addList() {
        this.listOfOutcomeOdds = new ArrayList();
        return this;
    }

    public OutcomeOddListBuilder addOutcomeOdd(OutcomeOdd outcomeOdd) {
        listOfOutcomeOdds.add(outcomeOdd);
        return this;
    }

    public OutcomeOddBuilder addOutcomeOdd() {
        return new OutcomeOddBuilder(this);
    }

    public ArrayList buildList() {
        return listOfOutcomeOdds;
    }

}
