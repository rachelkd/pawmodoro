package interface_adapter.create_cat;

import java.util.Collection;

import entity.Cat;
import interface_adapter.ViewManagerModel;
import interface_adapter.adoption.AdoptionState;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.initialize_cats.CatViewModelFactory;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.cat_management.create_cat.CreateCatOutputData;

/**
 * Create Cat Use Case Presenter.
 */
public class CreateCatPresenter implements CreateCatOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private InitializeCatsViewModel initializeCatsViewModel;
    private AdoptionViewModel adoptionViewModel;

    public CreateCatPresenter(ViewManagerModel viewManagerModel,
                              InitializeCatsViewModel initializeCatsViewModel,
                              AdoptionViewModel adoptionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.initializeCatsViewModel = initializeCatsViewModel;
        this.adoptionViewModel = adoptionViewModel;
    }

    @Override
    public void prepareSuccessView(CreateCatOutputData createCatOutputData) {
        final CatViewModelFactory catViewModelFactory = new CatViewModelFactory();
        final Collection<CatViewModel> catViewModels = initializeCatsViewModel.getState().getCatViewModels();

        final CatViewModel newCatViewModel = catViewModelFactory.create(createCatOutputData.getCat());
        catViewModels.add(newCatViewModel);

        final Collection<Cat> cats = initializeCatsViewModel.getState().getCats();
        cats.add(createCatOutputData.getCat());

        initializeCatsViewModel.getState().setCatViewModels(catViewModels);
        initializeCatsViewModel.getState().setCats(cats);
        initializeCatsViewModel.firePropertyChanged("initialize_cats");

        final AdoptionState adoptionState = adoptionViewModel.getState();
        adoptionState.setIsSuccess(createCatOutputData.isSuccess());
        adoptionViewModel.setState(adoptionState);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final AdoptionState adoptionState = adoptionViewModel.getState();
        adoptionState.setAdoptionError(errorMessage);
        adoptionViewModel.setState(adoptionState);
        adoptionViewModel.firePropertyChanged();
    }
}
