package interface_adapter.cat;

/**
 * The state for the Cat View Model.
 */
public class CatState {
    private String imageFileName;
    private String catName;

    // Copy constructor
    public CatState(CatState copy) {
        imageFileName = copy.imageFileName;
        catName = copy.catName;
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
}
