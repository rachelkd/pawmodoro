package use_case.adoption;

/**
 * Output data for the adoption use case
 */
public class AdoptionOutputData {
    private final String catName;
    private final boolean useCaseFailed;
    private final String ownerName;

    public AdoptionOutputData(String catName, boolean useCaseFailed, String ownerName) {
        this.catName = catName;
        this.useCaseFailed = useCaseFailed;
        this.ownerName = ownerName;
    }

    public String getCatName() {
        return catName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getOwnerName() { return ownerName; }
}
