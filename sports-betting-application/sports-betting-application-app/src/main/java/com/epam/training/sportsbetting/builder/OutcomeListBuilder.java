
package com.epam.training.sportsbetting.builder;

import com.epam.training.sportsbetting.domain.Outcome;

import java.util.ArrayList;


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
