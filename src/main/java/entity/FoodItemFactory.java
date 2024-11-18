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
        //TODO temporary, need to decide how food will work
        return new WetFood(foodId, name, 0);
    }
}
