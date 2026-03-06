package game.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import game.board.tiles.*;
import game.board.Tile;
import game.board.tiles.Property;

public abstract class  Player {

    public int where;
    public String name;
    public int money;
    public HashMap<Integer, Property> properties;
    public int houses;

    public Player(String name) {
        this.name = name;
        money = 10000;
        properties = new HashMap<>();
        where = 1;
    }

    public void getsMoney(int amount) {money += amount;}
    public void paysMoney(int amount) {money -= amount;}
    public void paysMoneyForService(int amount) {money -= amount;}

    public void buyProperty(Property property) {
        paysMoney(1000);
        properties.put(property.getWhere(),property);
        property.boughtProperty(this);
    }
    public void buildHouse(Property property) {
        paysMoney(4000);
        property.buildHouse();
    }
    public void payForProperty(Property property) {
        if (property.getHouse()) {
            paysMoney(2000);
            property.getOwner().getsMoney(2000);
        }
        else {
            paysMoney(500);
            property.getOwner().getsMoney(500);
        }
    }
    public int rollTheDice() {
        double y = (Math.random() * 6 + 1);
        return (int)Math.floor(y);
    }
    public boolean stepsOn(Tile tile) {
        if (tile instanceof Luck) {
            ((Luck) tile).getsLucky(this);
            return false;
        }
        else if (tile instanceof Service) {
            ((Service) tile).usingService(this);
            return true;
        }
        else if (tile instanceof Property) {
            if (((Property) tile).getOwner() == null) {
                buyProperty((Property) tile);
                return true;
            } else if (((Property) tile).getOwner() == this) {
                if (!((Property) tile).getHouse()) {
                    buildHouse((Property) tile);
                    ++houses;
                    return true;
                }
                return false;
            } else {
                payForProperty((Property) tile);
                return true;
            }
        }
        return false;
    }
    public boolean defeated() {
        return money <= 0;
    }
    public boolean step(int steps, HashMap<Integer, Tile> board)
    {
        int destination = where + steps;
        while (destination > board.size()) {
            destination -= board.size();
        }
        where = destination;
        return stepsOn(board.get(where));
    }

    public String getName() {return name;}
    public int getMoney() {return money;}
    public int getNumProp() {return properties.size();}
    public HashMap<Integer,Property> getProperties() {return properties;}
    public int getWhere() {return where;}
    public int getHouses() {return houses;}
}
