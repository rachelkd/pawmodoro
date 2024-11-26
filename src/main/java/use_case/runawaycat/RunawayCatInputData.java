package use_case.runawaycat;

/**
 * The input data for the runaway cat use case.
 */
public class RunawayCatInputData {
    private final String catName;
    private final String ownerName;

    public RunawayCatInputData(String catName, String ownerName) {
        this.catName = catName;
        this.ownerName = ownerName;
    }

    public String getCatName() {
        return catName;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
