package use_case.authentication.create_inventory;

import entity.AbstractFood;
import entity.Inventory;
import entity.InventoryFactory;

import java.util.Map;

/**
 * The Create Inventory Interctor.
 */
public class CreateInventoryInteractor implements CreateInventoryInputBoundary {
    private final CreateInventoryInventoryDataAccessInterface createInventoryDataAccessObject;
    private final CreateInventoryOutputBoundary createInventoryPresenter;
    private final InventoryFactory inventoryFactory;

    public CreateInventoryInteractor(CreateInventoryInventoryDataAccessInterface createInventoryDataAccessObject,
                                     CreateInventoryOutputBoundary createInventoryPresenter,
                                     InventoryFactory inventoryFactory) {
        this.createInventoryPresenter = createInventoryPresenter;
        this.createInventoryDataAccessObject = createInventoryDataAccessObject;
        this.inventoryFactory = inventoryFactory;
    }

    @Override
    public void execute(CreateInventoryInputData createInventoryInputData) {

        final Inventory inventory = inventoryFactory.create(createInventoryInputData.getOwnerId());

        // if inventory in memory
        if (createInventoryDataAccessObject.existsByOwnerId(createInventoryInputData.getOwnerId())) {
            Map<String, AbstractFood> items = createInventoryDataAccessObject.getInventoryItems(createInventoryInputData.getOwnerId());
            inventory.setItems(items);
        }

        createInventoryDataAccessObject.save(inventory);

        final boolean isSuccess = createInventoryDataAccessObject.existsByOwnerId(inventory.getOwnerId())
                && inventory.getItems().equals(createInventoryDataAccessObject.getInventoryItems(inventory.getOwnerId()));

        final CreateInventoryOutputData createinventoryOutputData =
                new CreateInventoryOutputData(inventory.getOwnerId(), isSuccess, inventory.getItems());
        createInventoryPresenter.prepareSuccessView(createinventoryOutputData);
    }
}
