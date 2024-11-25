package entity;

/**
 * Factory for building Abstract FoodItem objects.
 */
public class FoodItemFactory {
    // 4 food options
    private static final String MILK = "Milk";
    private static final String CHEESE = "Cheese";
    private static final String TUNA = "Tuna";
    private static final String TREAT = "Treat";

    /**
     * Creates a new food item.
     * @param name the name of the food item
     * @return returns a new food item
     */
    public AbstractFood create(String name) {
        if (name.equalsIgnoreCase(MILK)) {
            return new LowQualityFood(MILK, 1);
        }
        else if (name.equalsIgnoreCase(CHEESE)) {
            return new LowQualityFood(CHEESE, 1);
        }
        else if (name.equalsIgnoreCase(TUNA)) {
            return new WetFood(TUNA, 1);
        }
        else if (name.equalsIgnoreCase(TREAT)) {
            return new TreatFood(TREAT, 1);
        }
        return null;
    }
}
