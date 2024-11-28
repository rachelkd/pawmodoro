package app.builder.view.shared;

import view.*;

/**
 * Container for views that are shared across multiple views.
 */
public class SharedViews {
    private final CatView catView;
    private final DisplayCatStatsView displayCatStatsView;
    private final GetCatFactView getCatFactView;
    private final InventoryView inventoryView;
    private final CatContainerView catContainerView;

    /**
     * Creates a new SharedViews container.
     * @param catView the cat view
     * @param displayCatStatsView the display cat stats view
     * @param getCatFactView the get cat fact view
     * @param inventoryView the inventory view
     */
    public SharedViews(CatView catView,
            DisplayCatStatsView displayCatStatsView,
            GetCatFactView getCatFactView,
            InventoryView inventoryView,
            CatContainerView catContainerView) {
        this.catView = catView;
        this.displayCatStatsView = displayCatStatsView;
        this.getCatFactView = getCatFactView;
        this.inventoryView = inventoryView;
        this.catContainerView = catContainerView;
    }

    public CatView getCatView() {
        return catView;
    }

    public DisplayCatStatsView getDisplayCatStatsView() {
        return displayCatStatsView;
    }

    public GetCatFactView getGetCatFactView() {
        return getCatFactView;
    }

    public InventoryView getInventoryView() {
        return inventoryView;
    }

    public CatContainerView getCatContainerView() {
        return catContainerView;
    }
}
