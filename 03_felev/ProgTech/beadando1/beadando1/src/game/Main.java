package game;

import game.board.Tile;
import game.board.tiles.Luck;
import game.board.tiles.Property;
import game.board.tiles.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.read("game.txt");

            if (game.simulation()) {
                System.out.println();
                System.out.println("Game Finished!");
            }
            else {
                System.out.println();
                System.out.println("Secret Ending Unlocked!");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(-1);
        }
        catch (InvalidInputException e) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
    }
}
