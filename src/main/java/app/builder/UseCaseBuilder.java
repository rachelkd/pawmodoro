package app.builder;

import app.builder.usecase.AuthUseCaseBuilder;
import app.builder.usecase.CatUseCaseBuilder;
import app.builder.usecase.InventoryUseCaseBuilder;
import app.builder.usecase.SessionUseCaseBuilder;
import app.builder.usecase.TimerUseCaseBuilder;
import app.builder.view.Views;
import app.components.DataAccessComponents;

/**
 * Main builder that coordinates all use case builders.
 */
public class UseCaseBuilder {
    private final Views views;
    private final DataAccessComponents dataAccess;

    public UseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        this.views = views;
        this.dataAccess = dataAccess;
    }

    /**
     * Builds all use cases.
     *
     * @return this builder
     */
    public UseCaseBuilder build() {
        final AuthUseCaseBuilder authBuilder = new AuthUseCaseBuilder(views, dataAccess);
        final InventoryUseCaseBuilder inventoryBuilder = new InventoryUseCaseBuilder(views, dataAccess);
        final CatUseCaseBuilder catBuilder = new CatUseCaseBuilder(views, dataAccess);
        final SessionUseCaseBuilder sessionBuilder = new SessionUseCaseBuilder(views, dataAccess);
        final TimerUseCaseBuilder timerBuilder = new TimerUseCaseBuilder(views, dataAccess);

        authBuilder.build();
        inventoryBuilder.build();
        catBuilder.build();
        timerBuilder.build();
        // TODO: Add session use cases

        return this;
    }
}
