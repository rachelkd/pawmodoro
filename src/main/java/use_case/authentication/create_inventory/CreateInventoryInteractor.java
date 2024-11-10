package use_case.authentication.create_inventory;

import entity.Inventory;
import entity.InventoryFactory;

/**
 * The Create Inventory Interctor.
 */
public class CreateInventoryInteractor implements  CreateInventoryInputBoundary{
    private final CreateInventoryInventoryDataAccessInterface createInventoryDataAccessObject;
    private final CreateInventoryOutputBoundary createInventoryPresenter;
    private final InventoryFactory inventoryFactory;

    public CreateInventoryInteractor(CreateInventoryInventoryDataAccessInterface createInventoryDataAccessObject,
                                     CreateInventoryOutputBoundary createInventoryPresenter, InventoryFactory inventoryFactory) {
        this.createInventoryPresenter = createInventoryPresenter;
        this.createInventoryDataAccessObject = createInventoryDataAccessObject;
        this.inventoryFactory = inventoryFactory;
    }

    @Override
    public void execute(CreateInventoryInputData createInventoryInputData) {

        Inventory inventory = inventoryFactory.create(createInventoryInputData.getOwnerId());
        createInventoryDataAccessObject.save(inventory);

        final CreateInventoryOutputData createinventoryOutputData =
                new CreateInventoryOutputData(createInventoryInputData.getOwnerId());
        createInventoryPresenter.prepareSuccessView(createinventoryOutputData);
    }
}
