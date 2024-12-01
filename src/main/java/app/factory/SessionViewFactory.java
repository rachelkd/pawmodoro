package app.factory;

import app.service.DialogService;
import interface_adapter.break_session.BreakSessionState;
import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
import interface_adapter.music_control.MusicControlViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.adoption.AdoptionViewModel;
import view.BreakSessionView;
import view.CatContainerView;
import view.SetupSessionView;
import view.StudySessionView;

/**
 * Factory for creating session-related views.
 */
public class SessionViewFactory {
    /**
     * Creates a Setup Session View.
     * @param setupSessionViewModel the setup session view model
     * @return SetupSessionView
     */
    public SetupSessionView createSetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        return new SetupSessionView(setupSessionViewModel);
    }

    /**
     * Creates a Study Session View.
     * @param studySessionViewModel the study session view model
     * @param breakSessionViewModel the break session view model
     * @param initializeCatsViewModel the initialize cats view model
     * @param dialogService the dialog service
     * @param catContainerView the cat container view
     * @param musicControlViewModel the music control view model
     * @return StudySessionView
     */
    public StudySessionView createStudySessionView(StudySessionViewModel studySessionViewModel,
                                                  BreakSessionViewModel breakSessionViewModel,
                                                  InitializeCatsViewModel initializeCatsViewModel,
                                                  DialogService dialogService, 
                                                  CatContainerView catContainerView,
                                                  MusicControlViewModel musicControlViewModel) {
        return new StudySessionView(studySessionViewModel, breakSessionViewModel,
                initializeCatsViewModel, dialogService, catContainerView, musicControlViewModel);
    }

    /** 
     * Creates a Break Session View.
     * @param breakSessionViewModel the break session view model
     * @param breakSessionState the break session state
     * @param adoptionViewModel the adoption view model
     * @param dialogService the dialog service
     * @param catContainerView the cat container view
     * @return BreakSessionView
     */
    public BreakSessionView createBreakSessionView(BreakSessionViewModel breakSessionViewModel,
                                                    BreakSessionState breakSessionState,
                                                    AdoptionViewModel adoptionViewModel,
                                                    DialogService dialogService,
                                                    CatContainerView catContainerView) {
        return new BreakSessionView(breakSessionViewModel, 
                breakSessionState, adoptionViewModel, dialogService, catContainerView);
    }
}
