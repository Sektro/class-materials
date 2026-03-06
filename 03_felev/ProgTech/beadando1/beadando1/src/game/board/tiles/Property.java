package game.board.tiles;

import game.board.Tile;
import game.players.Player;

public class Property extends Tile {
    boolean house;
    Player owner;

    public Property(int where) {
        house = false;
        owner = null;
        this.where = where;
    }

    public void boughtProperty(Player player) {
        owner = player;
    }
    public void buildHouse() {
        house = true;
    }

    public Player getOwner() {return owner;}
    public boolean getHouse() {return house ;}
}
