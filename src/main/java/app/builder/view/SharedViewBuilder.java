package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.shared.SharedViewModels;
import app.builder.view.shared.SharedViews;
import app.builder.view.shared.SharedViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.SharedViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.AdoptionView;
import view.CatContainerView;
import view.CatView;
import view.DisplayCatStatsView;
import view.GetCatFactView;
import view.InventoryView;

/**
 * Builder for shared views and view models.
 */
public class SharedViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final DialogService dialogService;
    private final SharedViewModels viewModels;

    private CatView catView;
    private DisplayCatStatsView displayCatStatsView;
    private GetCatFactView getCatFactView;
    private InventoryView inventoryView;
    private AdoptionView adoptionView;
    private CatContainerView catContainerView;

    /**
     * Creates a new shared view builder.
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param dialogService the dialog service
     */
    public SharedViewBuilder(
            JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory,
            DialogService dialogService) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.dialogService = dialogService;

        // Create shared view models
        final SharedViewModelFactory sharedViewModelFactory = new SharedViewModelFactory();
        this.viewModels = new SharedViewModels(
                sharedViewModelFactory.createDisplayCatStatsViewModel(),
                sharedViewModelFactory.createInventoryViewModel(),
                sharedViewModelFactory.createCatViewModel(),
                sharedViewModelFactory.createGetCatFactViewModel(),
                sharedViewModelFactory.createAdoptionViewModel(),
                sharedViewModelFactory.createInitializeCatsViewModel());
    }

    /**
     * Builds and returns all shared views and models.
     * @return the shared views and models
     */
    public SharedViewsAndModels build() {
        return this
                .buildGetCatFactView()
                .buildDisplayCatStatsView()
                .buildInventoryView()
                .buildCatView()
                .buildCatContainerView()
                .buildAdoptionView()
                .createViewsAndModels();
    }

    private SharedViewBuilder buildAdoptionView() {
        adoptionView = viewFactory.createAdoptionView(viewModels.getAdoptionViewModel());
        return this;
    }

    /**
     * Builds the get cat fact view.
     * @return this builder
     */
    private SharedViewBuilder buildGetCatFactView() {
        getCatFactView = viewFactory.createGetCatFactView(viewModels.getGetCatFactViewModel());
        cardPanel.add(getCatFactView, getCatFactView.getViewName());
        cardPanel.revalidate();
        cardPanel.repaint();
        return this;
    }

    /**
     * Builds the display cat stats view.
     * @return this builder
     * @throws IllegalStateException if GetCatFactView hasn't been built yet
     */
    private SharedViewBuilder buildDisplayCatStatsView() {
        if (getCatFactView == null) {
            throw new IllegalStateException("GetCatFactView must be built before DisplayCatStatsView");
        }
        displayCatStatsView = viewFactory.createDisplayCatStatsView(
                viewModels.getDisplayCatStatsViewModel(),
                viewModels.getInventoryViewModel(),
                getCatFactView,
                dialogService);
        return this;
    }

    /**
     * Builds the inventory view.
     * @return this builder
     */
    private SharedViewBuilder buildInventoryView() {
        inventoryView = viewFactory.createInventoryView(viewModels.getInventoryViewModel());
        return this;
    }

    /**
     * Builds the cat view.
     * @return this builder
     * @throws IllegalStateException if GetCatFactView hasn't been built yet
     */
    private SharedViewBuilder buildCatView() {
        catView = viewFactory.createCatView(
                viewModels.getCatViewModel(),
                viewModels.getDisplayCatStatsViewModel(),
                viewModels.getInventoryViewModel(),
                dialogService,
                getCatFactView);
        cardPanel.add(catView, catView.getViewName());
        return this;
    }

    /**
     * Builds the CatContainer View.
     * @return the CatContainerView
     * @throws IllegalStateException if GetCAtFactView hasn't been built
     */
    private SharedViewBuilder buildCatContainerView() {
        if (getCatFactView == null) {
            throw new IllegalStateException("GetCatFactView must be built before DisplayCatStatsView");
        }
        catContainerView = viewFactory.createCatContainerView(viewModels.getInitializeCatsViewModel(),
                viewModels.getInventoryViewModel(),
                dialogService,
                getCatFactView);
        cardPanel.add(catContainerView, catContainerView.getViewName());
        return this;
    }

    /**
     * Creates and returns the views and models container.
     * @return the shared views and models
     */
    private SharedViewsAndModels createViewsAndModels() {
        final SharedViews views = new SharedViews(
                catView,
                displayCatStatsView,
                getCatFactView,
                inventoryView,
                adoptionView,
                catContainerView);
        return new SharedViewsAndModels(views, viewModels);
    }
}
