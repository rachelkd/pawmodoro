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

import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionState;
import interface_adapter.adoption.AdoptionViewModel;

/**
 * The view for the Adopting a cat use case.
 */
public class AdoptionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final AdoptionViewModel adoptionViewModel;
    private final String viewName = "adoption";
    private final JLabel name = new JLabel("Enter your new cat's name: ");
    private final JTextField nameField = new JTextField(15);
    private final String confirm = "Adopt now!";
    private final JButton confirmButton = new JButton(confirm);
    private final JButton cancelButton = new JButton("Cancel");

    /**
     * Creates a new AdoptionView.
     * 
     * @param adoptionViewModel the view model for the adoption use case
     */
    public AdoptionView(AdoptionViewModel adoptionViewModel) {
        this.adoptionViewModel = adoptionViewModel;
        final JPanel information = new JPanel();
        information.add(name);
        information.add(nameField);
        final JPanel finish = new JPanel();
        finish.add(confirmButton);
        finish.add(cancelButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(information);
        this.add(finish);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Implement action handling
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement property change handling
    }

    /**
     * Gets the name of this view.
     * 
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }
}
