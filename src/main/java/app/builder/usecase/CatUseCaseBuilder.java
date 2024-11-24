package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionPresenter;
import interface_adapter.change_cat_happiness.ChangeCatHappinessController;
import interface_adapter.change_cat_happiness.ChangeCatHappinessPresenter;
import interface_adapter.change_cat_hunger.ChangeCatHungerController;
import interface_adapter.change_cat_hunger.ChangeCatHungerPresenter;
import interface_adapter.create_cat.CreateCatController;
import interface_adapter.create_cat.CreateCatPresenter;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImagePresenter;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.display_cat_stats.DisplayCatStatsPresenter;
import interface_adapter.maxcatserror.MaxCatsErrorController;
import interface_adapter.maxcatserror.MaxCatsErrorPresenter;
import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayPresenter;
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
import use_case.display_cat_image.DisplayCatImageInputBoundary;
import use_case.display_cat_image.DisplayCatImageInteractor;
import use_case.display_cat_image.DisplayCatImageOutputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInputBoundary;
import use_case.display_cat_stats.DisplayCatStatsInteractor;
import use_case.display_cat_stats.DisplayCatStatsOutputBoundary;
import use_case.maxcatserror.MaxCatsErrorInputBoundary;
import use_case.maxcatserror.MaxCatsErrorInteractor;
import use_case.maxcatserror.MaxCatsErrorOutputBoundary;
import use_case.runawaycat.RunawayCatInputBoundary;
import use_case.runawaycat.RunawayCatInteractor;
import use_case.runawaycat.RunawayCatOutputBoundary;

// TODO: @rachelkd Make new *UseCaseBuilder class to fix fan out complexity
/**
 * Builder for cat-related use cases.
 */
