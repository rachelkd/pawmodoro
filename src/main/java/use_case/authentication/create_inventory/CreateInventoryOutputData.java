package use_case.authentication.create_inventory;

public class CreateInventoryOutputData {
    private final boolean success;

    public CreateInventoryOutputData(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {return success;}
}
