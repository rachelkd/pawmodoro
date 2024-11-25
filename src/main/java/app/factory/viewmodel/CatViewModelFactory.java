package app.factory.viewmodel;

import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;

/**
 * Factory for creating cat-related view models.
 */
public class CatViewModelFactory {
    /**
     * Creates a new cat view model.
     *
     * @return the cat view model
     */
    public CatViewModel createCatViewModel() {
        return new CatViewModel();
    }

    /**
     * Creates a new adoption view model.
     *
     * @return the adoption view model
     */
    public AdoptionViewModel createAdoptionViewModel() {
        return new AdoptionViewModel();
    }

    /**
     * Creates a new runaway cat view model.
     *
     * @return the runaway cat view model
     */
    public RunawayCatViewModel createRunawayCatViewModel() {
        return new RunawayCatViewModel();
    }

    /**
     * Creates a new max cats error view model.
     *
     * @return the max cats error view model
     */
    public MaxCatsErrorViewModel createMaxCatsErrorViewModel() {
        return new MaxCatsErrorViewModel();
    }

    /**
     * Creates a new display cat image view model.
     *
     * @return the display cat image view model
     */
    public DisplayCatImageViewModel createDisplayCatImageViewModel() {
        return new DisplayCatImageViewModel();
    }

    /**
     * Creates a new display cat stats view model.
     *
     * @return the display cat stats view model
     */
    public DisplayCatStatsViewModel createDisplayCatStatsViewModel() {
        return new DisplayCatStatsViewModel();
    }

    /**
     * Creates a new get cat fact view model.
     *
     * @return the get cat fact view model
     */
    public GetCatFactViewModel createGetCatFactViewModel() {
        return new GetCatFactViewModel();
    }
}
