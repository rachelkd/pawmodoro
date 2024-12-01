package app.builder.view.shared;

import view.AdoptionView;
import view.CatContainerView;
import view.CatView;
import view.DisplayCatStatsView;
import view.GetCatFactView;
import view.InventoryView;

/**
 * Container for views that are shared across multiple views.
 */
public class SharedViews {
    private final CatView catView;
    private final DisplayCatStatsView displayCatStatsView;
    private final GetCatFactView getCatFactView;
    private final InventoryView inventoryView;
    private final AdoptionView adoptionView;
    private final CatContainerView catContainerView;

    /**
     * Creates a new SharedViews container.
     * @param catView the cat view
     * @param displayCatStatsView the display cat stats view
     * @param getCatFactView the get cat fact view
     * @param inventoryView the inventory view
     * @param adoptionView the adoption view
     * @param catContainerView the cat container view
     */
    public SharedViews(CatView catView,
            DisplayCatStatsView displayCatStatsView,
            GetCatFactView getCatFactView,
            InventoryView inventoryView,
            AdoptionView adoptionView,
            CatContainerView catContainerView) {
        this.catView = catView;
        this.displayCatStatsView = displayCatStatsView;
        this.getCatFactView = getCatFactView;
        this.inventoryView = inventoryView;
        this.adoptionView = adoptionView;
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

    public AdoptionView getAdoptionView() { return adoptionView; }

    public CatContainerView getCatContainerView() {
        return catContainerView;
    }
}
