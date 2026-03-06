package model.shop;

/**
 * Represents an item that can be purchased from the safari shop.
 * Each item has a name, price, and type (animal or plant).
 * This class is immutable - all properties are final and can only be set through the constructor.
 */
public class ShopItem{
  private final String name;
  private final int price;
  private final ItemType type;

  /**
  * Creates a new shop item with the specified properties.
  * 
  * @param name  The display name of the item
  * @param price The cost of the item in game currency
  * @param type  The type of item (ANIMAL or PLANT)
  */
  public ShopItem(String name, int price, ItemType type){
    this.name = name;
    this.price = price;
    this.type = type;
  }

  //Getters for name, price, type
  public String getName() { return name; }
  public int getPrice() { return price; }
  public ItemType getType() { return type; }
}