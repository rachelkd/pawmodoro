package entity;

/**
 * Represents a treat food item that provides a small hunger boost.
 */
public class TreatFood extends AbstractFood {
    private static final int DEFAULT_POINTS = 30;

    /**
     * Creates a new treat food item.
     *
     * @param name
     *            the unique name of this treat (e.g., "cookie", "snack")
     * @throws IllegalArgumentException
     *             if name is null/empty or quantity is negative
     */
    public TreatFood(String name) {
        super(name, DEFAULT_POINTS);
    }

    @Override
    public String getDescription() {
        return "A small treat that provides a minor hunger boost.";
    }
}
