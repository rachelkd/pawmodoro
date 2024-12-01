package entity;

/**
 * Representation of Hunger Calculator in out program.
 */
public interface HungerCalculator {

    /**
     * Calculates the cat hunger points the user gets when they complete a study session or feed a cat.
     * @param studySessionLength the length of the study session user completed
     * @return the cat happiness points for the session
     */
    int calculateHungerPoints(int studySessionLength);
}
