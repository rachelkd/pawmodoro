package view;

import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.InventoryViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View when user is viewing contents of their inventory.
 */
public class InventoryView extends JPanel implements ActionListener {
    private final InventoryViewModel viewModel;
    private final CreateInventoryController createInventoryController;
    // private final AddToInventoryController addToInventoryController;

    /**
     * Creates the Inventory View.
     * @param viewModel the view model
     * @param createInventoryController the controller
     */
    public InventoryView(InventoryViewModel viewModel, CreateInventoryController createInventoryController) {
        this.viewModel = viewModel;
        // use diff inventory controller?
        this.createInventoryController = createInventoryController;

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
}
