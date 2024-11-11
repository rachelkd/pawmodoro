package use_case.timer;

/**
 * Input data for Timer Use Cases.
 */
public class TimerInputData {
    private final String username;

    public TimerInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
