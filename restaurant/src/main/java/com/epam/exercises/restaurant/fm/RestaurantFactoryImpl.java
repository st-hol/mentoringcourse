package com.epam.exercises.restaurant.fm;


import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.exception.NotExistingDishException;
import com.epam.exercises.restaurant.food.Chips;
import com.epam.exercises.restaurant.food.Dish;
import com.epam.exercises.restaurant.food.DishType;
import com.epam.exercises.restaurant.food.HotDog;
import com.epam.exercises.restaurant.service.FoodService;

public class RestaurantFactoryImpl extends RestaurantFactory {

    private static final String DISH_NOT_EXIST_MESSAGE = "There is no such dish";

    private FoodService foodService = new FoodService();

    public Dish makeDish(Order order) {
        Dish food = createBase(order.getDishType());
        food = foodService.decorateWithCondiments(food, order.getCondimentTypes());
        System.out.println("Factory: Preparing " + food.getDescription());
        return food;
    }

    private Dish createBase(final DishType dishType) {
        switch (dishType) {
            case HOTDOG:
                return new HotDog();
            case CHIPS:
                return new Chips();
            default:
                throw new NotExistingDishException(DISH_NOT_EXIST_MESSAGE);
        }
    }

}
