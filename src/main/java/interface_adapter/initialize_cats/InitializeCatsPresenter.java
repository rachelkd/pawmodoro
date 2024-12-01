package interface_adapter.initialize_cats;

import java.util.ArrayList;
import java.util.Collection;

import entity.Cat;
import interface_adapter.cat.CatViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import use_case.cat_management.initialize_cats.InitializeCatsOutputBoundary;
import use_case.cat_management.initialize_cats.InitializeCatsOutputData;

/**
 * Initialize Cats Use Case Presenter.
 */
public class InitializeCatsPresenter implements InitializeCatsOutputBoundary {
    private final InitializeCatsViewModel initializeCatsViewModel;
    private final CatViewModelFactory catViewModelFactory;
    private final StudySessionViewModel studySessionViewModel;

    public InitializeCatsPresenter(InitializeCatsViewModel initializeCatsViewModel,
                                   StudySessionViewModel studySessionViewModel,
                                   CatViewModelFactory catViewModelFactory) {
        this.initializeCatsViewModel = initializeCatsViewModel;
        this.studySessionViewModel = studySessionViewModel;
        this.catViewModelFactory = catViewModelFactory;
    }

    @Override
    public void prepareSuccessView(InitializeCatsOutputData initializeCatsOutputData) {
        final Collection<CatViewModel> catViewModels = new ArrayList<>();

        for (Cat cat: initializeCatsOutputData.getCats()) {
            catViewModels.add(catViewModelFactory.create(cat));
        }
        initializeCatsViewModel.getState().setCatViewModels(catViewModels);
        initializeCatsViewModel.getState().setCats(initializeCatsOutputData.getCats());
        initializeCatsViewModel.firePropertyChanged("initialize_cats");
        studySessionViewModel.firePropertyChanged("initialize_cats");
    }
}
