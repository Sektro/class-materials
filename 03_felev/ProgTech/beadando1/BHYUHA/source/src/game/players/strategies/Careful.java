package game.players.strategies;

import game.board.Tile;
import game.board.tiles.Luck;
import game.board.tiles.Property;
import game.board.tiles.Service;
import game.players.Player;

public class Careful extends Player {

    int spentSoFar;

    public Careful(String name) {super(name); spentSoFar = 0;}

    @Override
    public void paysMoney(int amount) {
        money -= amount;
        spentSoFar += amount;
    }

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
                if (!(spentSoFar >= (spentSoFar + money) / 2)) {buyProperty((Property) tile); return true;}
                return false;
            } else if (((Property) tile).getOwner() == this) {
                if (!((Property) tile).getHouse()) {
                    if (!(spentSoFar >= (spentSoFar + money) / 2)) {buildHouse((Property) tile); ++houses; return true;}
                    return false;
                }
            } else {
                payForProperty((Property) tile);
                return true;
            }
        }
        return false;
    }
}
