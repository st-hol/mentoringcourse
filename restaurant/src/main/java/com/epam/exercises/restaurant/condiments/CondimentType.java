package com.epam.exercises.restaurant.condiments;

public enum CondimentType {
    KETCHUP("Ketchup"),
    MUSTARD("Mustard");

    private String condimentName;

    CondimentType(String condimentName) {
        this.condimentName = condimentName;
    }

    public String getCondimentName() {
        return condimentName;
    }

}
