package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.cat.CatViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private final TimerViewModel timerViewModel;
    private final TimerView timerView;
    private TimerController timerController;

    private final JLabel username;

    private final JButton logOut;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private final CatView catView;
    private DisplayCatStatsController displayCatStatsController;

    public LoggedInView(LoggedInViewModel loggedInViewModel, TimerViewModel timerViewModel, CatViewModel catViewModel,
            DisplayCatStatsViewModel displayCatStatsViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.timerViewModel = timerViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.timerViewModel.addPropertyChangeListener(this);

        final JLabel pawmodoro = new JLabel("Pawmodoro");
        pawmodoro.setAlignmentX(Component.CENTER_ALIGNMENT);
        pawmodoro.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));
        pawmodoro.setForeground(Color.PINK);

        // final JLabel title = new JLabel("Logged In Screen");
        // title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        // Create a panel for the username greeting
        final JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        final JLabel usernameGreeting = new JLabel("Hello, ");
        username = new JLabel();
        final JLabel usernameExclamation = new JLabel("!");
        usernamePanel.add(usernameGreeting);
        usernamePanel.add(username);
        usernamePanel.add(usernameExclamation);

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword());
                    }
                });

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // Execute the logout use case through the Controller
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        final LoggedInState currentState = loggedInViewModel.getState();
                        this.logoutController.execute(currentState.getUsername());
                        clearPasswordField();
                    }
                });

        // Create timer view component
        this.timerView = new TimerView(timerViewModel);

        // Create CatView
        this.catView = new CatView(catViewModel, displayCatStatsViewModel, new DialogService());

        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(pawmodoro);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(timerView);
        this.add(catView);
        this.add(usernamePanel);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            // returns value of property when change occurs
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
        // Timer-related property change is handled by TimerView
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    /**
     * Clears the password field (for logging out).
     */
    public void clearPasswordField() {
        passwordInputField.setText("");
    }

    /**
     * Sets the display cat stats controller.
     * @param controller the display cat stats controller
     */
    public void setDisplayCatStatsController(DisplayCatStatsController controller) {
        this.displayCatStatsController = controller;
        this.catView.setDisplayCatStatsController(controller);
    }

    /**
     * Adds a cat image view to this panel.
     *
     * @param displayCatImageView the cat image view to add
     */
    public void addCatImageView(DisplayCatImageView displayCatImageView) {
        add(displayCatImageView);
    }
}
