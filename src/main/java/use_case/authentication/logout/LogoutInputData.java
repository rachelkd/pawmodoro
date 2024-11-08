package use_case.authentication.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    private String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

}
