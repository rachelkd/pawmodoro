package interface_adapter.cat;

/**
 * The state for the Cat View Model.
 */
public class CatState {
    private String imageFileName;
    private String catName;
    private String ownerUsername;
    private int hungerLevel;
    private int happinessLevel;
    private String error;

    // Copy constructor
    public CatState(CatState copy) {
        imageFileName = copy.imageFileName;
        catName = copy.catName;
        ownerUsername = copy.ownerUsername;
        hungerLevel = copy.hungerLevel;
        happinessLevel = copy.happinessLevel;
        error = copy.error;
    }

    public CatState() {
        // Default constructor
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }

    public void setHappinessLevel(int happinessLevel) {
        this.happinessLevel = happinessLevel;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
