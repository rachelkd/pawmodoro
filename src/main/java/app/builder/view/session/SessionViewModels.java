package app.builder.view.session;

import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
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
    private final GetCatFactViewModel getCatFactViewModel;
    private final DisplayCatStatsViewModel displayCatStatsViewModel;

    public SessionViewModels(SetupSessionViewModel setupSessionViewModel,
            InventoryViewModel inventoryViewModel,
            TimerViewModel timerViewModel, StudySessionViewModel studySessionViewModel,
            GetCatFactViewModel getCatFactViewModel, DisplayCatStatsViewModel displayCatStatsViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.inventoryViewModel = inventoryViewModel;
        this.timerViewModel = timerViewModel;
        this.studySessionViewModel = studySessionViewModel;
        this.getCatFactViewModel = getCatFactViewModel;
        this.displayCatStatsViewModel = displayCatStatsViewModel;
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

    public GetCatFactViewModel getGetCatFactViewModel() {
        return getCatFactViewModel;
    }

    public DisplayCatStatsViewModel getDisplayCatStatsViewModel() { return displayCatStatsViewModel;}
}
