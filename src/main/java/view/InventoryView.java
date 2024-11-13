package view;

import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.InventoryState;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View when user is viewing contents of their inventory.
 */
public class InventoryView extends JPanel implements ActionListener, PropertyChangeListener {
    private final InventoryViewModel viewModel;
    private CreateInventoryController createInventoryController;
    private AddToInventoryController addToInventoryController;
    private UseItemController useItemController;

    /**
     * Creates the Inventory View.
     * @param viewModel the view model
     */
    public InventoryView(InventoryViewModel viewModel) {
        this.viewModel = viewModel;


        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 200));
        Border border = BorderFactory.createLineBorder(Color.black);
        this.setBorder(border);

        final JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.add(title, BorderLayout.NORTH);

        // inventory initialized empty, but could be full once database :C
        JPanel inventoryPanel = new JPanel(new GridBagLayout());
        final JLabel items = new JLabel(viewModel.EMPTY_INVENTORY_LABEL);
        items.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventoryPanel.add(items);
        this.add(inventoryPanel, BorderLayout.CENTER);
    }

    // when add to inventory use case, update view

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "HELPPPPP");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("create_inventory")) {
            final InventoryState state = (InventoryState) evt.getNewValue();

        }
        else if (evt.getPropertyName().equals("inventory_add")) {
            final InventoryState state = (InventoryState) evt.getNewValue();
            // JOptionPane.showMessageDialog(null, "item added to inventory for " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("inventory_item_used")) {
            final InventoryState state = (InventoryState) evt.getNewValue();
            // JOptionPane.showMessageDialog(null, "item used for " + state.getUsername());
        }
    }

    public void setCreateInventoryController(CreateInventoryController createInventoryController) {
        this.createInventoryController = createInventoryController;
    }

    public void setAddToInventoryController(AddToInventoryController addToInventoryController) {
        this.addToInventoryController = addToInventoryController;
    }

    public void setUseItemController(UseItemController useItemController) {
        this.useItemController = useItemController;
    }
}
