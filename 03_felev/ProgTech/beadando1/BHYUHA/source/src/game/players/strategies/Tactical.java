package game.players.strategies;

import game.board.Tile;
import game.board.tiles.Luck;
import game.board.tiles.Property;
import game.board.tiles.Service;
import game.players.Player;

public class Tactical extends Player {
    boolean buys;

    public Tactical(String name) {super(name); buys = true;}

    @Override
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
                if (buys) {
                    buyProperty((Property) tile);
                    buys = false;
                    return true;
                } else {
                    buys = true;
                    return false;
                }
            } else if (((Property) tile).getOwner() == this) {
                if (!((Property) tile).getHouse()) {
                    if (buys) {
                        buildHouse((Property) tile);
                        buys = false;
                        ++houses;
                        return true;
                    } else {
                        buys = true;
                        return false;
                    }
                }
                return false;
            } else {
                payForProperty((Property) tile);
                return true;
            }
        }
        return false;
    }
}
