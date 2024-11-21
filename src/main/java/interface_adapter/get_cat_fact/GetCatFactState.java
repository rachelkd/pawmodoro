package interface_adapter.get_cat_fact;

/**
 * State for the cat fact view.
 */
public class GetCatFactState {
    private String catFact = "";
    private String error;

    public GetCatFactState(GetCatFactState copy) {
        catFact = copy.catFact;
        error = copy.error;
    }

    public GetCatFactState() {
        // Default constructor
    }

    public String getCatFact() {
        return catFact;
    }

    public void setCatFact(String catFact) {
        this.catFact = catFact;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
