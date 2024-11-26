package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;
import interface_adapter.music_control.MusicControlController;
import interface_adapter.music_control.MusicControlPresenter;
import use_case.music_control.MusicControlInputBoundary;
import use_case.music_control.MusicControlInteractor;
import use_case.music_control.MusicControlOutputBoundary;

public class MusicControlUseCaseBuilder extends AbstractUseCaseBuilder {

    public MusicControlUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
    }
    
    public MusicControlUseCaseBuilder buildMusicControlUseCase() {
        final MusicControlOutputBoundary outputBoundary = new MusicControlPresenter(
            getViews().getSession().getViewModels().getMusicControlViewModel());

        final MusicControlInputBoundary interactor = new MusicControlInteractor(outputBoundary);

        final MusicControlController controller = new MusicControlController(interactor);

        getViews().getSession().getViews().getStudySessionView().setMusicControlController(controller);

        return this;
    }

    public MusicControlUseCaseBuilder build() {
        return this.buildMusicControlUseCase();
    }
}
