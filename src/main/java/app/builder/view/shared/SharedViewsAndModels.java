package app.builder.view.shared;

/**
 * Container for shared views and models.
 */
public class SharedViewsAndModels {
    private final SharedViews views;
    private final SharedViewModels viewModels;

    public SharedViewsAndModels(SharedViews views, SharedViewModels viewModels) {
        this.views = views;
        this.viewModels = viewModels;
    }

    public SharedViews getViews() {
        return views;
    }

    public SharedViewModels getViewModels() {
        return viewModels;
    }
}
