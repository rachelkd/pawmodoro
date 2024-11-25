package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constants.Constants;
import interface_adapter.create_cat.CreateCatController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final String VIEW_NAME = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel userErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JButton logIn = new JButton("Log in");
    private final JButton backToSignUp = new JButton("Back to sign up");

    private LoginController loginController;
    private CreateCatController createCatController;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel pawmodoro = createPawmodoroLabel();
        final JLabel title = createTitleLabel();

        final LabelTextPanel usernameInfo = createUsernamePanel();
        final LabelTextPanel passwordInfo = createPasswordPanel();
        final JPanel buttons = createButtonPanel();

        configureErrorField();
        setupDocumentListeners();
        setupLayout(pawmodoro, title, usernameInfo, passwordInfo, buttons);
    }

    private JLabel createPawmodoroLabel() {
        final JLabel pawmodoro = new JLabel("Pawmodoro");
        pawmodoro.setAlignmentX(Component.CENTER_ALIGNMENT);
        pawmodoro.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));
        pawmodoro.setForeground(Color.PINK);
        return pawmodoro;
    }

    private JLabel createTitleLabel() {
        final JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private LabelTextPanel createUsernamePanel() {
        return new LabelTextPanel(new JLabel("Username"), usernameInputField);
    }

    private LabelTextPanel createPasswordPanel() {
        return new LabelTextPanel(new JLabel("Password"), passwordInputField);
    }

    private JPanel createButtonPanel() {
        final JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(backToSignUp);

        logIn.addActionListener(this);
        backToSignUp.addActionListener(this);

        return buttons;
    }

    private void configureErrorField() {
        userErrorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        userErrorField.setForeground(Color.RED);
    }

    private void setupDocumentListeners() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
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

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
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

    private void setupLayout(JLabel pawmodoro, JLabel title, LabelTextPanel usernameInfo, LabelTextPanel passwordInfo,
            JPanel buttons) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(pawmodoro);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING / 2)));
        this.add(usernameInfo);
        this.add(userErrorField);
        this.add(passwordInfo);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING / 2)));
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * 
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            // Log in
            final LoginState currentState = loginViewModel.getState();
            loginController.execute(
                    currentState.getUsername(),
                    currentState.getPassword());
        }
        else if (evt.getSource().equals(backToSignUp)) {
            // Switch to the Sign Up View
            loginController.switchToSignUpView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        userErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setCreateCatController(CreateCatController createCatController) {
        this.createCatController = createCatController;
    }
}
