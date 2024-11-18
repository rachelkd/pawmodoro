package use_case.display_cat_stats;

/**
 * Input data for displaying cat statistics.
 */
public class DisplayCatStatsInputData {
    private final String username;
    private final String catName;

    public DisplayCatStatsInputData(String username, String catName) {
        this.username = username;
        this.catName = catName;
    }

    public String getUsername() {
        return username;
    }

    public String getCatName() {
        return catName;
    }
}
