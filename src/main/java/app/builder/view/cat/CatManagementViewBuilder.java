package app.builder.view.cat;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.factory.ViewFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.AdoptionView;
import view.MaxCatsErrorView;
import view.RunawayCatView;

/**
 * Builder for cat management-related views.
 */
public class CatManagementViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final CatViewModels catViewModels;
    private final DialogService dialogService;

    private AdoptionView adoptionView;
    private RunawayCatView runawayCatView;
    private MaxCatsErrorView maxCatsErrorView;

    /**
     * Creates a new cat management view builder.
     *
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param catViewModels the cat view models
     * @param dialogService the dialog service
     */
    public CatManagementViewBuilder(
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
     * Builds all cat management-related views.
     *
     * @return this builder
     */
    public CatManagementViewBuilder build() {
        return this
                .buildAdoptionView()
                .buildRunawayCatView()
                .buildMaxCatsErrorView();
    }

    /**
     * Builds the adoption view.
     *
     * @return this builder
     */
    private CatManagementViewBuilder buildAdoptionView() {
        adoptionView = viewFactory.createAdoptionView(catViewModels.getAdoptionViewModel());
        cardPanel.add(adoptionView, adoptionView.getViewName());
        return this;
    }

    /**
     * Builds the runaway cat view.
     *
     * @return this builder
     */
    private CatManagementViewBuilder buildRunawayCatView() {
        runawayCatView = viewFactory.createRunawayCatView(catViewModels.getRunawayCatViewModel());
        cardPanel.add(runawayCatView, runawayCatView.getViewName());
        return this;
    }

    /**
     * Builds the max cats error view.
     *
     * @return this builder
     */
    private CatManagementViewBuilder buildMaxCatsErrorView() {
        maxCatsErrorView = viewFactory.createMaxCatsErrorView(catViewModels.getMaxCatsErrorViewModel());
        cardPanel.add(maxCatsErrorView, maxCatsErrorView.getViewName());
        return this;
    }

    public AdoptionView getAdoptionView() {
        return adoptionView;
    }

    public RunawayCatView getRunawayCatView() {
        return runawayCatView;
    }

    public MaxCatsErrorView getMaxCatsErrorView() {
        return maxCatsErrorView;
    }
}
