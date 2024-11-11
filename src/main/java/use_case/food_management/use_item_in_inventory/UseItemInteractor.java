package use_case.food_management.use_item_in_inventory;

import entity.FoodItemFactory;

/**
 * The Use Item Interactor.
 */
public class UseItemInteractor implements UseItemInputBoundary{
    private final UseItemDataAccessInterface useItemDataAccessObject;
    private final UseItemOutputBoundary useItemPresenter;


    public UseItemInteractor(UseItemDataAccessInterface useItemDataAccessObject, UseItemOutputBoundary useItemOutputBoundary) {
        this.useItemDataAccessObject = useItemDataAccessObject;
        this.useItemPresenter = useItemOutputBoundary;
    }

    @Override
    public void execute(UseItemInputData useItemInputData) {
        boolean isSuccess = false;

        // if you can use item
        if (useItemDataAccessObject.canUseItem(useItemInputData.getOwnerId(), useItemInputData.getFoodId())) {
            Integer quantity = useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                    .get(useItemInputData.getFoodId()).getQuantity();
            // if quantity greater than 1
            if (quantity > 1) {
                useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                        .get(useItemInputData.getFoodId()).setQuantity(quantity - 1);
                isSuccess = (quantity - 1) == useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems()
                        .get(useItemInputData.getFoodId()).getQuantity();
            } else {
                useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems().remove(useItemInputData.getFoodId());
                isSuccess = !(useItemDataAccessObject.getInventory(useItemInputData.getOwnerId()).getItems().containsKey(useItemInputData.getFoodId()));
            }
        }

        final UseItemOutputData useItemOutputData = new UseItemOutputData(isSuccess);
        useItemPresenter.prepareSuccessView(useItemOutputData);
    }
}
