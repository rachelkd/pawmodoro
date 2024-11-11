package entity;

/**
 * Represents dry food items like kibble that provide a moderate hunger boost.
 */
public class DryFood extends AbstractFood {
    private static final int DEFAULT_POINTS = 15;

    /**
     * Creates a new dry food item.
     *
     * @param foodId
     *            unique identifier (e.g., "basic_kibble", "premium_dry")
     * @param name
     *            display name (e.g., "Basic Kibble", "Premium Dry Food")
     * @param quantity
     *            the initial quantity
     * @throws IllegalArgumentException
     *             if foodId/name is invalid or quantity is negative
     */
    public DryFood(String foodId, String name, int quantity) {
        super(foodId, name, DEFAULT_POINTS, quantity);
    }

    @Override
    public String getDescription() {
        return "Standard dry food that provides good basic nutrition.";
    }
}
