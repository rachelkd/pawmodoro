package interface_adapter.runawaycat;

public class RunawayCatState {
    private String catName = "";
    private String ownerName = "";


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
