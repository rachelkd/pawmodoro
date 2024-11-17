package use_case.adoption;

/**
 * Output data for the adoption use case
 */
public class AdoptionOutputData {
    private final String catName;
    private final boolean useCaseFailed;

    public AdoptionOutputData(String catName, boolean useCaseFailed) {
        this.catName = catName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getCatName() {
        return catName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
