package view;

import app.service.DialogService;
import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModelFactory;
import interface_adapter.initialize_cats.CatViewFactory;
import interface_adapter.initialize_cats.InitializeCatsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CatContainerView extends JPanel implements PropertyChangeListener {
    private final InitializeCatsViewModel initializeCatsViewModel;
    private final InventoryViewModel inventoryViewModel;
    private final DialogService dialogService;
    private final GetCatFactView getCatFactView;

    private final CatViewFactory catViewFactory;
    private final DisplayCatStatsViewModelFactory displayCatStatsViewModelFactory;
    private final JPanel catsPanel;

    public CatContainerView(InitializeCatsViewModel initializeCatsViewModel,
                            InventoryViewModel inventoryViewModel,
                            DialogService dialogService,
                            GetCatFactView getCatFactView,
                            CatViewFactory catViewFactory,
                            DisplayCatStatsViewModelFactory displayCatStatsViewModelFactory) {
        this.initializeCatsViewModel = initializeCatsViewModel;
        this.inventoryViewModel = inventoryViewModel;
        this.dialogService = dialogService;
        this.getCatFactView = getCatFactView;
        this.catViewFactory = catViewFactory;
        this.displayCatStatsViewModelFactory = displayCatStatsViewModelFactory;
        this.catsPanel = new JPanel(new GridLayout());

        initializeCatsViewModel.addPropertyChangeListener(this);

        setUp();
        // updateCatViews();

        SwingUtilities.invokeLater(() -> {
            this.setOpaque(true);
            this.setEnabled(true);
            this.setVisible(true);
            this.revalidate();
            this.repaint();
        });
    }

    private void setUp() {
        this.setLayout(new BorderLayout());
        add(catsPanel, BorderLayout.CENTER);
    }

    private void updateCatViews() {
        catsPanel.removeAll();

        for (CatViewModel catViewModel : initializeCatsViewModel.getState().getCatViewModels()) {
            final DisplayCatStatsViewModel displayCatStatsViewModel = displayCatStatsViewModelFactory
                    .createDisplayCatStatsViewModel(catViewModel.getState());

            final CatView catView = catViewFactory.createCatView(catViewModel,
                    displayCatStatsViewModel,
                    inventoryViewModel,
                    dialogService,
                    getCatFactView);

            catsPanel.add(catView);
        }
        catsPanel.setOpaque(true);
        catsPanel.setVisible(true);
        catsPanel.revalidate();
        catsPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("initialize_cats")) {
            updateCatViews();
        }
    }

    public String getViewName() {
        return initializeCatsViewModel.getViewName();
    }
}
