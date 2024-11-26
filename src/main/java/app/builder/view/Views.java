package app.builder.view;

import app.builder.view.auth.AuthViewsAndModels;
import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewsAndModels;
import app.builder.view.shared.SharedViewsAndModels;
import interface_adapter.ViewManagerModel;

/**
 * Container for all views and view models in the application.
 */
public class Views {
    private final AuthViewsAndModels auth;
    private final CatViewsAndModels cat;
    private final SessionViewsAndModels session;
    private final SharedViewsAndModels shared;
    private final ViewManagerModel viewManagerModel;

    public Views(AuthViewsAndModels auth,
            CatViewsAndModels cat,
            SessionViewsAndModels session,
            SharedViewsAndModels shared,
            ViewManagerModel viewManagerModel) {
        this.auth = auth;
        this.cat = cat;
        this.session = session;
        this.shared = shared;
        this.viewManagerModel = viewManagerModel;
    }

    public AuthViewsAndModels getAuth() {
        return auth;
    }

    public CatViewsAndModels getCat() {
        return cat;
    }

    public SessionViewsAndModels getSession() {
        return session;
    }

    public SharedViewsAndModels getShared() {
        return shared;
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
