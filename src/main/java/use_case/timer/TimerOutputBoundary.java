package use_case.timer;

/**
 * Output boundary for timer operations.
 */
public interface TimerOutputBoundary {
    /**
     * Prepares the success view with timer data.
     * @param response the output data to present
     */
    void prepareSuccessView(TimerOutputData response);

    /**
     * Prepares the fail view with error message.
     * @param error the error message to present
     */
    void prepareFailView(String error);
} 