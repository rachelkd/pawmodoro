package app.builder.view.cat;

import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;

/**
 * Container for cat-related view models.
 */
public class CatViewModels {
    private final AdoptionViewModel adoptionViewModel;
    private final RunawayCatViewModel runawayCatViewModel;
    private final DisplayCatImageViewModel displayCatImageViewModel;

    public CatViewModels(AdoptionViewModel adoptionViewModel,
                         RunawayCatViewModel runawayCatViewModel,
                         DisplayCatImageViewModel displayCatImageViewModel) {
        this.adoptionViewModel = adoptionViewModel;
        this.runawayCatViewModel = runawayCatViewModel;
        this.displayCatImageViewModel = displayCatImageViewModel;
    }

    public AdoptionViewModel getAdoptionViewModel() {
        return adoptionViewModel;
    }

    public RunawayCatViewModel getRunawayCatViewModel() {
        return runawayCatViewModel;
    }

    public DisplayCatImageViewModel getDisplayCatImageViewModel() {
        return displayCatImageViewModel;
    }
}
