package app.builder.view.cat;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.factory.ViewFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.DisplayCatImageView;

/**
 * Builder for cat display-related views.
 */
public class CatDisplayViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final CatViewModels catViewModels;
    private final DialogService dialogService;

    private DisplayCatImageView displayCatImageView;

    /**
     * Creates a new cat display view builder.
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param catViewModels the cat view models
     * @param dialogService the dialog service
     */
    public CatDisplayViewBuilder(
            JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory,
            CatViewModels catViewModels,
            DialogService dialogService) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.catViewModels = catViewModels;
        this.dialogService = dialogService;
    }

    /**
     * Builds all cat display-related views.
     * @return this builder
     */
    public CatDisplayViewBuilder build() {
        return this.buildDisplayCatImageView();
    }

    /**
     * Builds the display cat image view.
     * @return this builder
     */
    private CatDisplayViewBuilder buildDisplayCatImageView() {
        displayCatImageView = viewFactory.createDisplayCatImageView(
                catViewModels.getDisplayCatImageViewModel());
        // Don't add to card panel; view will be injected into BreakSessionView
        return this;
    }

    public DisplayCatImageView getDisplayCatImageView() {
        return displayCatImageView;
    }
}
