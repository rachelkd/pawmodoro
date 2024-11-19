package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import app.builder.DataAccessBuilder;
import app.builder.UseCaseBuilder;
import app.builder.ViewBuilder;
import app.builder.view.Views;
import app.components.DataAccessComponents;
import app.factory.ViewFactory;
import interface_adapter.ViewManagerModel;
import view.ViewManager;

/**
 * Main builder class that orchestrates the construction of the application.
 */
public class AppBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final ViewManager viewManager;
    private Views views;
    private DataAccessComponents dataAccess;

    public AppBuilder() {
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(cardLayout);
        this.viewManagerModel = new ViewManagerModel();
        this.viewFactory = new ViewFactory();
        this.viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    }

    /**
     * Initializes all components of the application.
     *
     * @return this builder for method chaining
     */
    public AppBuilder initialize() {
        // Build data access layer
        this.dataAccess = new DataAccessBuilder()
                .buildUserDataAccess()
                .buildInventoryDataAccess()
                .buildTimerDataAccess()
                .buildAdoptionDataAccess()
                .buildDisplayCatImageDataAccess()
                .buildCatDataAccess()
                .build();

        // Build views
        this.views = new ViewBuilder(cardPanel, cardLayout, viewManagerModel, viewFactory)
                .build();

        // Build use cases
        new UseCaseBuilder(views, dataAccess).build();

        return this;
    }

    /**
     * Builds and returns the application frame.
     *
     * @return the configured application frame
     */
    public JFrame build() {
        if (views == null || dataAccess == null) {
            initialize();
        }

        final JFrame application = new JFrame("Pawmodoro");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        // Set initial view to signup
        viewManagerModel.setState(views.getAuth().getViews().getSignupView().getViewName());
        // Set view to display cat statistics
        viewManagerModel.setState(views.getAuth().getViews().getSignupView().getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
