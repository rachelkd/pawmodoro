package app.builder.view.session;

import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.music_control.MusicControlViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerViewModel;

/**
 * Container for session-related view models.
 */
public class SessionViewModels {
    private final LoginViewModel loginViewModel;
    private final SetupSessionViewModel setupSessionViewModel;
    private final TimerViewModel timerViewModel;
    private final StudySessionViewModel studySessionViewModel;
    private final BreakSessionViewModel breakSessionViewModel;
    private final MusicControlViewModel musicControlViewModel;

    public SessionViewModels(SetupSessionViewModel setupSessionViewModel,
                             TimerViewModel timerViewModel, StudySessionViewModel studySessionViewModel,
                             MusicControlViewModel musicControlViewModel, BreakSessionViewModel breakSessionViewModel,
                             LoginViewModel loginViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.timerViewModel = timerViewModel;
        this.studySessionViewModel = studySessionViewModel;
        this.breakSessionViewModel = breakSessionViewModel;
        this.loginViewModel = loginViewModel;
        this.musicControlViewModel = musicControlViewModel;
    }

    public SetupSessionViewModel getSetupSessionViewModel() {
        return setupSessionViewModel;
    }

    public TimerViewModel getTimerViewModel() {
        return timerViewModel;
    }

    public StudySessionViewModel getStudySessionViewModel() {
        return studySessionViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public BreakSessionViewModel getBreakSessionViewModel() {
        return breakSessionViewModel;
    }

    public MusicControlViewModel getMusicControlViewModel() {
        return musicControlViewModel;
    }

}
