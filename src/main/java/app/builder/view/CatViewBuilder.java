package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.cat.CatViewModels;
import app.builder.view.cat.CatViews;
import app.builder.view.cat.CatViewsAndModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.CatViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;
import view.AdoptionView;
import view.CatView;
import view.DisplayCatImageView;
import view.DisplayCatStatsView;
import view.MaxCatsErrorView;
import view.RunawayCatView;

/**
 * Builder for cat-related views.
 */
public class CatViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final CatViewModelFactory catViewModelFactory;
    private final CatViewModels catViewModels;
    private final DialogService dialogService;

    // Views
    private AdoptionView adoptionView;
    private RunawayCatView runawayCatView;
    private MaxCatsErrorView maxCatsErrorView;
    private DisplayCatImageView displayCatImageView;
    private DisplayCatStatsView displayCatStatsView;
    private CatView catView;

    /**
     * Creates a new cat view builder.
     *
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     */
    public CatViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.catViewModelFactory = new CatViewModelFactory();
        this.dialogService = new DialogService();
        this.catViewModels = new CatViewModels(
                catViewModelFactory.createAdoptionViewModel(),
                catViewModelFactory.createRunawayCatViewModel(),
                catViewModelFactory.createMaxCatsErrorViewModel(),
                catViewModelFactory.createDisplayCatImageViewModel(),
                catViewModelFactory.createDisplayCatStatsViewModel(),
                catViewModelFactory.createCatViewModel());
    }

    /**
     * Builds the adoption view.
     *
     * @return this builder
     */
    public CatViewBuilder buildAdoptionView() {
        adoptionView = viewFactory.createAdoptionView(catViewModels.getAdoptionViewModel());
        cardPanel.add(adoptionView, adoptionView.getViewName());
        return this;
    }

    /**
     * Builds the runaway cat view.
     *
     * @return this builder
     */
    public CatViewBuilder buildRunawayCatView() {
        runawayCatView = viewFactory.createRunawayCatView(catViewModels.getRunawayCatViewModel());
        cardPanel.add(runawayCatView, runawayCatView.getViewName());
        return this;
    }

    /**
     * Builds the max cats error view.
     *
     * @return this builder
     */
    public CatViewBuilder buildMaxCatsErrorView() {
        maxCatsErrorView = viewFactory.createMaxCatsErrorView(catViewModels.getMaxCatsErrorViewModel());
        cardPanel.add(maxCatsErrorView, maxCatsErrorView.getViewName());
        return this;
    }

    /**
     * Builds the display cat image view.
     *
     * @return this builder
     */
    public CatViewBuilder buildDisplayCatImageView() {
        displayCatImageView = viewFactory.createDisplayCatImageView(catViewModels.getDisplayCatImageViewModel());
        cardPanel.add(displayCatImageView, displayCatImageView.getViewName());
        return this;
    }

    /**
     * Builds the cat view.
     *
     * @return this builder
     */
    public CatViewBuilder buildCatView() {
        catView = viewFactory.createCatView(
                catViewModels.getCatViewModel(),
                catViewModels.getDisplayCatStatsViewModel(),
                dialogService);
        cardPanel.add(catView, catView.getViewName());
        return this;
    }

    /**
     * Builds the display cat stats view.
     *
     * @return this builder
     */
    public CatViewBuilder buildDisplayCatStatsView() {
        // TODO: @rachelkd idk what to do
        // displayCatStatsView = viewFactory.createDisplayCatStatsView(catViewModels.getDisplayCatStatsViewModel());
        // cardPanel.add(displayCatStatsView, displayCatStatsView.getViewName());
        return this;
    }

    /**
     * Builds and returns the cat views and models.
     *
     * @return the cat views and models
     */
    public CatViewsAndModels build() {
        final CatViews views = new CatViews(
                adoptionView,
                runawayCatView,
                maxCatsErrorView,
                displayCatImageView,
                displayCatStatsView,
                catView);

        return new CatViewsAndModels(views, catViewModels);
    }
}
