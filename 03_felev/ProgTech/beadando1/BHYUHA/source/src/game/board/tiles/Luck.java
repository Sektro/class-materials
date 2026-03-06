package game.board.tiles;

import game.board.Tile;
import game.players.Player;

public class Luck extends Tile {

    int reward;

    public Luck(int reward, int where) {
        this.reward = reward;
        this.where = where;
    }

    public void getsLucky(Player player) {
        player.getsMoney(reward);
    }
}
