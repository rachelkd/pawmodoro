package app.builder.view.cat;

import view.AdoptionView;
import view.DisplayCatImageView;
import view.RunawayCatView;

/**
 * Container for cat-related views.
 */
public class CatViews {
    // Management-related views
    private final AdoptionView adoptionView;
    private final RunawayCatView runawayCatView;

    // Display-related views
    private final DisplayCatImageView displayCatImageView;

    /**
     * Creates a new CatViews container by combining views from management and display builders.
     * @param managementBuilder the cat management view builder
     * @param displayBuilder the cat display view builder
     */
    public CatViews(CatManagementViewBuilder managementBuilder, CatDisplayViewBuilder displayBuilder) {
        // Management views
        this.adoptionView = managementBuilder.getAdoptionView();
        this.runawayCatView = managementBuilder.getRunawayCatView();

        // Display views
        this.displayCatImageView = displayBuilder.getDisplayCatImageView();
    }

    // View getters
    public AdoptionView getAdoptionView() {
        return adoptionView;
    }

    public RunawayCatView getRunawayCatView() {
        return runawayCatView;
    }

    public DisplayCatImageView getDisplayCatImageView() {
        return displayCatImageView;
    }
}
