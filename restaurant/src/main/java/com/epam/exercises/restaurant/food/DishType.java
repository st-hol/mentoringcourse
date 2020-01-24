package com.epam.exercises.restaurant.food;

public enum DishType {
    HOTDOG("HotDog"),
    CHIPS("Chips");

    private String foodName;

    DishType(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }
}
