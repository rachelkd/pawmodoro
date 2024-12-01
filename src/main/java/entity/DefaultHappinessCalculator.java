package entity;

import constants.Constants;

/**
 * Implementation of HappinessCalculator.
 */
public class DefaultHappinessCalculator implements HappinessCalculator {

    @Override
    public int calculateHappinessPoints(int studySessionLength) {
        final int happinessPoints;
        if (studySessionLength <= Constants.MINUTES_20) {
            happinessPoints = Constants.POINTS_FOR_LESS_EQUAL_20;
        }
        else if (studySessionLength <= Constants.MINUTES_40) {
            happinessPoints = Constants.POINTS_FOR_BETWEEN_21_AND_40;
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            happinessPoints = Constants.POINTS_FOR_BETWEEN_41_AND_59;
        }
        else {
            happinessPoints = Constants.POINTS_FOR_60;
        }
        return happinessPoints;
    }
}
