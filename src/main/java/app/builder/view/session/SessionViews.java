package app.builder.view.session;

import view.BreakSessionView;
import view.InventoryView;
import view.SetupSessionView;
import view.StudySessionView;

/**
 * Container for session-related views.
 */
public class SessionViews {
    private final SetupSessionView setupSessionView;
    private final InventoryView inventoryView;
    private final StudySessionView studySessionView;
    private final BreakSessionView breakSessionView;

    public SessionViews(SetupSessionView setupSessionView, InventoryView inventoryView,
            StudySessionView studySessionView, BreakSessionView breakSessionView) {
        this.setupSessionView = setupSessionView;
        this.inventoryView = inventoryView;
        this.studySessionView = studySessionView;
        this.breakSessionView = breakSessionView;
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

    public BreakSessionView getBreakSessionView() {
        return breakSessionView;
    }
}
