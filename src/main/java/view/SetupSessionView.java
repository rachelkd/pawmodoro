package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionState;
import interface_adapter.setupsession.SetupSessionViewModel;

/**
 * The view for when the user is setting up their study session.
 */
public class SetupSessionView extends JPanel implements ActionListener {
    private final SetupSessionViewModel setupSessionViewModel;
    private String[] studyChoices = {"1 min", "10 mins", "15 mins", "20 mins", "25 mins", "30 mins",
        "35 mins", "40 mins", "45 mins", "50 mins", "55 mins", "60 mins"};
    private String[] breakChoices = {"1 min", "5 mins", "10 mins"};
    private final JComboBox<String> studytime = new JComboBox<String>(studyChoices);
    private final JComboBox<String> breaktime = new JComboBox<String>(breakChoices);
    private JButton returnButton;

    private SetupSessionController setupSessionController;

    public SetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initializeTitle();
        initializeInstructions();
        initializeStudyPanel();
        initializeBreakPanel();
        initializeButtons();

        addBreakTimeListener();
        addStudyTimeListener();
    }

    private void initializeTitle() {
        final JLabel title = new JLabel(SetupSessionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.SETUP_SESSION_TITLE_FONT_SIZE));
        this.add(title);
    }

    private void initializeInstructions() {
        final JLabel instructions = new JLabel(SetupSessionViewModel.INSTRUCTIONS_LABEL);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.SETUP_SESSION_NORMAL_FONT_SIZE));
        this.add(instructions, BorderLayout.NORTH);
    }

    private void initializeStudyPanel() {
        final JPanel studyPanel = new JPanel();
        final JLabel studyLabel = new JLabel(SetupSessionViewModel.STUDY_LABEL);
        studyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        studyLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.SETUP_SESSION_NORMAL_FONT_SIZE));
        studyPanel.add(studyLabel);
        studyPanel.add(studytime);
        this.add(studyPanel);
    }

    private void initializeBreakPanel() {
        final JPanel breakPanel = new JPanel();
        final JLabel breakLabel = new JLabel(SetupSessionViewModel.BREAK_LABEL);
        breakLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        breakLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.SETUP_SESSION_NORMAL_FONT_SIZE));
        breakPanel.add(breakLabel);
        breakPanel.add(breaktime);
        this.add(breakPanel);
    }

    private void initializeButtons() {
        final JPanel buttons = new JPanel();
        returnButton = new JButton(SetupSessionViewModel.RETURN_BUTTON_LABEL);
        returnButton.addActionListener(this::handleReturnButton);
        buttons.add(returnButton);
        this.add(buttons, BorderLayout.SOUTH);
    }

    private void handleReturnButton(ActionEvent evt) {
        final SetupSessionState currentState = setupSessionViewModel.getState();
        setupSessionController.execute(
                currentState.getStudyTime(),
                currentState.getBreakTime());
        setupSessionController.switchToStudyView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(returnButton)) {
            final SetupSessionState currentState = setupSessionViewModel.getState();

            setupSessionController.execute(
                    currentState.getBreakTime(),
                    currentState.getStudyTime());
        }
    }

    public void setSetupSessionController(SetupSessionController setupSessionController) {
        this.setupSessionController = setupSessionController;
    }

    private void addStudyTimeListener() {
        studytime.addActionListener(evt -> {
            final SetupSessionState currentState = setupSessionViewModel.getState();
            currentState.setStudyTime((String) studytime.getSelectedItem());
            setupSessionViewModel.setState(currentState);
        });
    }

    private void addBreakTimeListener() {
        breaktime.addActionListener(evt -> {
            final SetupSessionState currentState = setupSessionViewModel.getState();
            currentState.setBreakTime((String) breaktime.getSelectedItem());
            setupSessionViewModel.setState(currentState);
        });
    }

    public String getViewName() {
        return setupSessionViewModel.getViewName();
    }
}
