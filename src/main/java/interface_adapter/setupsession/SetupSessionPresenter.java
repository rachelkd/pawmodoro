package interface_adapter.setupsession;

import interface_adapter.ViewManagerModel;

/**
 * Presenter for the setting up study session use case.
 */
public class SetupSessionPresenter {
    private final SetupSessionViewModel setupSessionViewModel;
    private final ViewManagerModel viewManagerModel;

    public SetupSessionPresenter(SetupSessionViewModel setupSessionViewModel, ViewManagerModel viewManagerModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        //When success, switch to study timer view.

    }

    @Override
    public void prepareFailView() {

    }

    @Override
    public void switchToStudyView() {
        viewManagerModel.setState(studySessionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
