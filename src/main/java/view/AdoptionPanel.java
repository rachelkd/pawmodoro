package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import constants.Constants;
import interface_adapter.adoption.AdoptionViewModel;

/**
 * A panel for the adoption view.
 */
public class AdoptionPanel extends JPanel {
    private static final int INDENT = 10;

    private final JPanel mainPanel;
    private final JPanel informationPanel;
    private final JPanel finishPanel;
    private final JPanel adoptionCompletePanel;

    private final JLabel name;
    private final JLabel congratulationsMessage;
    private final JTextField nameField;
    private final JButton confirmButton;
    private final JButton cancelButton;
    private final JButton returnButton;

    public AdoptionPanel(AdoptionViewModel adoptionViewModel) {
        mainPanel = new JPanel();
        informationPanel = new JPanel();
        finishPanel = new JPanel();
        adoptionCompletePanel = new JPanel();

        name = new JLabel(AdoptionViewModel.NAME_LABEL);
        congratulationsMessage = new JLabel(AdoptionViewModel.ADOPTION_DONE_LABEL);
        nameField = new JTextField(Constants.COLUMN_SIZE);
        confirmButton = new JButton(AdoptionViewModel.CONFIRM_BUTTON_LABEL);
        cancelButton = new JButton(AdoptionViewModel.CANCEL_BUTTON_LABEL);
        returnButton = new JButton(AdoptionViewModel.RETURN_LABEL);

        setupPanel();
    }

    private void setupPanel() {
        mainPanel.setPreferredSize(new Dimension(Constants.ADOPTION_VIEW_WIDTH, Constants.ADOPTION_VIEW_HEIGHT));
        mainPanel.setLayout(new BorderLayout());
        final Border border = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(border);
        
        final JLabel title = new JLabel(AdoptionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, INDENT, 0, 0));

        informationPanel.add(name);
        informationPanel.add(nameField);
        finishPanel.add(confirmButton);
        finishPanel.add(cancelButton);
        adoptionCompletePanel.add(congratulationsMessage);
        adoptionCompletePanel.add(returnButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(informationPanel, BorderLayout.CENTER);
        mainPanel.add(finishPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getInformationPanel() {
        return informationPanel;
    }

    public JPanel getFinishPanel() {
        return finishPanel;
    }

    public JPanel getAdoptionCompletePanel() {
        return adoptionCompletePanel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }
}
