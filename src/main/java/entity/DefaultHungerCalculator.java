package entity;

import constants.Constants;

/**
 * Implementation of HungerCalculator.
 */
public class DefaultHungerCalculator implements HungerCalculator {

    @Override
    public int calculateHungerPoints(int studySessionLength) {
        final int hungerPoints;
        if (studySessionLength <= Constants.MINUTES_20) {
            hungerPoints = Constants.HUNGER_FOR_LESS_EQUAL_20;
        }
        else if (studySessionLength <= Constants.MINUTES_40) {
            hungerPoints = Constants.HUNGER_FOR_BETWEEN_21_AND_40;
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            hungerPoints = Constants.HUNGER_FOR_BETWEEN_41_AND_59;
        }
        else {
            hungerPoints = Constants.HUNGER_FOR_60;
        }
        return hungerPoints;
    }
}
