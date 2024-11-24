package use_case.food_management.use_item_in_inventory;

import java.util.HashMap;
import java.util.Map;

import entity.AbstractFood;
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
        boolean isSuccess = false;
        Map<String, AbstractFood> inventoryItems = new HashMap<>();

        // if you can use item
        if (inventoryDataAccessObject.canUseItem(useItemInputData.getOwnerId(), useItemInputData.getFoodName())) {

            final int quantity = inventoryDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                    .get(useItemInputData.getFoodName()).getQuantity();
            Inventory inventory = inventoryDataAccessObject.getInventory(useItemInputData.getOwnerId());

            // if quantity greater than 1
            if (quantity > 1) {
                inventoryItems = inventory.getItems();
                inventoryItems.get(useItemInputData.getFoodName()).setQuantity(quantity - 1);
                inventory.setItems(inventoryItems);

                inventoryDataAccessObject.updateInventory(inventory);

                isSuccess = (quantity - 1) == inventoryDataAccessObject.getInventory(useItemInputData.getOwnerId())
                        .getItems().get(useItemInputData.getFoodName()).getQuantity();
            }
            else {
                inventoryItems = inventory.getItems();
                // when testing need to use capitals
                inventoryItems.remove(useItemInputData.getFoodName());
                inventory.setItems(inventoryItems);

                inventoryDataAccessObject.updateInventory(inventory);

                isSuccess = !(inventoryDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                        .containsKey(useItemInputData.getFoodName()));
            }

        }

        final UseItemOutputData useItemOutputData = new UseItemOutputData(isSuccess, inventoryItems);
        useItemPresenter.prepareSuccessView(useItemOutputData);
    }
}
