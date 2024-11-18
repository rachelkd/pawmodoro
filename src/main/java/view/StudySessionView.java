package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import constants.Constants;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerViewModel;

/**
 * Views for Study sessions.
 */
public class StudySessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "study session";

    private final TimerViewModel timerViewModel;
    private final TimerView timerView;
    private final StudySessionViewModel studySessionViewModel;
    private TimerController timerController;
    private final JButton settings;

    public StudySessionView(StudySessionViewModel studySessionViewModel, TimerViewModel timerViewModel) {
        this.timerViewModel = timerViewModel;
        timerViewModel.addPropertyChangeListener(this);
        this.studySessionViewModel = studySessionViewModel;
        studySessionViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("STUDY SESSION");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));

        final JPanel buttons = new JPanel();
        settings = new JButton("User Settings");
        buttons.add(settings);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        settings.addActionListener(this);

        this.timerView = new TimerView(timerViewModel);
        this.add(buttons);
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
        this.add(timerView);
    }

    public String getViewName() {
        return viewName;
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("settings clicked");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("settings changed");
    }
}
