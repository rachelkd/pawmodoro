package entity;

/**
 * Abstract class representing a food item in the inventory.
 * Different types of food can extend this class to provide specific
 * implementations.
 */
public abstract class AbstractFood {
    // Display name (e.g., "Tuna Can", "Dry Kibble")
    private final String name;
    private final int points;

    /**
     * Creates a new food item.
     *
     * @param name
     *            display name that can contain spaces (e.g., "Tuna Can")
     * @param points
     *            the number of hunger points this food restores
     * @throws IllegalArgumentException
     *             if:
     *             - name is null or empty
     *             - points is negative
     *             - quantity is negative
     */
    protected AbstractFood(String name, int points) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Food name cannot be null or empty");
        }
        if (points < 0) {
            throw new IllegalArgumentException("Food points cannot be negative");
        }

        this.name = name;
        this.points = points;
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
     * Abstract method to get the description of this food item.
     * Each food type should provide its own description.
     *
     * @return a description of the food item
     */
    public abstract String getDescription();
}
