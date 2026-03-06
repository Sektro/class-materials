package game;

import game.board.Tile;
import game.board.tiles.Luck;
import game.board.tiles.Property;
import game.board.tiles.Service;
import game.players.Player;
import game.players.strategies.Careful;
import game.players.strategies.Greedy;
import game.players.strategies.Tactical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    public HashMap<Integer, Tile> board;
    public ArrayList<Player> players;
    int round;
    int numRoll;
    int[] rolls;
    boolean endOfGame;
    boolean secret;
    int rollCounter;
    boolean isTest;

    public Game() {
        board = new HashMap<>();
        players = new ArrayList<>();
        round = 1;
        numRoll = 0;
        rolls  = new int[0];
        endOfGame = false;
        secret = false;
        rollCounter = 0;
        isTest = false;
    }

    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        System.out.println();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        String isTestString = sc.next();
        int numTiles = sc.nextInt();
        if (numTiles <= 0) {endOfGame = true;}
        for (int i = 1; i < numTiles + 1; i++) {
            Tile tile;
            switch (sc.next()) {
                case "p":
                    tile = new Property(i);
                    break;
                case "l":
                    tile = new Luck(sc.nextInt(), i);
                    break;
                case "s":
                    tile = new Service(sc.nextInt(), i);
                    break;
                default:
                    throw new InvalidInputException();
            }
            board.put(i, tile);
        }
        int numPlayer = sc.nextInt();
        for (int i = 0; i < numPlayer; i++) {
            Player player;
            String name = sc.next();
            switch (sc.next()) {
                case "g":
                    player = new Greedy(name);
                    break;
                case "t":
                    player = new Tactical(name);
                    break;
                case "c":
                    player = new Careful(name);
                    break;
                default:
                    throw new InvalidInputException();
            }
            players.add(player);
        }
        if (isTestString.equals("t")) {
            isTest = true;
            ArrayList<Integer> rollsHelp = new ArrayList<>();
            numRoll = sc.nextInt();
            for (int i = 0; i < numRoll * players.size(); i++) {
                rollsHelp.add(sc.nextInt());
            }
            rolls = new int[numRoll * players.size()];
            int i = 0;
            for (int r : rollsHelp) {
                rolls[i] = r;
                ++i;
            }
        }
    }

    public void state() {
        System.out.println("-----Round " + round + "-----");
        System.out.println("Players:");
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getMoney() + " Peták, property: " + p.getNumProp() + " properties (houses: " + p.getHouses() + "), Tile: " + p.getWhere());
        }
    }

    public void round() {
        ArrayList<Player> temp = new ArrayList<>();
        for (Player p : players) {
            if (p.step(p.rollTheDice(), board)) {
                if (p.defeated()) {
                    for (Map.Entry<Integer,Property> entry : p.getProperties().entrySet()) {
                        board.put(entry.getKey(),new Property(entry.getKey()));
                    }
                    temp.add(p);
                }
            }
        }
        for (Player p : temp) {
            players.remove(p);
        }
        if (players.size() > 1) {state();}
        else if (players.size() == 1)
            {for (Player p : players) {
                System.out.println();
            System.out.println("---Congratulations " + p.getName() + " has won the game!---");
            System.out.println("___Stats:__________________________________________________");
            System.out.println("properties: " + p.getNumProp() + " db");
            System.out.println("money: " + p.getMoney() + " Peták");
                }
                endOfGame = true;
            }
        else {
            System.out.println("~~~${|In this world of capitalism|}$~~~");
            System.out.println("~~~${|    nobody's a winner....  |}$~~~");
            endOfGame = true;
            secret = true;
        }
    }

    public boolean simulation() {
        if (isTest) {
            int divide = 0;
            if (!players.isEmpty()) {divide = rolls.length / players.size();}
            for (int i = 0; i < divide && !endOfGame; ++i) {
                roundTest();
                ++round;
            }
            int maxMoney = 0;
            Player maxPlayer = new Tactical("Alexander the Great");
            for (Player p : players) {
                if (p.getMoney() > maxMoney) {
                    maxPlayer = p;
                    maxMoney = p.getMoney();
                }
            }
            System.out.println();
            System.out.println("(The winner in test conditions is " + maxPlayer.getName() + ")");
        }
        else {
            while (!endOfGame) {
                round();
                ++round;
            }
        }

        if (secret) {return false;}
        else {return true;}
    }


    public void roundTest() {
        ArrayList<Player> temp = new ArrayList<>();
        for (Player p : players) {
            if (p.step(rolls[rollCounter], board)) {
                if (p.defeated()) {
                    for (Map.Entry<Integer,Property> entry : p.getProperties().entrySet()) {
                        board.put(entry.getKey(),new Property(entry.getKey()));
                    }
                    temp.add(p);
                }
            }
            ++rollCounter;
        }
        for (Player p : temp) {
            players.remove(p);
        }
        if (players.size() > 1) {state();}
        else if (players.size() == 1)
        {for (Player p : players) {
            System.out.println("---Congratulations " + p.getName() + " has won the game!---");
            System.out.println("___Stats:__________________________________________________");
            System.out.println("properties: " + p.getNumProp() + " db");
            System.out.println("money: " + p.getMoney() + " Peták");
        }
            endOfGame = true;
        }
        else {
            System.out.println("~~~${|In this world of capitalism|}$~~~");
            System.out.println("~~~${|    nobody's a winner....  |}$~~~");
            endOfGame = true;
            secret = true;
        }
    }
}

