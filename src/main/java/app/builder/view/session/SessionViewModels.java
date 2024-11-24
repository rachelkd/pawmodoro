package app.builder.view.session;

import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerViewModel;

/**
 * Container for session-related view models.
 */
public class SessionViewModels {
    private final SetupSessionViewModel setupSessionViewModel;
    private final InventoryViewModel inventoryViewModel;
    private final TimerViewModel timerViewModel;
    private final StudySessionViewModel studySessionViewModel;
    private final BreakSessionViewModel breakSessionViewModel;

    public SessionViewModels(SetupSessionViewModel setupSessionViewModel,
            InventoryViewModel inventoryViewModel,
            TimerViewModel timerViewModel, StudySessionViewModel studySessionViewModel,
            BreakSessionViewModel breakSessionViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.inventoryViewModel = inventoryViewModel;
        this.timerViewModel = timerViewModel;
        this.studySessionViewModel = studySessionViewModel;
        this.breakSessionViewModel = breakSessionViewModel;
    }

    public SetupSessionViewModel getSetupSessionViewModel() {
        return setupSessionViewModel;
    }

    public TimerViewModel getTimerViewModel() {
        return timerViewModel;
    }

    public InventoryViewModel getInventoryViewModel() {
        return inventoryViewModel;
    }

    public StudySessionViewModel getStudySessionViewModel() {
        return studySessionViewModel;
    }

    public BreakSessionViewModel getBreakSessionViewModel() {
        return breakSessionViewModel;
    }
}
