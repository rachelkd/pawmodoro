package entity;

/**
 * Represents wet food items like canned food that provide a high hunger boost.
 */
public class WetFood extends AbstractFood {
    private static final int DEFAULT_POINTS = 20;

    /**
     * Creates a new wet food item.
     * @param name display name (e.g., "Tuna Can", "Salmon Paste")
     * @throws IllegalArgumentException if foodId/name is invalid or quantity is negative
     */
    public WetFood(String name) {
        super(name, DEFAULT_POINTS);
    }

    @Override
    public String getDescription() {
        return "Premium wet food that provides excellent nutrition.";
    }
}
