package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import entity.CatFactory;
import entity.DefaultHappinessCalculator;
import entity.DefaultHungerCalculator;
import entity.FoodItemFactory;
import entity.HappinessCalculator;
import entity.HungerCalculator;
import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionPresenter;
import interface_adapter.change_cat_happiness.ChangeCatHappinessController;
import interface_adapter.change_cat_happiness.ChangeCatHappinessPresenter;
import interface_adapter.change_cat_hunger.ChangeCatHungerController;
import interface_adapter.change_cat_hunger.ChangeCatHungerPresenter;
import interface_adapter.create_cat.CreateCatController;
import interface_adapter.create_cat.CreateCatPresenter;
import interface_adapter.maxcatserror.MaxCatsErrorController;
import interface_adapter.maxcatserror.MaxCatsErrorPresenter;
import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayCatPresenter;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInteractor;
import use_case.adoption.AdoptionOutputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInteractor;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessOutputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInputBoundary;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerInteractor;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerOutputBoundary;
import use_case.cat_management.create_cat.CreateCatInputBoundary;
import use_case.cat_management.create_cat.CreateCatInteractor;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.maxcatserror.MaxCatsErrorInputBoundary;
import use_case.maxcatserror.MaxCatsErrorInteractor;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;
import use_case.runawaycat.RunawayCatInputBoundary;
import use_case.runawaycat.RunawayCatInteractor;
import use_case.runawaycat.RunawayCatOutputBoundary;

/**
 * Builder for cat management-related use cases.
 */
public class CatManagementUseCaseBuilder extends AbstractUseCaseBuilder {
    private final HappinessCalculator happinessCalculator;
    private final HungerCalculator hungerCalculator;
    private final FoodItemFactory foodItemFactory;
    private final CatFactory catFactory;

    /**
     * Creates a new cat management use case builder.
     * @param views the views
     * @param dataAccess the data access components
     */
    public CatManagementUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
        this.happinessCalculator = new DefaultHappinessCalculator();
        this.hungerCalculator = new DefaultHungerCalculator();
        this.foodItemFactory = new FoodItemFactory();
        this.catFactory = new CatFactory();
    }

    /**
     * Builds the adoption use case.
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildAdoptionUseCase() {
        final AdoptionOutputBoundary outputBoundary = new AdoptionPresenter(
                getViews().getCat().getViewModels().getAdoptionViewModel());

        final AdoptionInputBoundary interactor = new AdoptionInteractor(outputBoundary);

        final AdoptionController controller = new AdoptionController(interactor, getDataAccess().getUserDataAccess());
        getViews().getCat().getViews().getAdoptionView().setAdoptionController(controller);
        return this;
    }

    /**
     * Builds the max cats error use case.
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildMaxCatsErrorUseCase() {
        final MaxCatsErrorOutputBoundary outputBoundary = new MaxCatsErrorPresenter(
                getViews().getViewManagerModel(),
                getViews().getCat().getViewModels().getMaxCatsErrorViewModel());

        final MaxCatsErrorInputBoundary interactor = new MaxCatsErrorInteractor(outputBoundary);
        final MaxCatsErrorController controller = new MaxCatsErrorController(interactor);
        getViews().getCat().getViews().getMaxCatsErrorView().setMaxCatsController(controller);
        return this;
    }

    /**
     * Builds the runaway cat use case.
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildRunawayCatUseCase() {
        final RunawayCatOutputBoundary outputBoundary = new RunawayCatPresenter(
                getViews().getCat().getViewModels().getRunawayCatViewModel(),
                getViews().getViewManagerModel());

        final RunawayCatInputBoundary interactor = new RunawayCatInteractor(outputBoundary,
                getDataAccess().getCatDataAccess());
        final RunawayCatController controller =
                new RunawayCatController(getViews().getCat().getViewModels().getRunawayCatViewModel(), interactor);
        getViews().getCat().getViews().getRunawayCatView().setRunawayCatController(controller);

        return this;
    }

    /**
     * Builds Create Cat use case.
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildCreateCatUseCase() {
        final CreateCatOutputBoundary outputBoundary = new CreateCatPresenter(getViews().getViewManagerModel(),
                getViews().getCat().getViewModels().getMaxCatsErrorViewModel(),
                getViews().getShared().getViewModels().getInitializeCatsViewModel(),
                getViews().getCat().getViewModels().getAdoptionViewModel());

        final CreateCatInputBoundary interactor = new CreateCatInteractor(getDataAccess().getCatDataAccess(),
                outputBoundary, catFactory);
        final CreateCatController controller = new CreateCatController(interactor);
        // views are lowkey guesses for where these uses cases will be needed, can be added to or removed as needed
        getViews().getAuth().getViews().getSignupView().setCreateCatController(controller);
        getViews().getAuth().getViews().getLoginView().setCreateCatController(controller);
        // whatever view will have new cat button
        getViews().getCat().getViews().getAdoptionView().setCreateCatController(controller);
        return this;
    }

    /**
     * Builds Change Cat Hunger Use Case.
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildChangeCatHungerUseCase() {
        final ChangeCatHungerOutputBoundary outputBoundary =
                new ChangeCatHungerPresenter(getViews().getViewManagerModel(),
                        getViews().getShared().getViewModels().getDisplayCatStatsViewModel());

        final ChangeCatHungerInputBoundary interactor =
                new ChangeCatHungerInteractor(getDataAccess().getCatDataAccess(), outputBoundary,
                        hungerCalculator, foodItemFactory);
        final ChangeCatHungerController controller = new ChangeCatHungerController(interactor);

        getViews().getShared().getViews().getInventoryView().setChangeCatHungerController(controller);
        getViews().getSession().getViews().getStudySessionView().setChangeCatHungerController(controller);
        return this;

    }

    /**
     * Builds Change Cat Happiness Use Case.
     * @return this builder
     */
    public CatManagementUseCaseBuilder buildChangeCatHappinessUseCase() {
        final ChangeCatHappinessOutputBoundary outputBoundary =
                new ChangeCatHappinessPresenter(getViews().getViewManagerModel(),
                        getViews().getSession().getViewModels().getStudySessionViewModel(),
                        getViews().getShared().getViewModels().getDisplayCatStatsViewModel(),
                        getViews().getCat().getViewModels().getRunawayCatViewModel());

        final ChangeCatHappinessInputBoundary interactor = new ChangeCatHappinessInteractor(
                getDataAccess().getCatDataAccess(), outputBoundary, happinessCalculator);
        final ChangeCatHappinessController controller = new ChangeCatHappinessController(interactor);

        getViews().getSession().getViews().getStudySessionView().setChangeCatHappinessController(controller);
        return this;
    }

    /**
     * Builds all cat management-related use cases.
     * @return this builder
     */
    public CatManagementUseCaseBuilder build() {
        return this
                .buildAdoptionUseCase()
                .buildMaxCatsErrorUseCase()
                .buildRunawayCatUseCase()
                .buildCreateCatUseCase()
                .buildChangeCatHungerUseCase()
                .buildChangeCatHappinessUseCase();
    }
}
