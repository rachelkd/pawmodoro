package app.builder.view.session;

import view.*;

/**
 * Container for session-related views.
 */
public class SessionViews {
    private final SetupSessionView setupSessionView;
    private final InventoryView inventoryView;
    private final StudySessionView studySessionView;
    private final TimerView timerView;
    private final GetCatFactView getCatFactView;
    private final DisplayCatStatsView displayCatStatsView;

    public SessionViews(SetupSessionView setupSessionView, InventoryView inventoryView,
                        StudySessionView studySessionView, TimerView timerView,
                        GetCatFactView getCatFactView, DisplayCatStatsView displayCatStatsView) {
        this.setupSessionView = setupSessionView;
        this.inventoryView = inventoryView;
        this.studySessionView = studySessionView;
        this.timerView = timerView;
        this.getCatFactView = getCatFactView;
        this.displayCatStatsView = displayCatStatsView;
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

    public DisplayCatStatsView getDisplayCatStatsView() {
        return displayCatStatsView;
    }
}
