package entity;

/**
 * Factory for building Abstract FoodItem objects
 */
public class FoodItemFactory {

    public AbstractFoodItem create (String foodId, String name) {
        // temporary, need to decide ids
        return new WetFood(foodId, name, 0);
    }
}
