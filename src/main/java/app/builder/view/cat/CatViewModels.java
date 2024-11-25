package app.builder.view.cat;

import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;

/**
 * Container for cat-related view models.
 */
public class CatViewModels {
    private final AdoptionViewModel adoptionViewModel;
    private final RunawayCatViewModel runawayCatViewModel;
    private final MaxCatsErrorViewModel maxCatsErrorViewModel;
    private final DisplayCatImageViewModel displayCatImageViewModel;
    private final DisplayCatStatsViewModel displayCatStatsViewModel;
    private final GetCatFactViewModel getCatFactViewModel;
    private final CatViewModel catViewModel;

    public CatViewModels(AdoptionViewModel adoptionViewModel,
            RunawayCatViewModel runawayCatViewModel,
            MaxCatsErrorViewModel maxCatsErrorViewModel,
            DisplayCatImageViewModel displayCatImageViewModel,
            DisplayCatStatsViewModel displayCatStatsViewModel,
            GetCatFactViewModel getCatFactViewModel,
            CatViewModel catViewModel) {
        this.adoptionViewModel = adoptionViewModel;
        this.runawayCatViewModel = runawayCatViewModel;
        this.maxCatsErrorViewModel = maxCatsErrorViewModel;
        this.displayCatImageViewModel = displayCatImageViewModel;
        this.displayCatStatsViewModel = displayCatStatsViewModel;
        this.getCatFactViewModel = getCatFactViewModel;
        this.catViewModel = catViewModel;
    }

    public AdoptionViewModel getAdoptionViewModel() {
        return adoptionViewModel;
    }

    public RunawayCatViewModel getRunawayCatViewModel() {
        return runawayCatViewModel;
    }

    public MaxCatsErrorViewModel getMaxCatsErrorViewModel() {
        return maxCatsErrorViewModel;
    }

    public DisplayCatImageViewModel getDisplayCatImageViewModel() {
        return displayCatImageViewModel;
    }

    public DisplayCatStatsViewModel getDisplayCatStatsViewModel() {
        return displayCatStatsViewModel;
    }

    public GetCatFactViewModel getGetCatFactViewModel() {
        return getCatFactViewModel;
    }

    public CatViewModel getCatViewModel() {
        return catViewModel;
    }
}
