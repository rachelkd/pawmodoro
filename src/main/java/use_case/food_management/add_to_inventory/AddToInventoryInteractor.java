package use_case.food_management.add_to_inventory;

import entity.AbstractFoodItem;
import entity.FoodItemFactory;
import entity.InventoryFactory;

/**
 * The Add To Inventory Interactor
 */
public class AddToInventoryInteractor implements AddToInventoryInputBoundary{
    private final AddToInventoryDataAccessInterface addToInventoryDataAccessObject;
    private final AddToInventoryOutputBoundary addToInventoryPresenter;
    private final FoodItemFactory foodFactory;

    public AddToInventoryInteractor(AddToInventoryDataAccessInterface addToInventoryDataAccessObject,
                                    AddToInventoryOutputBoundary addToInventoryOutputBoundary,
                                    FoodItemFactory foodFactory) {
        this.addToInventoryDataAccessObject = addToInventoryDataAccessObject;
        this.addToInventoryPresenter = addToInventoryOutputBoundary;
        this.foodFactory = foodFactory;
    }

    @Override
    public void execute(AddToInventoryInputData addToInventoryInputData) {
        // assume existing inventory

        // item in inventory already
        if (addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                .getItems().containsKey(addToInventoryInputData.getFoodId())) {

            addToInventoryDataAccessObject.updateQuantity(addToInventoryInputData.getOwnerId(),
                    addToInventoryInputData.getFoodId(), addToInventoryDataAccessObject
                            .getInventory(addToInventoryInputData.getOwnerId())
                            .getItems().get(addToInventoryInputData.getFoodId()).getQuantity() + 1 );
            addToInventoryDataAccessObject.save(addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId()));

        } // item not in inventory
        else {
            AbstractFoodItem foodItem = foodFactory.create(addToInventoryInputData.getFoodId(), "temp");
            addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().put(addToInventoryInputData.getFoodId(), foodItem);
            addToInventoryDataAccessObject.save(addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId()));
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(addToInventoryInputData.getOwnerId(), addToInventoryInputData.getFoodId());
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);

    }

}
