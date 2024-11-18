package interface_adapter.study_session;

import interface_adapter.ViewModel;

/**
 * View Model of Study Session.
 */
public class StudySessionViewModel extends ViewModel<StudySessionState> {

    public StudySessionViewModel() {
        super("study session");
        setState(new StudySessionState());
    }
}

