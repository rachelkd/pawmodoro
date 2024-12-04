package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.builder.DataAccessBuilder;
import app.builder.UseCaseBuilder;
import app.builder.ViewBuilder;
import app.builder.view.Views;
import app.components.DataAccessComponents;
import app.config.BuilderConfiguration;

/**
 * The AppBuilder class.
 */
public class AppBuilder {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private final BuilderConfiguration config;
    
    private Views views;
    private DataAccessComponents dataAccess;

    public AppBuilder() {
        this.config = new BuilderConfiguration(cardPanel, cardLayout);
    }

    /**
     * Builds the application.
     * @return the main application frame
     */
    public JFrame build() {
        if (views == null || dataAccess == null) {
            initialize();
        }
        return config.createMainFrame(views);
    }

    /**
     * Initializes the AppBuilder.
     * @return this AppBuilder
     */
    public AppBuilder initialize() {
        this.dataAccess = new DataAccessBuilder().build();
        this.views = new ViewBuilder(cardPanel, cardLayout, config.getViewManagerModel(), 
                                   config.getViewFactory(), config.getDialogService()).build();
        new UseCaseBuilder(views, dataAccess).build();
        return this;
    }
}
