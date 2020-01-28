
package com.epam.training.sportsbetting.builder;

import java.util.ArrayList;

import com.epam.training.sportsbetting.domain.Outcome;


public class OutcomeListBuilder {

    private ArrayList listOfOutcomes;

    public OutcomeListBuilder addList() {
        this.listOfOutcomes = new ArrayList();
        return this;
    }

    public OutcomeListBuilder addOutcome(Outcome outcome) {
        listOfOutcomes.add(outcome);
        return this;
    }

    public OutcomeBuilder addOutcome() {
        return new OutcomeBuilder(this);
    }

    public ArrayList buildList() {
        return listOfOutcomes;
    }

}
