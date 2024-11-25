package use_case.get_cat_fact;

/**
 * Output data class for the cat fact retrieval use case.
 * Contains the retrieved cat fact or error information.
 */
public class GetCatFactOutputData {
    private final String catFact;
    private final boolean useCaseFailed;
    private final String errorMessage;

    public GetCatFactOutputData(String catFact, boolean useCaseFailed, String errorMessage) {
        this.catFact = catFact;
        this.useCaseFailed = useCaseFailed;
        this.errorMessage = errorMessage;
    }

    public String getCatFact() {
        return catFact;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
