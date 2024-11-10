package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.setupsession.SetupSessionController;
import interface_adapter.setupsession.SetupSessionState;
import interface_adapter.setupsession.SetupSessionViewModel;

/**
 * The view for when the user is setting up their study session
 */
public class SetupSessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "setup study session";
    private final SetupSessionViewModel setupSessionViewModel;
    String[] studyChoices = {"10 mins", "15 mins", "20 mins", "25 mins", "30 mins", "35 mins", "40 mins", "45 mins",
            "50 mins", "55 mins", "60 mins"};
    String[] breakChoices = {"5 mins", "10 mins"};
    final JComboBox<String> studytime = new JComboBox<String>(studyChoices);
    final JComboBox<String> breaktime = new JComboBox<String>(breakChoices);
    private final String instructions = "Choose your study intervals:";
    private final JLabel instructionsLabel = new JLabel(instructions);
    private final JLabel studymsg = new JLabel("Study: ");
    private final JLabel breakmsg = new JLabel("Break: ");
    private final JButton logoutButton = new JButton("Log out");
    private final JButton start = new JButton("Start Studying!");

    public SetupSessionView(SetupSessionViewModel setupSessionViewModel) {
        this.setupSessionViewModel = setupSessionViewModel;
        this.setupSessionViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Setup Study Session Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel studyPanel = new JPanel();
        studyPanel.add(studymsg);
        studyPanel.add(studytime);

        final JPanel breakPanel = new JPanel();
        breakPanel.add(breakmsg);
        breakPanel.add(breaktime);


        final JPanel buttons = new JPanel();
        buttons.add(logoutButton);
        buttons.add(start);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(instructionsLabel);
        this.add(studyPanel);
        this.add(breakPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