public class CatUseCaseBuilder extends AbstractUseCaseBuilder {
    /**
     * Creates a new cat use case builder.
     *
     * @param views the views
     * @param dataAccess the data access components
     */
    public CatUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }

    /**
     * Builds the adoption use case.
     *
     * @return this builder
     */
    public CatUseCaseBuilder buildAdoptionUseCase() {
        final AdoptionOutputBoundary outputBoundary = new AdoptionPresenter(
                getViews().getSession().getViewModels().getSetupSessionViewModel(),
                getViews().getCat().getViewModels().getAdoptionViewModel(),
                getViews().getViewManagerModel());

        final AdoptionInputBoundary interactor = new AdoptionInteractor(outputBoundary);

        final AdoptionController controller = new AdoptionController(interactor);
        getViews().getCat().getViews().getAdoptionView().setAdoptionController(controller);
        return this;
    }

    /**
     * Builds the display cat image use case.
     *
     * @return this builder
     */
    public CatUseCaseBuilder buildDisplayCatImageUseCase() {
        final DisplayCatImageOutputBoundary outputBoundary = new DisplayCatImagePresenter(
                getViews().getCat().getViewModels().getDisplayCatImageViewModel());

        final DisplayCatImageInputBoundary interactor = new DisplayCatImageInteractor(
                getDataAccess().getDisplayCatImageDataAccess(),
                outputBoundary);

        final DisplayCatImageController controller = new DisplayCatImageController(interactor);
        getViews().getCat().getViews().getDisplayCatImageView().setDisplayCatImageController(controller);
        return this;
    }

    /**
     * Builds the display cat stats use case.
     *
     * @return this builder
     */
    public CatUseCaseBuilder buildDisplayCatStatsUseCase() {
        final DisplayCatStatsOutputBoundary presenter = new DisplayCatStatsPresenter(
                getViews().getCat().getViewModels().getDisplayCatStatsViewModel());

        final DisplayCatStatsInputBoundary interactor = new DisplayCatStatsInteractor(
                getDataAccess().getCatDataAccessObject(),
                presenter);

        final DisplayCatStatsController controller = new DisplayCatStatsController(interactor);

        getViews().getCat().getViews().getCatView().setDisplayCatStatsController(controller);

        return this;
    }

    /**
     * Builds the max cats error use case.
     *
     * @return this builder
     */
    public CatUseCaseBuilder buildMaxCatsErrorUseCase() {
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
     *
     * @return this builder
     */
    public CatUseCaseBuilder buildRunawayCatUseCase() {
        final RunawayCatOutputBoundary outputBoundary = new RunawayPresenter(
                getViews().getCat().getViewModels().getRunawayCatViewModel(),
                getViews().getViewManagerModel());

        final RunawayCatInputBoundary interactor = new RunawayCatInteractor(outputBoundary);
        final RunawayCatController controller =
                new RunawayCatController(getViews().getCat().getViewModels().getRunawayCatViewModel(), interactor);
        getViews().getCat().getViews().getRunawayCatView().setRunawayCatController(controller);

        return this;
    }

    /**
     * Builds Create Cat use case.
     * @return this builder
     */
    public CatUseCaseBuilder buildCreateCatUseCase() {
        final CreateCatOutputBoundary outputBoundary = new CreateCatPresenter(getViews().getViewManagerModel(),
                getViews().getCat().getViewModels().getMaxCatsErrorViewModel(),
                getViews().getAuth().getViewModels().getLoginViewModel(),
                getViews().getAuth().getViewModels().getSignupViewModel());

        final CreateCatInputBoundary interactor = new CreateCatInteractor(getDataAccess().getCatDataAccessObject(),
                outputBoundary);
        final CreateCatController controller = new CreateCatController(interactor);
        // views are lowkey guesses for where these uses cases will be needed, can be added to or removed as needed
        getViews().getAuth().getViews().getSignupView().setCreateCatController(controller);
        getViews().getAuth().getViews().getLoginView().setCreateCatController(controller);
        // whatever view will have new cat button
        getViews().getAuth().getViews().getLoggedInView().setCreateCatController(controller);
        return this;
    }

    /**
     * Builds Change Cat Hunger Use Case.
     * @return this builder
     */
    public CatUseCaseBuilder buildChangeCatHungerUseCase() {
        final ChangeCatHungerOutputBoundary outputBoundary =
                new ChangeCatHungerPresenter(getViews().getViewManagerModel(),
                        getViews().getCat().getViewModels().getDisplayCatStatsViewModel());

        final ChangeCatHungerInputBoundary interactor =
                new ChangeCatHungerInteractor(getDataAccess().getCatDataAccessObject(), outputBoundary);
        final ChangeCatHungerController controller = new ChangeCatHungerController(interactor);

        getViews().getSession().getViews().getInventoryView().setChangeCatHungerController(controller);
        return this;

    }

    /**
     * Builds Change Cat Happiness Use Case.
     * @return this builder
     */
    public CatUseCaseBuilder buildChangeCatHappinessUseCase() {
        final ChangeCatHappinessOutputBoundary outputBoundary =
                new ChangeCatHappinessPresenter(getViews().getViewManagerModel(),
                        getViews().getCat().getViewModels().getDisplayCatStatsViewModel());

        final ChangeCatHappinessInputBoundary interactor = new ChangeCatHappinessInteractor(
                getDataAccess().getCatDataAccessObject(), outputBoundary);
        final ChangeCatHappinessController controller = new ChangeCatHappinessController(interactor);

        getViews().getSession().getViews().getStudySessionView().setChangeCatHappinessController(controller);
        return this;
    }

    /**
     * Builds all cat-related use cases.
     *
     * @return this builder
     */
    public CatUseCaseBuilder build() {
        return this
                .buildAdoptionUseCase()
                .buildDisplayCatImageUseCase()
                .buildDisplayCatStatsUseCase()
                .buildMaxCatsErrorUseCase()
                .buildRunawayCatUseCase()
                .buildCreateCatUseCase()
                .buildChangeCatHungerUseCase()
                .buildChangeCatHappinessUseCase();
    }
}
