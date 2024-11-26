package app.builder;

import app.builder.usecase.AuthUseCaseBuilder;
import app.builder.usecase.CatDisplayUseCaseBuilder;
import app.builder.usecase.CatManagementUseCaseBuilder;
import app.builder.usecase.SharedUseCaseBuilder;
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
     * @return this builder
     */
    public UseCaseBuilder build() {
        final AuthUseCaseBuilder authBuilder = new AuthUseCaseBuilder(views, dataAccess);
        final SharedUseCaseBuilder sharedBuilder = new SharedUseCaseBuilder(views, dataAccess);
        final CatDisplayUseCaseBuilder catDisplayBuilder = new CatDisplayUseCaseBuilder(views, dataAccess);
        final CatManagementUseCaseBuilder catManagementBuilder = new CatManagementUseCaseBuilder(views, dataAccess);
        final TimerUseCaseBuilder timerBuilder = new TimerUseCaseBuilder(views, dataAccess);

        sharedBuilder.build();
        authBuilder.build();
        catDisplayBuilder.build();
        catManagementBuilder.build();
        timerBuilder.build();

        return this;
    }
}
