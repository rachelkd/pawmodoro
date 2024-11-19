package app.builder.view;

import app.builder.view.auth.AuthViewsAndModels;
import app.builder.view.cat.CatViewsAndModels;
import app.builder.view.session.SessionViewsAndModels;
import interface_adapter.ViewManagerModel;

/**
 * Container for all views and view models in the application.
 */
public class Views {
    private final AuthViewsAndModels auth;
    private final CatViewsAndModels cat;
    private final SessionViewsAndModels session;
    private final ViewManagerModel viewManagerModel;

    public Views(AuthViewsAndModels auth,
            CatViewsAndModels cat,
            SessionViewsAndModels session,
            ViewManagerModel viewManagerModel) {
        this.auth = auth;
        this.cat = cat;
        this.session = session;
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

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
