package entity;

import java.util.Random;

/**
 * Represents a virtual pet cat in the Pawmodoro application.
 * Each cat has a unique name within its owner's CatHouse.
 * The cat's well-being is measured through hunger and happiness levels,
 * which are affected by the owner's study habits and feeding patterns.
 */
public class Cat {
    private static final int INITIAL_LEVEL = 100;
    private static final int MAX_LEVEL = 100;
    private static final int MIN_LEVEL = 0;
    private static final int NUMBER_OF_CAT_IMAGES = 5;
    private static final String CAT_IMAGE_FORMAT = "cat-%d.png";

    private final String name;
    private final String ownerUsername;
    private int hungerLevel;
    private int happinessLevel;
    private Position position;
    private final String imageFileName;
    private boolean catObjectCreated;

    /**
     * Creates a new Cat with the specified name and owner.
     * Note: The uniqueness of the cat's name within the owner's CatHouse
     * must be validated before creating a new Cat instance.
     *
     * @param name The unique name of the cat
     * @param ownerUsername The username of the cat's owner
     * @throws IllegalArgumentException if name is null or empty
     */
    public Cat(String name, String ownerUsername) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Cat name cannot be null or empty");
        }
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.hungerLevel = INITIAL_LEVEL;
        this.happinessLevel = INITIAL_LEVEL;
        this.position = new Position(0, 0);

        // Randomly select a cat image
        final Random random = new Random();
        final int imageNumber = random.nextInt(NUMBER_OF_CAT_IMAGES) + 1;
        this.imageFileName = String.format(CAT_IMAGE_FORMAT, imageNumber);
    }

    /**
     * Updates the cat's hunger level by the specified amount.
     * The level is constrained between 0 and 100.
     *
     * @param amount the amount to change the hunger level by (positive or negative)
     */
    public void updateHungerLevel(int amount) {
        this.hungerLevel = Math.max(MIN_LEVEL, Math.min(MAX_LEVEL, this.hungerLevel + amount));
    }

    /**
     * Updates the cat's happiness level by the specified amount.
     * The level is constrained between 0 and 100.
     *
     * @param amount the amount to change the happiness level by (positive or negative)
     */
    public void updateHappinessLevel(int amount) {
        this.happinessLevel = Math.max(MIN_LEVEL, Math.min(MAX_LEVEL, this.happinessLevel + amount));
    }

    /**
     * Updates the cat's position for animation purposes.
     *
     * @param newPosition The new position for the cat
     */
    public void move(Position newPosition) {
        this.position = newPosition;
    }

    /**
     * Feeds the cat with the specified food item, affecting both hunger and happiness levels.
     *
     * @param food the food item to feed to the cat
     */
    public void feed(AbstractFood food) {
        this.updateHungerLevel(food.getPoints());
    }

    public String getName() {
        return name;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }

    public Position getPosition() {
        return position;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public boolean isCatObjectCreated() {
        return catObjectCreated;
    }

    public void setCatObjectCreated(boolean catObjectCreated) {
        this.catObjectCreated = catObjectCreated;
    }
}
