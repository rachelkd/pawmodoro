package entity;

/**
 * Representation of Happiness Calculator in our program.
 */
public interface HappinessCalculator {

    /**
     * Calculates the cat happiness points the user gets for completing a study session.
     *
     * @param studySessionLength the length of the study session user completed
     * @return the cat happiness points for the session
     */
    int calculateHappinessPoints(int studySessionLength);
}
