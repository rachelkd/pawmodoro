package interface_adapter.create_cat;

import entity.Cat;
import interface_adapter.ViewManagerModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.initialize_cats.CatViewModelFactory;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import use_case.cat_management.create_cat.CreateCatOutputBoundary;
import use_case.cat_management.create_cat.CreateCatOutputData;

import java.util.Collection;

/**
 * Create Cat Use Case Presenter.
 */
public class CreateCatPresenter implements CreateCatOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private MaxCatsErrorViewModel maxCatsErrorViewModel;
    private InitializeCatsViewModel initializeCatsViewModel;

    public CreateCatPresenter(ViewManagerModel viewManagerModel,
                              MaxCatsErrorViewModel maxCatsErrorView,
                              InitializeCatsViewModel initializeCatsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.maxCatsErrorViewModel = maxCatsErrorView;
        this.initializeCatsViewModel = initializeCatsViewModel;
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
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
