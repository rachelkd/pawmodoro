package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayCatState;
import interface_adapter.runawaycat.RunawayCatViewModel;

/**
 * The View for the Runaway Cat Use Case.
 */

public class RunawayCatView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String badNews = "Oh no, <name> has ran away!";
    private final String reminder = "Remember to monitor your cats' health and happiness!";
    private final JButton confirm = new JButton("Okay..");
    private final JLabel news = new JLabel(badNews);
    private final JLabel reminders = new JLabel(reminder);

    public RunawayCatView(RunawayCatViewModel runawayCatViewModel) {
        this.runawayCatViewModel = runawayCatViewModel;
        JPanel messages = new JPanel();
        messages.add(news);
        messages.add(reminders);
        JPanel buttons = new JPanel();
        buttons.add(confirm);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(messages);
        this.add(buttons);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
