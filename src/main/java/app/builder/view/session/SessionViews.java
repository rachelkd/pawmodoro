package app.builder.view.session;

import view.InventoryView;
import view.SetupSessionView;
import view.StudySessionView;
import view.TimerView;

/**
 * Container for session-related views.
 */
public class SessionViews {
    private final SetupSessionView setupSessionView;
    private final InventoryView inventoryView;
    private final StudySessionView studySessionView;
    private final TimerView timerView;

    public SessionViews(SetupSessionView setupSessionView, InventoryView inventoryView,
            StudySessionView studySessionView, TimerView timerView) {
        this.setupSessionView = setupSessionView;
        this.inventoryView = inventoryView;
        this.studySessionView = studySessionView;
        this.timerView = timerView;
    }

    public SetupSessionView getSetupSessionView() {
        return setupSessionView;
    }

    public InventoryView getInventoryView() {
        return inventoryView;
    }

    public StudySessionView getStudySessionView() {
        return studySessionView;
    }

    public TimerView getTimerView() {
        return timerView;
    }
}
