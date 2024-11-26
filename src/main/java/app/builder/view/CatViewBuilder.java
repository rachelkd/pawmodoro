package app.builder.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.builder.view.cat.CatDisplayViewBuilder;
import app.builder.view.cat.CatManagementViewBuilder;
import app.builder.view.cat.CatViewModels;
import app.builder.view.cat.CatViews;
import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewModels;
import app.factory.ViewFactory;
import app.factory.viewmodel.CatViewModelFactory;
import app.factory.viewmodel.SessionViewModelFactory;
import app.service.DialogService;
import interface_adapter.ViewManagerModel;

/**
 * Builder for cat-related views.
 */
public class CatViewBuilder {
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;
    private final ViewFactory viewFactory;
    private final CatViewModels catViewModels;
    private final SessionViewModels sessionViewModels;
    private final DialogService dialogService;

    private CatDisplayViewBuilder displayBuilder;
    private CatManagementViewBuilder managementBuilder;

    /**
     * Creates a new cat view builder.
     *
     * @param cardPanel the card panel
     * @param cardLayout the card layout
     * @param viewManagerModel the view manager model
     * @param viewFactory the view factory
     * @param dialogService the dialog service
     */
    public CatViewBuilder(JPanel cardPanel,
            CardLayout cardLayout,
            ViewManagerModel viewManagerModel,
            ViewFactory viewFactory,
            DialogService dialogService) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewFactory = viewFactory;
        this.dialogService = dialogService;

        final CatViewModelFactory catViewModelFactory = new CatViewModelFactory();
        this.catViewModels = new CatViewModels(
                catViewModelFactory.createAdoptionViewModel(),
                catViewModelFactory.createRunawayCatViewModel(),
                catViewModelFactory.createMaxCatsErrorViewModel(),
                catViewModelFactory.createDisplayCatImageViewModel(),
                catViewModelFactory.createGetCatFactViewModel(),
                catViewModelFactory.createCatViewModel());

        final SessionViewModelFactory sessionViewModelFactory = new SessionViewModelFactory();
        this.sessionViewModels = new SessionViewModels(
                sessionViewModelFactory.createSetupSessionViewModel(),
                sessionViewModelFactory.createInventoryViewModel(),
                sessionViewModelFactory.createTimerViewModel(),
                sessionViewModelFactory.createStudySessionViewModel(),
                sessionViewModelFactory.createGetCatFactViewModel(),
                sessionViewModelFactory.createDisplayCatStatsViewModel());
    }

    /**
     * Builds and returns the cat views and models.
     *
     * @return the cat views and models
     */
    public CatViewsAndModels build() {
        // Create and build display views
        displayBuilder = new CatDisplayViewBuilder(
                cardPanel,
                cardLayout,
                viewManagerModel,
                viewFactory,
                catViewModels,
                sessionViewModels,
                dialogService);
        displayBuilder.build();

        // Create and build management views
        managementBuilder = new CatManagementViewBuilder(
                cardPanel,
                cardLayout,
                viewManagerModel,
                viewFactory,
                catViewModels,
                dialogService);
        managementBuilder.build();

        // Create views container using both builders
        final CatViews views = new CatViews(managementBuilder, displayBuilder);
        return new CatViewsAndModels(views, catViewModels);
    }
}
