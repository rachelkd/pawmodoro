package interface_adapter.initialize_cats;

import entity.Cat;
import interface_adapter.cat.CatViewModel;
import use_case.cat_management.initialize_cats.InitializeCatsOutputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsOutputData;

import java.util.ArrayList;
import java.util.Collection;

public class InitializeCatsPresenter implements InitializeCatsOutputBoundary {
    private final InitializeCatsViewModel initializeCatsViewModel;
    private final CatViewModelFactory catViewModelFactory;

    public InitializeCatsPresenter(InitializeCatsViewModel initializeCatsViewModel,
                                   CatViewModelFactory catViewModelFactory) {
        this.initializeCatsViewModel = initializeCatsViewModel;
        this.catViewModelFactory = catViewModelFactory;
    }

    @Override
    public void prepareSuccessView(InitializeCatsOutputData initializeCatsOutputData) {
        final Collection<CatViewModel> catViewModels = new ArrayList<>();

        for (Cat cat: initializeCatsOutputData.getCats()) {
            catViewModels.add(catViewModelFactory.create(cat));
        }
        initializeCatsViewModel.getState().setCatViewModels(catViewModels);
        initializeCatsViewModel.firePropertyChanged();
    }
}
