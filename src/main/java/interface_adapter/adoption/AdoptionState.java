package interface_adapter.adoption;

/**
 * The state for the Adoption View Model.
 */
public class AdoptionState {
    private String catName = "";
    private String adoptionError = "";
    private String owner = "";

    /**
     * Gets the owner object.
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the cat name.
     * @return the cat name
     */
    public String getCatName() {
        return catName;
    }

    /**
     * Sets the cat name.
     * @param catName the cat name to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * Sets the error message.
     * @param error the error message
     */
    public void setAdoptionError(String error) {
        this.adoptionError = error;
    }

    /**
     * Gets the error message.
     * @return The error message
     */
    public String getAdoptionError() {
        return adoptionError;
    }
}
