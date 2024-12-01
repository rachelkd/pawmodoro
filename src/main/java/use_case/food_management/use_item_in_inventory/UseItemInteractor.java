package use_case.food_management.use_item_in_inventory;

import java.util.Map;

import entity.Inventory;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * The Use Item Interactor.
 */
public class UseItemInteractor implements UseItemInputBoundary {
    private final InventoryDataAccessInterface inventoryDataAccessObject;
    private final UseItemOutputBoundary useItemPresenter;

    public UseItemInteractor(InventoryDataAccessInterface useItemDataAccessObject,
                             UseItemOutputBoundary useItemOutputBoundary) {
        this.inventoryDataAccessObject = useItemDataAccessObject;
        this.useItemPresenter = useItemOutputBoundary;
    }

    @Override
    public void execute(UseItemInputData useItemInputData) {
        Map<String, Integer> inventoryItems =
                inventoryDataAccessObject.getInventoryItems(useItemInputData.getOwnerId());

        // if you can use item
        if (inventoryDataAccessObject.canUseItem(useItemInputData.getOwnerId(), useItemInputData.getFoodName())) {

            final Inventory inventory = inventoryDataAccessObject.getInventory(useItemInputData.getOwnerId());
            final int quantity = inventory.getItems().get(useItemInputData.getFoodName());
            inventoryItems = inventory.getItems();

            // if quantity greater than 1
            if (quantity > 1) {
                inventoryItems.put(useItemInputData.getFoodName(), quantity - 1);
            }
            else {
                inventoryItems.remove(useItemInputData.getFoodName());
            }
            inventory.setItems(inventoryItems);
            inventoryDataAccessObject.updateInventory(inventory);

        }
        final UseItemOutputData useItemOutputData =
                new UseItemOutputData(inventoryItems, useItemInputData.getOwnerId());
        useItemPresenter.prepareSuccessView(useItemOutputData);
    }
}
