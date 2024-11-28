package use_case.cat_management.initialize_cats;

public class InitializeCatsInputData {
    private final String ownerUsername;

    public InitializeCatsInputData(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }
}
