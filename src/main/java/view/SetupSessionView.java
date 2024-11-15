package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionState;
import interface_adapter.setupsession.SetupSessionViewModel;

/**
 * The view for when the user is setting up their study session
 */
public class SetupSessionView extends JPanel implements ActionListener {
    private final String viewName = "setup study session";
    private final SetupSessionViewModel setupSessionViewModel;
    String[] studyChoices = {"10 mins", "15 mins", "20 mins", "25 mins", "30 mins", "35 mins", "40 mins", "45 mins",
            "50 mins", "55 mins", "60 mins"};
    String[] breakChoices = {"5 mins", "10 mins"};
    final JComboBox<String> studytime = new JComboBox<String>(studyChoices);
    final JComboBox<String> breaktime = new JComboBox<String>(breakChoices);
    private final JButton returnButton;

    private SetupSessionController setupSessionController;

    public SetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;

        final JLabel title = new JLabel(SetupSessionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel studyPanel = new JPanel();
        studyPanel.add(new JLabel(SetupSessionViewModel.STUDY_LABEL));
        studyPanel.add(studytime);

        final JPanel breakPanel = new JPanel();
        breakPanel.add(new JLabel(SetupSessionViewModel.BREAK_LABEL));
        breakPanel.add(breaktime);


        final JPanel buttons = new JPanel();
        returnButton = new JButton(SetupSessionViewModel.RETURN_BUTTON_LABEL);
        buttons.add(returnButton);

        returnButton.addActionListener(this);

        addBreakTimeListener();
        addStudyTimeListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JLabel(SetupSessionViewModel.INSTRUCTIONS_LABEL));
        this.add(studyPanel);
        this.add(breakPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(returnButton)) {
            final SetupSessionState currentState = setupSessionViewModel.getState();

            setupSessionController.execute(
                    currentState.getBreakTime(),
                    currentState.getStudyTime()
            );
        }
    }


    public String getViewName() {
        return viewName;
    }

    public void setSetupSessionController(SetupSessionController setupSessionController) {
        this.setupSessionController = setupSessionController;
    }

    private void addStudyTimeListener() {
        studytime.addActionListener(e -> {
            SetupSessionState currentState = setupSessionViewModel.getState();
            currentState.setStudyTime((String) studytime.getSelectedItem());
            setupSessionViewModel.setState(currentState);
        });
    }

    private void addBreakTimeListener() {
        breaktime.addActionListener(e -> {
            SetupSessionState currentState = setupSessionViewModel.getState();
            currentState.setBreakTime((String) studytime.getSelectedItem());
            setupSessionViewModel.setState(currentState);
        });
    }
}
