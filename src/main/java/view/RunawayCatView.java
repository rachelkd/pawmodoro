package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.swing.border.Border;

import constants.Constants;
import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayCatState;
import interface_adapter.runawaycat.RunawayCatViewModel;

/**
 * A view component that displays a message when a cat runs away.
 * This panel shows the cat's name and a reminder message about cat care,
 * along with a confirmation button.
 */
public class RunawayCatView extends JDialog implements ActionListener, PropertyChangeListener {
    private final String viewName = "runaway cat";
    private final String badNews = "Oh no, %s has ran away!";
    private final JButton confirm = new JButton(RunawayCatViewModel.CONFIRM_LABEL);
    private final JLabel news = new JLabel();
    private final JLabel reminders = new JLabel(RunawayCatViewModel.REMINDER_LABEL);
    private final JLabel name = new JLabel(RunawayCatViewModel.TITLE_LABEL);
    private final RunawayCatViewModel runawayCatViewModel;
    private RunawayCatController runawayCatController;

    private final JPanel mainPanel;
    private final JPanel buttons;
    private final JPanel messages;
    private final JPanel title;

    /**
     * Creates a new RunawayCatView
     * @param parent the application
     * @param runawayCatViewModel the view model for the runaway cat use case
     */
    public RunawayCatView(JFrame parent, RunawayCatViewModel runawayCatViewModel) {
        this.runawayCatViewModel = runawayCatViewModel;

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(Constants.RUNAWAYCAT_VIEW_WIDTH, Constants.RUNAWAYCAT_VIEW_HEIGHT));
        mainPanel.setLayout(new BorderLayout());
        final Border border = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(border);

        this.title = new JPanel();
        title.add(name);

        this.messages = new JPanel();
        messages.add(news);
        messages.add(reminders);

        this.buttons = new JPanel();
        buttons.add(confirm);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(messages, BorderLayout.CENTER);
        mainPanel.add(buttons, BorderLayout.SOUTH);

        confirm.addActionListener(this);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(parent);

        runawayCatViewModel.addPropertyChangeListener(this);
        updateMessage();
    }

    private void updateMessage() {
        final RunawayCatState state = runawayCatViewModel.getState();
        news.setText(String.format(badNews, state.getCatName()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            final RunawayCatState currentState = runawayCatViewModel.getState();
            runawayCatController.execute(currentState.getCatName(), currentState.getOwnerName());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateMessage();
    }

    public String getViewName() {
        return viewName;
    }

    public void setRunawayCatController(RunawayCatController runawayCatController) {
        this.runawayCatController = runawayCatController;
    }
}
