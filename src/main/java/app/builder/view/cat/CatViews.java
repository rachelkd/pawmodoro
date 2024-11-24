package app.builder.view.cat;

import view.AdoptionView;
import view.CatView;
import view.DisplayCatImageView;
import view.DisplayCatStatsView;
import view.MaxCatsErrorView;
import view.RunawayCatView;

/**
 * Container for cat-related views.
 */
public class CatViews {
    private final AdoptionView adoptionView;
    private final RunawayCatView runawayCatView;
    private final MaxCatsErrorView maxCatsErrorView;
    private final DisplayCatImageView displayCatImageView;
    private final DisplayCatStatsView displayCatStatsView;
    private final CatView catView;

    public CatViews(AdoptionView adoptionView,
            RunawayCatView runawayCatView,
            MaxCatsErrorView maxCatsErrorView,
            DisplayCatImageView displayCatImageView,
            DisplayCatStatsView displayCatStatsView,
            CatView catView) {
        this.adoptionView = adoptionView;
        this.runawayCatView = runawayCatView;
        this.maxCatsErrorView = maxCatsErrorView;
        this.displayCatImageView = displayCatImageView;
        this.displayCatStatsView = displayCatStatsView;
        this.catView = catView;
    }

    // View getters
    public AdoptionView getAdoptionView() {
        return adoptionView;
    }

    public RunawayCatView getRunawayCatView() {
        return runawayCatView;
    }

    public MaxCatsErrorView getMaxCatsErrorView() {
        return maxCatsErrorView;
    }

    public DisplayCatImageView getDisplayCatImageView() {
        return displayCatImageView;
    }

    public DisplayCatStatsView getDisplayCatStatsView() {
        return displayCatStatsView;
    }

    public CatView getCatView() {
        return catView;
    }
}
