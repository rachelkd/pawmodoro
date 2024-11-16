package use_case.food_management.use_item_in_inventory;

import java.util.HashMap;
import java.util.Map;

import entity.AbstractFood;

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
        if (useItemDataAccessObject.canUseItem(useItemInputData.getOwnerId(), useItemInputData.getFoodId())) {
            final Integer quantity = useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                    .get(useItemInputData.getFoodId()).getQuantity();
            // if quantity greater than 1
            if (quantity > 1) {
                inventoryItems = useItemDataAccessObject.getInventory(useItemInputData
                        .getOwnerId()).getItems();
                inventoryItems.get(useItemInputData.getFoodId()).setQuantity(quantity - 1);
                useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).setItems(inventoryItems);
                isSuccess = (quantity - 1) == useItemDataAccessObject.getInventory(useItemInputData.getOwnerId())
                        .getItems().get(useItemInputData.getFoodId()).getQuantity();
            }
            else {
                inventoryItems = useItemDataAccessObject.getInventory(useItemInputData
                        .getOwnerId()).getItems();
                inventoryItems.remove(useItemInputData.getFoodId());
                useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).setItems(inventoryItems);

                isSuccess = !(useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                        .containsKey(useItemInputData.getFoodId()));
            }
        }

        final UseItemOutputData useItemOutputData = new UseItemOutputData(isSuccess, inventoryItems);
        useItemPresenter.prepareSuccessView(useItemOutputData);
    }
}
