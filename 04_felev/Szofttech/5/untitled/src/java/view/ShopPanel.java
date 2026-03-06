package view;

import model.GameConstants;
import model.shop.*;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that displays the shop interface for the safari game.
 * Contains a money display and scrollable list of purchasable items.
 * Items can be bought and placed in the game world.
 */
public class ShopPanel extends JPanel {
    private final Shop shop;
    private final GamePanel gamePanel;
    private JLabel moneyLabel;
    private JButton sellButton;

    /**
     * Creates a new shop panel connected to the game panel.
     * Initializes the shop with starting money and sets up the UI components.
     *
     * @param gamePanel The main game panel to interact with for item placement
     */
    public ShopPanel(GamePanel gamePanel) {
        this.shop = gamePanel.getShop();
        this.gamePanel = gamePanel;

        setLayout(new BorderLayout());
        init();
    }

    /**
     * Initializes all UI components of the shop panel.
     * Sets up the money display, creates buttons for each shop item,
     * and arranges them in a scrollable panel.
     */
    private void init() {
        // Set fixed width for shop panel but full height
        setPreferredSize(new Dimension(GameConstants.SHOP_PANEL_WIDTH, GameConstants.CAMERA_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(32, 32, 32)); // Dark background

        // Money display
        moneyLabel = new JLabel("Money: $" + shop.getPlayerMoney());
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setHorizontalAlignment(JLabel.CENTER);
        moneyLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(moneyLabel, BorderLayout.NORTH);

        //Sell button
        sellButton = new JButton("Sell Mode");
        sellButton.addActionListener(e -> {
            gamePanel.setSellMode(true);
            gamePanel.requestFocusInWindow();
        });

        // Shop items
        JPanel shopItemsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        shopItemsPanel.setBackground(new Color(32, 32, 32));

        shopItemsPanel.add(sellButton);
        
        for (ShopItem item : shop.getItems().values()) {
            JButton btn = new JButton(item.getName() + " - $" + item.getPrice());
            btn.setPreferredSize(new Dimension(200, 30));
            btn.addActionListener(e -> {
                if(shop.canBuy(item.getName())){
                    if(shop.purchase(item.getName())){
                        gamePanel.setPlacementMode(item.getName());
                        gamePanel.requestFocusInWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough money to buy " + item.getName(), "Cannot Buy", JOptionPane.WARNING_MESSAGE);
                    gamePanel.requestFocusInWindow();
                }
            });
            shopItemsPanel.add(btn);
        }

        //Not working gotta look into ScrollPane
        JScrollPane scrollPane = new JScrollPane(shopItemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Updates the money display label with the current player money.
     * Called after successful purchases to reflect the new balance.
     */
    public void updateMoneyLabel() {
        moneyLabel.setText("Money: $" + shop.getPlayerMoney());
    }
}
