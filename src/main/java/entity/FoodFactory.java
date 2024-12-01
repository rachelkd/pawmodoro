package entity;

/**
 * Representation of the Food Factory.
 */
public interface FoodFactory {

    /**
     * Creates a new food item based on food name.
     * @param name the name of the food item
     * @return returns a new food item
     */
    AbstractFood create(String name);

    /**
     * Creates a new food item based on study session length.
     * @param studySessionLength length of the study session
     * @return returns a new food item
     */
    AbstractFood create(int studySessionLength);
}
