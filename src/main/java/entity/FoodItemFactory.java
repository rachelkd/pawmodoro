package entity;

/**
 * Factory for building Abstract FoodItem objects.
 */
public class FoodItemFactory {

    /**
     * Creates a new food item.
     * @param foodId the food id
     * @param name the name of the food item
     * @return returns a new food item
     */
    public AbstractFood create(String foodId, String name) {
        // temporary, need to decide ids
        return new WetFood(foodId, name, 0);
    }
}
