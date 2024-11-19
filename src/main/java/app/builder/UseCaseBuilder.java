package app.builder;

import app.builder.usecase.AuthUseCaseBuilder;
import app.builder.usecase.CatUseCaseBuilder;
import app.builder.usecase.InventoryUseCaseBuilder;
import app.builder.usecase.TimerUseCaseBuilder;
import app.builder.view.Views;
import app.components.DataAccessComponents;

/**
 * Main builder that coordinates all use case builders.
 */
public class UseCaseBuilder {
    private final AuthUseCaseBuilder authBuilder;
    private final InventoryUseCaseBuilder inventoryBuilder;
    private final CatUseCaseBuilder catBuilder;
    private final TimerUseCaseBuilder timerBuilder;

    public UseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        this.authBuilder = new AuthUseCaseBuilder(views, dataAccess);
        this.inventoryBuilder = new InventoryUseCaseBuilder(views, dataAccess);
        this.catBuilder = new CatUseCaseBuilder(views, dataAccess);
        this.timerBuilder = new TimerUseCaseBuilder(views, dataAccess);
    }

    /**
     * Builds all use cases.
     *
     * @return this builder
     */
    public UseCaseBuilder build() {
        authBuilder.buildLoginUseCase();
        authBuilder.buildSignupUseCase();
        authBuilder.buildLogoutUseCase();
        authBuilder.buildChangePasswordUseCase();

        inventoryBuilder.buildInventoryUseCases();
        catBuilder.buildCatUseCases();
        timerBuilder.buildTimerUseCases();

        return this;
    }
}
