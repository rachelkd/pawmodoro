package app;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import app.service.DialogService;
import data_access.InMemoryInventoryDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.add_to_inventory.AddToInventoryPresenter;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;
import interface_adapter.use_item_in_inventory.UseItemPresenter;
import use_case.food_management.InventoryDataAccessInterface;
import use_case.food_management.create_inventory.CreateInventoryInputBoundary;
import use_case.food_management.create_inventory.CreateInventoryInteractor;
import use_case.food_management.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.food_management.create_inventory.CreateInventoryOutputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryDataAccessInterface;
import use_case.food_management.add_to_inventory.AddToInventoryInputBoundary;
import use_case.food_management.add_to_inventory.AddToInventoryInteractor;
import use_case.food_management.add_to_inventory.AddToInventoryOutputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemDataAccessInterface;
import use_case.food_management.use_item_in_inventory.UseItemInputBoundary;
import use_case.food_management.use_item_in_inventory.UseItemInteractor;
import use_case.food_management.use_item_in_inventory.UseItemOutputBoundary;
import view.InventoryView;
import view.ViewManager;

// for showing on monday

/**
 * Show the inventory view with an example.
 */
public class InventoryExample {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private InventoryView inventoryView;
    private InventoryViewModel inventoryViewModel = new InventoryViewModel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    /**
     * Builds and runs inventory view in app.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final InventoryExample inventoryExample = new InventoryExample();
        JFrame pawmodoro = inventoryExample.buildExistingInventory();
        // JFrame pawmodoro = inventoryExample.build();

        pawmodoro.pack();
        pawmodoro.setVisible(true);
    }

    public JFrame build() {

        final InventoryDataAccessInterface dataAccessObject = new InMemoryInventoryDataAccessObject();
        final CreateInventoryOutputBoundary presenter = new CreateInventoryPresenter(viewManagerModel, inventoryViewModel);
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(dataAccessObject, presenter,
                inventoryFactory);
        final CreateInventoryController controller = new CreateInventoryController(interactor);

        final JFrame application = new JFrame("Show up pls");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        // inventory does not exist yet
        DialogService dialogService = new DialogService();
        dialogService.showInventoryDialog(application, inventoryViewModel);

        controller.execute("chiually");

        return application;
    }

    public JFrame buildExistingInventory() {

        final InventoryDataAccessInterface dataAccessObject = new InMemoryInventoryDataAccessObject();
        final CreateInventoryOutputBoundary presenter = new CreateInventoryPresenter( viewManagerModel, inventoryViewModel);
        final AddToInventoryOutputBoundary addItemPresenter = new AddToInventoryPresenter(inventoryViewModel);
        final UseItemOutputBoundary useItemPresenter = new UseItemPresenter(inventoryViewModel);

        // add inventory
        createInventory(dataAccessObject);

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();

        final CreateInventoryInputBoundary createInventoryInteractor = new CreateInventoryInteractor(dataAccessObject, presenter,
                inventoryFactory);
        final AddToInventoryInputBoundary addToInventoryInteractor = new AddToInventoryInteractor(dataAccessObject, addItemPresenter, foodItemFactory);
        final UseItemInputBoundary useItemInteractor = new UseItemInteractor(dataAccessObject, useItemPresenter);

        final CreateInventoryController createInventoryController = new CreateInventoryController(createInventoryInteractor);
        final AddToInventoryController addToInventoryController = new AddToInventoryController(addToInventoryInteractor);
        final UseItemController useItemController = new UseItemController(useItemInteractor);

        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        DialogService dialogService = new DialogService();
        dialogService.showInventoryDialog(application, inventoryViewModel);

        createInventoryController.execute("chiually");
        addToInventoryController.execute("chiually", "cheese");
        useItemController.execute("chiually", "cheese");

        return application;
    }

    void createInventory(InventoryDataAccessInterface inventoryRepository) {

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();
        Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("Milk");
        final AbstractFood anotherFoodItem = foodItemFactory.create("Tuna");
        foodItem.setQuantity(2);
        anotherFoodItem.setQuantity(1);
        Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItem);
        inventoryItems.put("tuna", anotherFoodItem);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);
    }
}
