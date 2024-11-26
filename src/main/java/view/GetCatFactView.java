package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import interface_adapter.get_cat_fact.GetCatFactController;
import interface_adapter.get_cat_fact.GetCatFactState;
import interface_adapter.get_cat_fact.GetCatFactViewModel;

/**
 * Panel for displaying cat facts.
 */
public class GetCatFactView extends JPanel implements PropertyChangeListener {
    private final GetCatFactViewModel getCatFactViewModel;
    private GetCatFactController getCatFactController;
    private JLabel factLabel;

    public GetCatFactView(GetCatFactViewModel getCatFactViewModel) {
        this.getCatFactViewModel = getCatFactViewModel;
        this.getCatFactViewModel.addPropertyChangeListener(this);

        createAndAddComponents();

        // Update with initial state if available
        final GetCatFactState currentState = getCatFactViewModel.getState();
        if (currentState != null) {
            updateView(currentState);
        }
    }

    private void createAndAddComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title label
        final JLabel titleLabel = new JLabel(Constants.CAT_FACT_TITLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        // Padding
        add(Box.createVerticalStrut(Constants.DISPLAY_CAT_STATS_PADDING / 2));

        // Create fact label panel
        final JPanel factPanel = new JPanel();
        factPanel.setLayout(new BoxLayout(factPanel, BoxLayout.X_AXIS));
        factPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create fact label
        factLabel = new JLabel(Constants.CAT_FACT_LOADING);
        factLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        factLabel.setHorizontalAlignment(JLabel.CENTER);
        factPanel.add(factLabel);

        add(factPanel);
    }

    private void updateView(GetCatFactState state) {
        if (state.getError() != null) {
            factLabel.setText(Constants.CAT_FACT_ERROR_PREFIX + state.getError());
        }
        else if (state.getCatFact() != null) {
            factLabel.setText("<html><div style='width: "
                    + (Constants.CAT_FACT_TEXT_WIDTH)
                    + "px; text-align: center'>" + state.getCatFact() + "</div></html>");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final GetCatFactState state = (GetCatFactState) evt.getNewValue();
            updateView(state);
        }
    }

    public void setGetCatFactController(GetCatFactController controller) {
        this.getCatFactController = controller;
    }

    /**
     * Gets the view name.
     *
     * @return the view name
     */
    public String getViewName() {
        return getCatFactViewModel.getViewName();
    }

    /**
     * Fetches a new cat fact using the controller.
     */
    public void fetchNewFact() {
        if (getCatFactController != null) {
            getCatFactController.execute();
        }
    }
}
