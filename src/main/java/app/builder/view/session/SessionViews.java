package app.builder.view.session;

import view.InventoryView;
import view.SetupSessionView;

/**
 * Container for session-related views.
 */
public class SessionViews {
    private final SetupSessionView setupSessionView;
    private final InventoryView inventoryView;

    public SessionViews(SetupSessionView setupSessionView, InventoryView inventoryView) {
        this.setupSessionView = setupSessionView;
        this.inventoryView = inventoryView;
    }

    public SetupSessionView getSetupSessionView() {
        return setupSessionView;
    }

    public InventoryView getInventoryView() {
        return inventoryView;
    }
}
