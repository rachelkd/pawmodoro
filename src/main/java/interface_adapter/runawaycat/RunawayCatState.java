package interface_adapter.runawaycat;

/**
 * The state for the Runaway Cat View Model
 */
public class RunawayCatState {
    private String catName = "";
    
    public RunawayCatState(RunawayCatState copy) {
        catName = copy.catName;
    }

    public RunawayCatState() {}

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
} 