package use_case.timer;

/**
 * Input boundary for timer operations.
 */
public interface TimerInputBoundary {
    /**
     * Executes the timer operation specified in the input data.
     * 
     * @param timerInputData the input data containing the timer action
     */
    void execute(TimerInputData timerInputData);
}

