package interface_adapter.setupsession;

import use_case.setupsession.SetupSessionInputBoundary;
import use_case.setupsession.SetupSessionInputData;

/**
 * Controller for user setting up study session timings use case.
 */

public class SetupSessionController {
    private final SetupSessionInputBoundary userSetupInteractor;

    public SetupSessionController(SetupSessionInputBoundary userSetupInteractor) {
        this.userSetupInteractor = userSetupInteractor;
    }

    /**
     * Executes the setup session use case.
     *
     * @param studyTime study time interval length
     * @param breakTime break time interval length
     */
    public void execute(int studyTime, int breakTime) {
        final SetupSessionInputData setupSessionInputData = new SetupSessionInputData(studyTime, breakTime);
        userSetupInteractor.execute(setupSessionInputData);
    }

    /**
     * Executes the 'switch to study view' Use Case.
     */
    public void switchToStudyView() {
        userSetupInteractor.switchToStudyView();
    }
}
