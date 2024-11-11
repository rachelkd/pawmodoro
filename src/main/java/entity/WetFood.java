package entity;

/**
 * Represents wet food items like canned food that provide a high hunger boost.
 */
public class WetFood extends AbstractFood {
    private static final int DEFAULT_POINTS = 25;

    /**
     * Creates a new wet food item.
     *
     * @param foodId
     *            unique identifier (e.g., "tuna_can", "salmon_paste")
     * @param name
     *            display name (e.g., "Tuna Can", "Salmon Paste")
     * @param quantity
     *            the initial quantity
     * @throws IllegalArgumentException
     *             if foodId/name is invalid or quantity is negative
     */
    public WetFood(String foodId, String name, int quantity) {
        super(foodId, name, DEFAULT_POINTS, quantity);
    }

    @Override
    public String getDescription() {
        return "Premium wet food that provides excellent nutrition.";
    }
}
