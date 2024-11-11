package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// import interface_adapter.adoption.AdoptionController;
// import interface_adapter.adoption.AdoptionState;
// import interface_adapter.adoption.AdoptionViewModel;

/**
 * The view for the Adopting a cat use case.
 */
public class AdoptionView extends JPanel implements ActionListener, PropertyChangeListener {
    // private final AdoptionViewModel adoptionViewModel;
    private final JLabel name = new JLabel("Enter your new cat's name: ");
    private final JTextField nameField = new JTextField(15);
    private final String confirm = "Adopt now!";
    private final JButton confirmButton = new JButton(confirm);

    // public AdoptionView(AdoptionViewModel adoptionViewModel) {
    // this.adoptionViewModel = adoptionViewModel;
    // JPanel information = new JPanel();
    // information.add(name);
    // information.add(nameField);
    // final JPanel finish = new JPanel();
    // finish.add(confirmButton);

    // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // this.add(information);
    // this.add(finish);

    // confirmButton.addActionListener(this);
    // }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
