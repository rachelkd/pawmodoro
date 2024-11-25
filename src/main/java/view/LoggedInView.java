package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constants.Constants;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private static final String VIEW_NAME = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private JLabel username;

    private JButton logOut;

    private final JTextField passwordInputField = new JTextField(15);
    private JButton changePassword;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        initializeLayout();
        setupComponents();
        addListeners();
    }

    /**
     * Initializes the layout for the logged in view.
     */
    private void initializeLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets up all UI components and adds them to the view.
     */
    private void setupComponents() {
        final JLabel pawmodoro = createTitleLabel();
        final JPanel usernamePanel = createUsernamePanel();
        final LabelTextPanel passwordInfo = createPasswordPanel();
        final JPanel buttons = createButtonPanel();

        addComponentsToView(pawmodoro, usernamePanel, passwordInfo, buttons);
    }

    /**
     * Creates the title label with proper styling.
     * 
     * @return styled title label
     */
    private JLabel createTitleLabel() {
        final JLabel pawmodoro = new JLabel("Pawmodoro");
        pawmodoro.setAlignmentX(Component.CENTER_ALIGNMENT);
        pawmodoro.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));
        pawmodoro.setForeground(Color.PINK);
        return pawmodoro;
    }

    /**
     * Creates the panel containing username greeting.
     * 
     * @return panel with username greeting
     */
    private JPanel createUsernamePanel() {
        final JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        final JLabel usernameGreeting = new JLabel("Hello, ");
        username = new JLabel();
        final JLabel usernameExclamation = new JLabel("!");

        usernamePanel.add(usernameGreeting);
        usernamePanel.add(username);
        usernamePanel.add(usernameExclamation);

        return usernamePanel;
    }

    /**
     * Creates the panel containing password input field.
     * 
     * @return panel with password input
     */
    private LabelTextPanel createPasswordPanel() {
        return new LabelTextPanel(new JLabel("Password"), passwordInputField);
    }

    /**
     * Creates a panel containing all buttons.
     * 
     * @return panel with buttons
     */
    private JPanel createButtonPanel() {
        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        changePassword = new JButton("Change Password");
        buttons.add(logOut);
        buttons.add(changePassword);
        return buttons;
    }

    /**
     * Adds all components to the view with proper spacing.
     * 
     * @param pawmodoro title label
     * @param usernamePanel panel with username greeting
     * @param passwordInfo panel with password input
     * @param buttons panel with buttons
     */
    private void addComponentsToView(JLabel pawmodoro, JPanel usernamePanel,
            LabelTextPanel passwordInfo, JPanel buttons) {
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(pawmodoro);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(usernamePanel);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * Sets up all listeners for buttons and input fields.
     */
    private void addListeners() {
        setupPasswordListener();
        setupChangePasswordButtonListener();
        setupLogoutButtonListener();
    }

    /**
     * Sets up the listener for the password input field.
     */
    private void setupPasswordListener() {
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
    }

    /**
     * Sets up the listener for the change password button.
     */
    private void setupChangePasswordButtonListener() {
        changePassword.addActionListener(evt -> {
            if (evt.getSource().equals(changePassword)) {
                final LoggedInState currentState = loggedInViewModel.getState();
                changePasswordController.execute(
                        currentState.getUsername(),
                        currentState.getPassword());
            }
        });
    }

    /**
     * Sets up the listener for the logout button.
     */
    private void setupLogoutButtonListener() {
        logOut.addActionListener(evt -> {
            if (evt.getSource().equals(logOut)) {
                final LoggedInState currentState = loggedInViewModel.getState();
                logoutController.execute(currentState.getUsername());
                clearPasswordField();
            }
        });
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
        return VIEW_NAME;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    /**
     * Clears the password field (for logging out).
     */
    public void clearPasswordField() {
        passwordInputField.setText("");
    }
}
