package use_case.display_cat_stats;

/**
 * Output data containing cat statistics.
 */
public class DisplayCatStatsOutputData {
    private final String catName;
    private final int hungerLevel;
    private final int happinessLevel;
    private final String imageFileName;
    private final boolean useCaseFailed;
    private final String error;

    /**
     * Creates output data for a successful use case.
     *
     * @param catName the name of the cat
     * @param hungerLevel the hunger level of the cat
     * @param happinessLevel the happiness level of the cat
     * @param imageFileName the image file name for the cat
     */
    public DisplayCatStatsOutputData(String catName, int hungerLevel, int happinessLevel, String imageFileName) {
        this.catName = catName;
        this.hungerLevel = hungerLevel;
        this.happinessLevel = happinessLevel;
        this.imageFileName = imageFileName;
        this.useCaseFailed = false;
        this.error = null;
    }

    /**
     * Creates output data for a failed use case.
     *
     * @param error the error message
     */
    public DisplayCatStatsOutputData(String error) {
        this.catName = "";
        this.hungerLevel = 0;
        this.happinessLevel = 0;
        this.imageFileName = "";
        this.useCaseFailed = true;
        this.error = error;
    }

    public String getCatName() {
        return catName;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getError() {
        return error;
    }
}
