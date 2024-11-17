package interface_adapter.display_cat_stats;

import interface_adapter.ViewModel;

/**
 * View model for displaying cat statistics in a popup window.
 */
public class DisplayCatStatsViewModel extends ViewModel<DisplayCatStatsState> {
    public static final String TITLE_LABEL = "Cat Statistics";
    public static final String HUNGER_LABEL = "Hunger Level: ";
    public static final String HAPPINESS_LABEL = "Happiness Level: ";
    public static final String CLOSE_BUTTON_LABEL = "Close";

    public DisplayCatStatsViewModel() {
        super("display cat stats");
        setState(new DisplayCatStatsState());
    }
}
