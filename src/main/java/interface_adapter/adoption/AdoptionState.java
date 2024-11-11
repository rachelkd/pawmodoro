package interface_adapter.adoption;

/**
 * The state for the Adoption View Model.
 */
public class AdoptionState {
    private String catName = "";

    /**
     * Gets the cat name.
     * 
     * @return the cat name
     */
    public String getCatName() {
        return catName;
    }

    /**
     * Sets the cat name.
     * 
     * @param catName the cat name to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }
}
