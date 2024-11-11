package use_case.food_management.add_to_inventory;

import entity.AbstractFood;
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
        // repository of inventories
        this.addToInventoryDataAccessObject = addToInventoryDataAccessObject;
        this.addToInventoryPresenter = addToInventoryOutputBoundary;
        this.foodFactory = foodFactory;
    }

    @Override
    public void execute(AddToInventoryInputData addToInventoryInputData) {
        // assume existing inventory
        boolean isSuccess;

        // item in inventory already
        if (addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                .getItems().containsKey(addToInventoryInputData.getFoodId())) {

            Integer prevQuantity = addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId()).getQuantity();

            addToInventoryDataAccessObject.updateQuantity(addToInventoryInputData.getOwnerId(),
                    addToInventoryInputData.getFoodId(),  prevQuantity + 1 );
            addToInventoryDataAccessObject.save(addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId()));

            isSuccess = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodId()) && addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId()).getQuantity() == prevQuantity + 1;

        } // item not in inventory
        else {
            AbstractFood foodItem = foodFactory.create(addToInventoryInputData.getFoodId(), "temp");
            foodItem.setQuantity(1);

            addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().put(addToInventoryInputData.getFoodId(), foodItem);

            addToInventoryDataAccessObject.save(addToInventoryDataAccessObject
                    .getInventory(addToInventoryInputData.getOwnerId()));

            isSuccess = addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().containsKey(addToInventoryInputData.getFoodId()) &&
                    addToInventoryDataAccessObject.getInventory(addToInventoryInputData.getOwnerId())
                    .getItems().get(addToInventoryInputData.getFoodId()).getQuantity() == 1;
        }

        final AddToInventoryOutputData addToInventoryOutputData =
                new AddToInventoryOutputData(isSuccess);
        addToInventoryPresenter.prepareSuccessView(addToInventoryOutputData);

    }

}
