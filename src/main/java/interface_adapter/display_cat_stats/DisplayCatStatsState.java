package interface_adapter.display_cat_stats;

/**
 * State for the display cat statistics view.
 */
public class DisplayCatStatsState {
    private String catName = "";
    private int hungerLevel;
    private int happinessLevel;
    private String imageFileName;
    private String error;

    public DisplayCatStatsState(DisplayCatStatsState copy) {
        catName = copy.catName;
        hungerLevel = copy.hungerLevel;
        happinessLevel = copy.happinessLevel;
        imageFileName = copy.imageFileName;
        error = copy.error;
    }

    public DisplayCatStatsState() {

    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }

    public void setHappinessLevel(int happinessLevel) {
        this.happinessLevel = happinessLevel;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {

        this.error = error;
    }
}
