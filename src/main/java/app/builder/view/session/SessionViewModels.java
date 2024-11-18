package app.builder.view.session;

import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.timer.TimerViewModel;

/**
 * Container for session-related view models.
 */
public class SessionViewModels {
    private final SetupSessionViewModel setupSessionViewModel;
    private final InventoryViewModel inventoryViewModel;
    private final TimerViewModel timerViewModel;

    public SessionViewModels(SetupSessionViewModel setupSessionViewModel,
            InventoryViewModel inventoryViewModel,
            TimerViewModel timerViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.inventoryViewModel = inventoryViewModel;
        this.timerViewModel = timerViewModel;
    }

    public SetupSessionViewModel getSetupSessionViewModel() {
        return setupSessionViewModel;
    }

    public InventoryViewModel getInventoryViewModel() {
        return inventoryViewModel;
    }

    public TimerViewModel getTimerViewModel() {
        return timerViewModel;
    }
}
