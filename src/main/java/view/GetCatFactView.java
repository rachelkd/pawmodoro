package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    private final JLabel factLabel;
    private GetCatFactController getCatFactController;

    public GetCatFactView(GetCatFactViewModel getCatFactViewModel) {
        this.getCatFactViewModel = getCatFactViewModel;
        this.getCatFactViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create title label
        final JLabel titleLabel = new JLabel(Constants.CAT_FACT_TITLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Create fact label
        factLabel = new JLabel(Constants.CAT_FACT_LOADING);
        factLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(factLabel);

        // Update with initial state if available
        final GetCatFactState currentState = getCatFactViewModel.getState();
        if (currentState != null) {
            updateView(currentState);
        }
    }

    private void updateView(GetCatFactState state) {
        if (state.getError() != null) {
            factLabel.setText(Constants.CAT_FACT_ERROR_PREFIX + state.getError());
        }
        else if (state.getCatFact() != null) {
            factLabel.setText(state.getCatFact());
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
}
