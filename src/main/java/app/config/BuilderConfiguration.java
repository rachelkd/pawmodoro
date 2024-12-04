package app.config;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.builder.view.Views;
import app.factory.ViewFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.ViewManager;

/**
 * The BuilderConfigurationClass.
 */
public class BuilderConfiguration {
    private final DialogService dialogService;
    private final ViewManager viewManager;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final ApplicationFrameConfig frameConfig;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    public BuilderConfiguration(JPanel cardPanel, CardLayout cardLayout) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = new ViewManagerModel();
        this.dialogService = new DialogService(cardPanel);
        this.viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
        this.viewFactory = new ViewFactory(dialogService);
        this.frameConfig = new ApplicationFrameConfig();
    }

    /**
     * Creates the Main Frame.
     * @param views the views
     * @return the application
     */
    public JFrame createMainFrame(Views views) {
        final JFrame application = new JFrame("Pawmodoro");
        frameConfig.configureFrame(application, cardPanel);
        dialogService.setMainFrame(application);
        viewManagerModel.setState(views.getAuth().getViews().getSignupView().getViewName());
        viewManagerModel.firePropertyChanged();
        return application;
    }

    public DialogService getDialogService() {
        return dialogService;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
