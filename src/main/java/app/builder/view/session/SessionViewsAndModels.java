package app.builder.view.session;

/**
 * Container that combines session-related views and view models.
 */
public class SessionViewsAndModels {
    private final SessionViews views;
    private final SessionViewModels viewModels;

    public SessionViewsAndModels(SessionViews views, SessionViewModels viewModels) {
        this.views = views;
        this.viewModels = viewModels;
    }

    public SessionViews getViews() {
        return views;
    }

    public SessionViewModels getViewModels() {
        return viewModels;
    }
}
