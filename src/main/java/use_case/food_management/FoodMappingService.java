package use_case.food_management;

import constants.Constants;

/**
 * Determines the food name based on the study session length.
 */
public class FoodMappingService {

    /**
     * Returns the rewarded food item based on the study time.
     * @param studySessionLength the length of the study session
     * @return the food name
     */
    public String getFoodName(int studySessionLength) {
        final String foodName;

        if (studySessionLength < Constants.MINUTES_20) {
            foodName = "Milk";
        }
        else if (studySessionLength < Constants.MINUTES_40) {
            foodName = "Cheese";
        }
        else if (studySessionLength < Constants.MINUTES_60) {
            foodName = "Tuna";
        }
        else {
            foodName = "Treat";
        }
        return foodName;
    }
}
