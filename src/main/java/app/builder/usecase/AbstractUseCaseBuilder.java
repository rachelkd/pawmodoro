package app.builder.usecase;

import app.components.DataAccessComponents;
import app.builder.view.Views;

/**
 * Base class for all use case builders.
 */
public abstract class AbstractUseCaseBuilder {
    private final Views views;
    private final DataAccessComponents dataAccess;

    /**
     * Creates a new base use case builder.
     *
     * @param views the views
     * @param dataAccess the data access components
     */
    public AbstractUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        this.views = views;
        this.dataAccess = dataAccess;
    }

    /**
     * Gets the views.
     *
     * @return the views
     */
    protected Views getViews() {
        return views;
    }

    /**
     * Gets the data access components.
     *
     * @return the data access components
     */
    protected DataAccessComponents getDataAccess() {
        return dataAccess;
    }
}
