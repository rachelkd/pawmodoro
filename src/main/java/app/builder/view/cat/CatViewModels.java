package app.builder.view.cat;

import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
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

    public CatViewModels(AdoptionViewModel adoptionViewModel,
                         RunawayCatViewModel runawayCatViewModel,
                         MaxCatsErrorViewModel maxCatsErrorViewModel,
                         DisplayCatImageViewModel displayCatImageViewModel) {
        this.adoptionViewModel = adoptionViewModel;
        this.runawayCatViewModel = runawayCatViewModel;
        this.maxCatsErrorViewModel = maxCatsErrorViewModel;
        this.displayCatImageViewModel = displayCatImageViewModel;
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
}
