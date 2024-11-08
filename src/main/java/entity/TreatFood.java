package entity;

/**
 * Represents a treat food item that provides a small hunger boost.
 */
public class TreatFood extends FoodItem {
    private static final int DEFAULT_POINTS = 10;

    /**
     * Creates a new treat food item.
     *
     * @param name
     *            the unique name of this treat (e.g., "cookie", "snack")
     * @param quantity
     *            the initial quantity
     * @throws IllegalArgumentException
     *             if name is null/empty or quantity is negative
     */
    public TreatFood(String name, int quantity) {
        super(name, DEFAULT_POINTS, quantity);
    }

    @Override
    public String getDescription() {
        return "A small treat that provides a minor hunger boost.";
    }
}
