package app;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import data_access.InMemoryInventoryDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.create_inventory.InventoryViewModel;
import use_case.authentication.create_inventory.*;
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

        pawmodoro.pack();
        pawmodoro.setVisible(true);
    }

    public JFrame build() {
        inventoryView = new InventoryView(inventoryViewModel);
        cardPanel.add(inventoryView, inventoryViewModel.getViewName());

        final CreateInventoryInventoryDataAccessInterface dataAccessObject = new InMemoryInventoryDataAccessObject();
        final CreateInventoryOutputBoundary presenter = new CreateInventoryPresenter(viewManagerModel, inventoryViewModel);


        final InventoryFactory inventoryFactory = new FoodInventoryFactory();

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(dataAccessObject, presenter,
                inventoryFactory);
        final CreateInventoryController controller = new CreateInventoryController(interactor);
        inventoryView.setCreateInventoryController(controller);

        // inventory does not exist yet
        controller.execute("chiually");


        final JFrame application = new JFrame("");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        application.add(inventoryView);

        return application;
    }

    public JFrame buildExistingInventory() {
        // makes the property fire properly
        inventoryView = new InventoryView(inventoryViewModel);
        cardPanel.add(inventoryView, inventoryViewModel.getViewName());

        final CreateInventoryInventoryDataAccessInterface dataAccessObject = new InMemoryInventoryDataAccessObject();
        final CreateInventoryOutputBoundary presenter = new CreateInventoryPresenter( viewManagerModel, inventoryViewModel);

        // add inventory
        createInventory(dataAccessObject);

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(dataAccessObject, presenter,
                inventoryFactory);

        final CreateInventoryController controller = new CreateInventoryController(interactor);
        inventoryView.setCreateInventoryController(controller);

        controller.execute("chiually");

        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        application.add(inventoryView);

        return application;
    }

    void createInventory(CreateInventoryInventoryDataAccessInterface inventoryRepository) {

        final InventoryFactory inventoryFactory = new FoodInventoryFactory();
        final FoodItemFactory foodItemFactory = new FoodItemFactory();
        Inventory inventory = inventoryFactory.create("chiually");
        final AbstractFood foodItem = foodItemFactory.create("milk", "Milk");
        final AbstractFood anotherFoodItem = foodItemFactory.create("tuna", "Tuna");
        foodItem.setQuantity(2);
        anotherFoodItem.setQuantity(1);
        Map<String, AbstractFood> inventoryItems = inventory.getItems();
        inventoryItems.put("milk", foodItem);
        inventoryItems.put("tuna", anotherFoodItem);
        inventory.setItems(inventoryItems);
        inventoryRepository.save(inventory);
    }
}
