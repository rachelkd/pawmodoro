package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;

/**
 * Builder for session-related use cases.
 */
public class SessionUseCaseBuilder {
    private final Views views;
    private final DataAccessComponents dataAccess;

    public SessionUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        this.views = views;
        this.dataAccess = dataAccess;
    }
    // TODO: What use cases do we have in the study session view?
}
