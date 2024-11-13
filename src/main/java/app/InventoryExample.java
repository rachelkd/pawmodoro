package app;

import data_access.InMemoryInventoryDataAccessObject;
import entity.FoodInventoryFactory;
import entity.InventoryFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.CreateInventoryPresenter;
import interface_adapter.create_inventory.InventoryViewModel;
import use_case.authentication.create_inventory.CreateInventoryInputBoundary;
import use_case.authentication.create_inventory.CreateInventoryInteractor;
import use_case.authentication.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.authentication.create_inventory.CreateInventoryOutputBoundary;
import view.InventoryView;
import view.LoggedInView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class InventoryExample {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private InventoryView inventoryView;
    private LoggedInViewModel loggedInView = new LoggedInViewModel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    public static void main(String[] args) {
        final InventoryExample inventoryExample = new InventoryExample();
        JFrame pawmodoro = inventoryExample.build();

        pawmodoro.pack();
        pawmodoro.setVisible(true);
    }

    public JFrame build() {

        final InventoryViewModel viewModel = new InventoryViewModel();
        final CreateInventoryInventoryDataAccessInterface dataAccessObject = new InMemoryInventoryDataAccessObject();
        final CreateInventoryOutputBoundary presenter = new CreateInventoryPresenter(loggedInView);

        // empty inventory
        final InventoryFactory inventoryFactory = new FoodInventoryFactory();

        final CreateInventoryInputBoundary interactor = new CreateInventoryInteractor(dataAccessObject, presenter,
                inventoryFactory);
        final CreateInventoryController controller = new CreateInventoryController(interactor);
        final InventoryView inventoryView = new InventoryView(viewModel, controller);


        final JFrame application = new JFrame("Pllllssssss");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        application.add(inventoryView);

        return application;
    }
}
