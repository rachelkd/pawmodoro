package use_case.setupsession;

/**
 * Input Boundary for actions which are related to setting up the study session.
 *
 */
public interface SetupSessionInputBoundary {

    /**
     * Executes the setup session use case.
     *
     * @param setupSessionInputData the input data
     */
    void execute(SetupSessionInputData setupSessionInputData);

    /**
     * Executes the switch to the studying use case.
     */
    void switchToStudyView();

    /**
     * Handles the passing of Inputdata.
     * @param setupSessionInputData the seteupSessionInputdata
     */
    void handle(SetupSessionInputData setupSessionInputData);
}
