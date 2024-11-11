package use_case.timer;

public class GetTimerInputData {
    private final String username;

    public GetTimerInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
