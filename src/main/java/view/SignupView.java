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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constants.Constants;
import interface_adapter.create_cat.CreateCatController;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final String VIEW_NAME = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;
    private CreateCatController createCatController;

    private JButton signUp;
    private JButton toLogin;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        initializeLayout();
        setupComponents();
        addListeners();
    }

    /**
     * Initializes the layout for the signup view.
     */
    private void initializeLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets up all UI components and adds them to the view.
     */
    private void setupComponents() {
        final JLabel pawmodoro = createTitleLabel();
        final JLabel title = createSubtitleLabel();
        final JPanel inputPanel = createInputPanel();
        final JPanel buttons = createButtonPanel();

        addComponentsToView(pawmodoro, title, inputPanel, buttons);
    }

    /**
     * Creates the title label with proper styling.
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
     * Creates the subtitle label.
     * @return subtitle label
     */
    private JLabel createSubtitleLabel() {
        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    /**
     * Creates a panel containing all input fields.
     * @return panel with input fields
     */
    private JPanel createInputPanel() {
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(usernameInfo);
        panel.add(passwordInfo);
        panel.add(repeatPasswordInfo);
        return panel;
    }

    /**
     * Creates a panel containing all buttons.
     * @return panel with buttons
     */
    private JPanel createButtonPanel() {
        final JPanel buttons = new JPanel();
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(toLogin);
        buttons.add(signUp);
        return buttons;
    }

    /**
     * Adds all components to the view with proper spacing.
     * @param pawmodoro title label
     * @param title subtitle label
     * @param inputPanel panel containing input fields
     * @param buttons panel containing buttons
     */
    private void addComponentsToView(JLabel pawmodoro, JLabel title, JPanel inputPanel, JPanel buttons) {
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(pawmodoro);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(title);
        this.add(inputPanel);
        this.add(buttons);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
    }

    /**
     * Sets up all listeners for buttons and input fields.
     */
    private void addListeners() {
        setupSignUpButtonListener();
        setupLoginButtonListener();
        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();
    }

    /**
     * Sets up the listener for the sign up button.
     */
    private void setupSignUpButtonListener() {
        signUp.addActionListener(evt -> {
            if (evt.getSource().equals(signUp)) {
                final SignupState currentState = signupViewModel.getState();
                signupController.execute(
                        currentState.getUsername(),
                        currentState.getPassword(),
                        currentState.getRepeatPassword());
            }
        });
    }

    /**
     * Sets up the listener for the login button.
     */
    private void setupLoginButtonListener() {
        toLogin.addActionListener(evt -> signupController.switchToLoginView());
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }

    public void setCreateCatController(CreateCatController controller) {
        this.createCatController = controller;
    }
}
