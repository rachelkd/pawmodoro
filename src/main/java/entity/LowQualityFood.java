package entity;

/**
 * Represents dry food items like kibble that provide a moderate hunger boost.
 */
public class LowQualityFood extends AbstractFood {
    private static final int DEFAULT_POINTS = 10;

    /**
     * Creates a new dry food item.
     *
     * @param name
     *            display name (e.g., "Basic Kibble", "Premium Dry Food")
     * @throws IllegalArgumentException
     *             if foodId/name is invalid or quantity is negative
     */
    public LowQualityFood(String name) {
        super(name, DEFAULT_POINTS);
    }

    @Override
    public String getDescription() {
        return "Standard dry food that provides good basic nutrition.";
    }
}
