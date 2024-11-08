package entity;

/**
 * Abstract class representing a food item in the inventory.
 * Different types of food can extend this class to provide specific
 * implementations.
 */
public abstract class AbstractFoodItem {
    // Unique identifier (e.g., "tuna_can", "dry_kibble")
    private final String foodId;
    // Display name (e.g., "Tuna Can", "Dry Kibble")
    private final String name;
    private final int points;
    private int quantity;

    /**
     * Creates a new food item.
     *
     * @param foodId
     *            unique identifier with no spaces (e.g., "tuna_can")
     * @param name
     *            display name that can contain spaces (e.g., "Tuna Can")
     * @param points
     *            the number of hunger points this food restores
     * @param quantity
     *            the initial quantity
     * @throws IllegalArgumentException
     *             if:
     *             - foodId is null, empty, or contains spaces
     *             - name is null or empty
     *             - points is negative
     *             - quantity is negative
     */
    protected AbstractFoodItem(String foodId, String name, int points, int quantity) {
        if (foodId == null || foodId.trim().isEmpty() || foodId.contains(" ")) {
            throw new IllegalArgumentException("Food ID cannot be null, empty, or contain spaces");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Food name cannot be null or empty");
        }
        if (points < 0) {
            throw new IllegalArgumentException("Food points cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Food quantity cannot be negative");
        }

        this.foodId = foodId;
        this.name = name;
        this.points = points;
        this.quantity = quantity;
    }

    /**
     * Gets the unique identifier of this food type.
     *
     * @return the food ID (e.g., "tuna_can")
     */
    public String getFoodId() {
        return foodId;
    }

    /**
     * Gets the display name of this food type.
     *
     * @return the food name (e.g., "Tuna Can")
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of hunger points this food restores.
     *
     * @return the hunger points value
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets the current quantity of this food item.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of this food item.
     *
     * @param quantity
     *            the new quantity
     * @throws IllegalArgumentException
     *             if quantity is negative
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Food quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    /**
     * Abstract method to get the description of this food item.
     * Each food type should provide its own description.
     *
     * @return a description of the food item
     */
    public abstract String getDescription();
}
