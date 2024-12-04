package app.builder.usecase;

import app.builder.view.Views;
import app.components.DataAccessComponents;

/**
 * Builder for timer-related use cases.
 */
public class TimerUseCaseBuilder extends AbstractUseCaseBuilder {
    private final SetupSessionUseCaseBuilder setupBuilder;
    private final StudySessionUseCaseBuilder studyBuilder;
    private final BreakSessionUseCaseBuilder breakBuilder;

    public TimerUseCaseBuilder(Views views, DataAccessComponents dataAccess) {
        super(views, dataAccess);
        this.setupBuilder = new SetupSessionUseCaseBuilder(views);
        this.studyBuilder = new StudySessionUseCaseBuilder(views);
        this.breakBuilder = new BreakSessionUseCaseBuilder(views);
    }

    /**
     * Builds all timer-related use cases.
     *
     * @return this builder
     */
    public TimerUseCaseBuilder build() {
        setupBuilder.build();
        studyBuilder.build();
        breakBuilder.build();
        return this;
    }
}
