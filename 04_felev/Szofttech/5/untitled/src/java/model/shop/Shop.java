package model.shop;

import controller.TileManager;
import model.animal.*;
import model.plant.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the shop system for purchasing animals and plants in the safari.
 * Handles item inventory, prices, and purchase transactions.
 */
public class Shop {
  private Map<String, ShopItem> items;
  private int playerMoney;
  private final int TICKET_PRICE = 30;
  //Maybe used for ticket sales in the future
  private int totalTicketsSold = 0;
  private int totalTicketRevenue = 0;

  /**
  * Creates a new shop with initial money balance.
  *
  * @param initialMoney The starting amount of money for the player
  */
  public Shop(int initalMoney){
    this.playerMoney = initalMoney;
    init();
  }

  /**
  * Initializes the shop's inventory with available items and their prices.
  * Sets up both animals and plants that can be purchased.
  */
  private void init(){
    items = new HashMap<>();
        // Add Jeep
        items.put("Jeep", new ShopItem("Jeep", 5000, ItemType.JEEP));

        // Add Road
        items.put("Road", new ShopItem("Road", 50, ItemType.ROAD));

        //Add Chip
        items.put("Tracking Chip", new ShopItem("Tracking Chip", 250, ItemType.CHIP));
        
        // Add Animals
        items.put("Zebra", new ShopItem("Zebra", 500, ItemType.ANIMAL));
        items.put("Elephant", new ShopItem("Elephant", 2000, ItemType.ANIMAL));
        items.put("Lion", new ShopItem("Lion", 3000, ItemType.ANIMAL));
        items.put("Hyena", new ShopItem("Hyena", 1500, ItemType.ANIMAL));
        
        // Add Plants
        items.put("Palm Tree", new ShopItem("Palm Tree", 100, ItemType.PLANT));
        items.put("Baobab Tree", new ShopItem("Baobab Tree", 300, ItemType.PLANT));
        items.put("Bush", new ShopItem("Bush", 50, ItemType.PLANT));

  }

  /**
  * Checks if the player can afford to buy a specific item.
  * 
  * @param itemName The name of the item to check
  * @return true if the item exists and player has enough money, false otherwise
  */
  public boolean canBuy(String itemName){
    ShopItem item = items.get(itemName);
    return item != null && playerMoney >= item.getPrice();
  }

  /**
  * Processes the purchase of an item if the player can afford it.
  * Deducts the item's price from player's money if successful.
  * 
  * @param itemName The name of the item to purchase
  * @return true if purchase was successful, false if item doesn't exist or insufficient funds
  */
  public boolean purchase(String itemName){
    if(!canBuy(itemName)){
      return false;
    }

    return true;
  }

  public boolean purchaseTrackingChip(Animal animal) {
    if (canBuy("Tracking Chip") && !animal.hasTrackingChip()) {
        deductMoney("Tracking Chip");
        animal.setHasTrackingChip(true);

        return true;
    }
    
    return false;
  }

  public void deductMoney(String itemName){
    ShopItem item = items.get(itemName);
    if(item != null){
      playerMoney -= item.getPrice();
    }
  }

  /**
  * Creates a new instance of a purchased item at specified coordinates.
  * 
  * @param itemName The name of the item to create
  * @param x The x-coordinate for placement
  * @param y The y-coordinate for placement
  * @return The created object (Animal or Plant) or null if item doesn't exist
  */
  public Object createPurchasedItem(String itemName, int x, int y){
    ShopItem item = items.get(itemName);
    if(item == null){
      return null;
    }

    return switch (itemName) {
      case "Zebra" -> new Herbivore(5, 5, 5, x, y, HerbivoreType.Zebra, new TileManager());
      case "Elephant" -> new Herbivore(5, 5, 5, x, y, HerbivoreType.Elephant, new TileManager());
      case "Lion" -> new Carnivore(5, 5, 5, x, y, CarnivoreType.Lion, new TileManager());
      case "Hyena" -> new Carnivore(5, 5, 5, x, y, CarnivoreType.Hyena, new TileManager());
      case "Palm Tree" -> new Plant(5, PlantType.PalmTree, x, y);
      case "Baobab Tree" -> new Plant(5, PlantType.BaobabTree, x, y);
      case "Bush" -> new Plant(5, PlantType.Bush, x, y);
      default -> null;
    };
  }

  public int sellItem(Object obj) {
    int salePrice = 0;
    final double SELL_PERCENTAGE = 0.7; // Sell for 70% of original price
    
    if (obj instanceof Herbivore) {
        Herbivore herbivore = (Herbivore) obj;
        switch (herbivore.getHerbivoreType()) {
            case Zebra:
                salePrice = (int)(items.get("Zebra").getPrice() * SELL_PERCENTAGE);
                break;
            case Elephant:
                salePrice = (int)(items.get("Elephant").getPrice() * SELL_PERCENTAGE);
                break;
        }
    } else if (obj instanceof Carnivore) {
        Carnivore carnivore = (Carnivore) obj;
        switch (carnivore.getCarnivoreType()) {
            case Lion:
                salePrice = (int)(items.get("Lion").getPrice() * SELL_PERCENTAGE);
                break;
            case Hyena:
                salePrice = (int)(items.get("Hyena").getPrice() * SELL_PERCENTAGE);
                break;
        }
    } else if (obj instanceof Plant) {
        Plant plant = (Plant) obj;
        switch (plant.getPlantType()) {
            case PalmTree:
                salePrice = (int)(items.get("Palm Tree").getPrice() * SELL_PERCENTAGE);
                break;
            case BaobabTree:
                salePrice = (int)(items.get("Baobab Tree").getPrice() * SELL_PERCENTAGE);
                break;
            case Bush:
                salePrice = (int)(items.get("Bush").getPrice() * SELL_PERCENTAGE);
                break;
        }
    }
    
    // Add money to player's account
    playerMoney += salePrice;
    
    return salePrice;
  }

  public void collectTicketRevenue(int visitorCount){
    int revenue = visitorCount * TICKET_PRICE;
    playerMoney += revenue;
    totalTicketsSold += visitorCount;
    totalTicketRevenue += revenue;
  }

  //Getter for playerMoney, items
  public int getPlayerMoney() { return playerMoney; }
  public Map<String, ShopItem> getItems() { return items; }
  public int getTicketPrice() { return TICKET_PRICE; }
  public int getTotalTicketsSold() { return totalTicketsSold; }
  public int getTotalTicketRevenue() { return totalTicketRevenue; }

}
