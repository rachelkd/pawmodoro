package interface_adapter.adoption;

/**
 * The state for the Adoption View Model.
 */
public class AdoptionState {
    private String catName = "";
    private String adoptionError = "";
    private String owner = "";
    private boolean isSuccess = false;

    /**
     * Gets the owner name.
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner's name
     * @param name the name of the owner
     */
    public void setOwner(String name) {
        this.owner = name;
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

    /**
     * Gets whether cat was successfully adopted.
     */
    public boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * Sets whether cat was successfully adopted.
     * @param isSuccess
     */
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
