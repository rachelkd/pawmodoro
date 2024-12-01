package entity;

import use_case.food_management.add_to_inventory.FoodMappingService;

/**
 * Factory for building Abstract FoodItem objects.
 */
public class FoodItemFactory implements FoodFactory {
    // 4 food options
    private static final String MILK = "Milk";
    private static final String CHEESE = "Cheese";
    private static final String TUNA = "Tuna";
    private static final String TREAT = "Treat";

    private final FoodMappingService foodMappingService = new FoodMappingService();

    @Override
    public AbstractFood create(String name) {
        return getFoodItem(name);
    }

    @Override
    public AbstractFood create(int studySessionLength) {
        return getFoodItem(foodMappingService.getFoodName(studySessionLength));
    }

    AbstractFood getFoodItem(String foodName) {
        AbstractFood food = null;

        if (foodName.equalsIgnoreCase(MILK)) {
            food = new LowQualityFood(MILK, 1);
        }
        else if (foodName.equalsIgnoreCase(CHEESE)) {
            food = new LowQualityFood(CHEESE, 1);
        }
        else if (foodName.equalsIgnoreCase(TUNA)) {
            food = new WetFood(TUNA, 1);
        }
        else if (foodName.equalsIgnoreCase(TREAT)) {
            food = new TreatFood(TREAT, 1);
        }
        return food;
    }
}
