package use_case.food_management.use_item_in_inventory;

import java.util.HashMap;
import java.util.Map;

import entity.AbstractFood;
import entity.Inventory;

/**
 * The Use Item Interactor.
 */
public class UseItemInteractor implements UseItemInputBoundary {
    private final UseItemDataAccessInterface useItemDataAccessObject;
    private final UseItemOutputBoundary useItemPresenter;

    public UseItemInteractor(UseItemDataAccessInterface useItemDataAccessObject,
                             UseItemOutputBoundary useItemOutputBoundary) {
        this.useItemDataAccessObject = useItemDataAccessObject;
        this.useItemPresenter = useItemOutputBoundary;
    }

    @Override
    public void execute(UseItemInputData useItemInputData) {
        boolean isSuccess = false;
        Map<String, AbstractFood> inventoryItems = new HashMap<>();

        // if you can use item
        if (useItemDataAccessObject.canUseItem(useItemInputData.getOwnerId(), useItemInputData.getFoodName())) {

            final int quantity = useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                    .get(useItemInputData.getFoodName()).getQuantity();
            Inventory inventory = useItemDataAccessObject.getInventory(useItemInputData.getOwnerId());

            // if quantity greater than 1
            if (quantity > 1) {
                inventoryItems = inventory.getItems();
                inventoryItems.get(useItemInputData.getFoodName()).setQuantity(quantity - 1);
                inventory.setItems(inventoryItems);

                useItemDataAccessObject.updateInventory(inventory);

                isSuccess = (quantity - 1) == useItemDataAccessObject.getInventory(useItemInputData.getOwnerId())
                        .getItems().get(useItemInputData.getFoodName()).getQuantity();
            }
            else {
                inventoryItems = inventory.getItems();
                inventoryItems.remove(useItemInputData.getFoodName());
                inventory.setItems(inventoryItems);

                useItemDataAccessObject.updateInventory(inventory);

                isSuccess = !(useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                        .containsKey(useItemInputData.getFoodName()));
            }

        }

        final UseItemOutputData useItemOutputData = new UseItemOutputData(isSuccess, inventoryItems);
        useItemPresenter.prepareSuccessView(useItemOutputData);
    }
}
