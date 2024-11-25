package app.builder.view.session;

import view.GetCatFactView;
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
    private final GetCatFactView getCatFactView;

    public SessionViews(SetupSessionView setupSessionView, InventoryView inventoryView,
            StudySessionView studySessionView, TimerView timerView, GetCatFactView getCatFactView) {
        this.setupSessionView = setupSessionView;
        this.inventoryView = inventoryView;
        this.studySessionView = studySessionView;
        this.timerView = timerView;
        this.getCatFactView = getCatFactView;
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

    public GetCatFactView getGetCatFactView() {
        return getCatFactView;
    }
}
