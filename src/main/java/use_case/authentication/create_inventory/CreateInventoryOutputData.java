package use_case.authentication.create_inventory;

public class CreateInventoryOutputData {
    private final String ownerId;

    public CreateInventoryOutputData(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {return ownerId;}
}
