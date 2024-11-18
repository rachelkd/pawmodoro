package app.builder.view.cat;

/**
 * Container that combines cat-related views and view models.
 */
public class CatViewsAndModels {
    private final CatViews views;
    private final CatViewModels viewModels;

    public CatViewsAndModels(CatViews views, CatViewModels viewModels) {
        this.views = views;
        this.viewModels = viewModels;
    }

    public CatViews getViews() {
        return views;
    }

    public CatViewModels getViewModels() {
        return viewModels;
    }
}
