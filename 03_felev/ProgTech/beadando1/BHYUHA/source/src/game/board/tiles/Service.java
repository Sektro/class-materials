package game.board.tiles;

import game.board.Tile;
import game.players.Player;

public class Service extends Tile {

    int cost;

    public Service(int cost, int where) {
        this.cost = cost;
        this.where = where;
    }

    public void usingService(Player player) {
        player.paysMoneyForService(cost);
    }
}
