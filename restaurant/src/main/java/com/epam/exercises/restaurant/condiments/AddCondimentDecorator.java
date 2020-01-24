package com.epam.exercises.restaurant.condiments;

import com.epam.exercises.restaurant.exception.UnknownHappinessIncrementException;
import com.epam.exercises.restaurant.food.Dish;

public class AddCondimentDecorator extends DishDecorator {
    private static final String UNKNOWN_HAPPINESS_MESSAGE = "There is no happiness for this product";

    protected CondimentType condimentType;

    public AddCondimentDecorator(Dish decoratedDish, CondimentType condimentType) {
        super(decoratedDish);
        this.condimentType = condimentType;
        this.description = decoratedDish.getDescription() + " " + condimentType.getCondimentName();
    }

    /**
     * It seems like violation of Single Responsibility Principle
     * <p>
     * Considering refactoring plan to divide into: AddKetchupDecorator, AddMustardDecorator
     */
    @Override
    public double happinessIncrement(double currentHappiness) {
        switch (condimentType) {
            case KETCHUP:
                return decoratedDish.happinessIncrement(currentHappiness) * 2;
            case MUSTARD:
                return 1;
            default:
                throw new UnknownHappinessIncrementException(UNKNOWN_HAPPINESS_MESSAGE);
        }
    }
}
