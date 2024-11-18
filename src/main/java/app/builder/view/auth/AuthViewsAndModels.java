package app.builder.view.auth;

/**
 * Container that combines auth-related views and view models.
 */
public class AuthViewsAndModels {
    private final AuthViews views;
    private final AuthViewModels viewModels;

    public AuthViewsAndModels(AuthViews views, AuthViewModels viewModels) {
        this.views = views;
        this.viewModels = viewModels;
    }

    public AuthViews getViews() {
        return views;
    }

    public AuthViewModels getViewModels() {
        return viewModels;
    }
}
