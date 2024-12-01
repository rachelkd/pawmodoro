package use_case.food_management.create_inventory;

import java.util.Map;

import entity.AbstractFood;
import entity.Inventory;
import entity.InventoryFactory;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * The Create Inventory Interctor.
 */
public class CreateInventoryInteractor implements CreateInventoryInputBoundary {
    private final InventoryDataAccessInterface inventoryDataAccessObject;
    private final CreateInventoryOutputBoundary createInventoryPresenter;
    private final InventoryFactory inventoryFactory;

    public CreateInventoryInteractor(InventoryDataAccessInterface inventoryDataAccessObject,
                                     CreateInventoryOutputBoundary createInventoryPresenter,
                                     InventoryFactory inventoryFactory) {
        this.createInventoryPresenter = createInventoryPresenter;
        this.inventoryDataAccessObject = inventoryDataAccessObject;
        this.inventoryFactory = inventoryFactory;
    }

    @Override
    public void execute(CreateInventoryInputData createInventoryInputData) {

        final Inventory inventory = inventoryFactory.create(createInventoryInputData.getOwnerId());

        // if inventory in memory
        if (inventoryDataAccessObject.existsByOwnerId(createInventoryInputData.getOwnerId())) {
            final Map<String, AbstractFood> items = inventoryDataAccessObject.getInventoryItems(
                    createInventoryInputData.getOwnerId());
            inventory.setItems(items);
        }

        inventoryDataAccessObject.save(inventory);

        // ensure inventory was successfully added to the repository
        final boolean isSuccess = inventoryDataAccessObject.existsByOwnerId(inventory.getOwnerId())
                && inventory.getItems().equals(inventoryDataAccessObject.getInventoryItems(inventory
                .getOwnerId()));

        final CreateInventoryOutputData createinventoryOutputData =
                new CreateInventoryOutputData(inventory.getOwnerId(), isSuccess, inventory.getItems());
        createInventoryPresenter.prepareSuccessView(createinventoryOutputData);
    }
}
