package use_case.setupsession;

/**
 * Output boundary interface for the SetupSession use case.
 * This interface defines the methods that the presenter must implement
 * to handle the output from the SetupSession use case.
 */
public interface SetupSessionOutputBoundary {
    /**
     * Called when the setup is successful and the system should
     * prepare to switch to the study timer view.
     *
     * @param outputData the Output data for the Use Case.
     */
    void prepareSuccessView(SetupSessionOutputData outputData);

    /**
     * Called when the system needs to switch to the study view.
     */
    void switchToStudyView();
}
