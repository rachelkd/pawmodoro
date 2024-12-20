package interface_adapter.display_cat_stats;

import interface_adapter.ViewModel;
import interface_adapter.cat.CatState;

/**
 * View model for displaying cat statistics in a popup window.
 */
public class DisplayCatStatsViewModel extends ViewModel<CatState> {
    public static final String TITLE_LABEL = "Cat Statistics";
    public static final String HUNGER_LABEL = "Hunger Level: ";
    public static final String HAPPINESS_LABEL = "Happiness Level: ";
    public static final String CLOSE_BUTTON_LABEL = "Close";
    public static final String INVENTORY_BUTTON_LABEL = "Open Inventory";

    public DisplayCatStatsViewModel() {
        super("display cat stats");
        setState(new CatState());
    }
}
