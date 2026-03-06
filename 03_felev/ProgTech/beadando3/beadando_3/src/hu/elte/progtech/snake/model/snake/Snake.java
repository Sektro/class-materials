package hu.elte.progtech.snake.model.snake;

import hu.elte.progtech.snake.model.Coordinate;
import hu.elte.progtech.snake.model.Direction;
import hu.elte.progtech.snake.model.GameConstants;
import hu.elte.progtech.snake.model.SnakeLogic;

import java.util.ArrayList;

public class Snake {

    private ArrayList<Coordinate> positions;
    private Direction facing;
    private Coordinate behindTail;

    public Snake() {
        positions = new ArrayList<Coordinate>();
        facing = Direction.RIGHT;
    }

    /**
     * a mozgást végző függvény, minden játékciklusban meghívjuk
     */
    public void move() {
        Coordinate destination = positions.getFirst();
        destination.setX(destination.getX() + facing.getX());
        destination.setY(destination.getY() + facing.getY());
        Coordinate temp = destination;
        for (Coordinate p : positions) {
            temp = p;
            p = destination;
            destination = temp;
        }
        behindTail = destination;
    }

    /**
     * visszaadja, hogy az irány amerre fordulni akarunk egyezik-e a kígyó nyakával
     * (arra nem lehet fordulni, ezért kell ellenőrizni)
     * @param direction
     * @return
     */
    public boolean cantMove(Direction direction) {
        Coordinate destination = positions.getFirst();
        destination.setX(destination.getX() + direction.getX());
        destination.setY(destination.getY() + direction.getY());
        return destination == positions.get(1);
    }

    /**
     * visszaadja, hogy a kígyó beleütközik-e bármibe, ha arra halad tovább, amerre épp fordul
     * @param snakelogic
     * @return
     */
    public boolean wouldBump(SnakeLogic snakelogic) {
        Coordinate destination = positions.getFirst();
        destination.setX(destination.getX() + facing.getX());
        destination.setY(destination.getY() + facing.getY());

        if (snakelogic.getGridValue(destination.getY(), destination.getX()) == GameConstants.BORDER) {
            return true;
        }
        for (Coordinate p : positions) {
            if (p.equals(destination)) {
                return true;
            }
        }
        return false;
    }

    /**
     * létrehozza a kígyót
     * @param pos
     */
    public void Spawn(int pos) {
        positions.add(new Coordinate(pos,pos));
    }

    /**
     * vizsgálja, hogy a kígyó testével egyezik-e a megaddott koordináta
     * @param row
     * @param column
     * @return
     */
    public boolean onSnake(int row, int column)  {
        Coordinate tile = new Coordinate(column, row);

        for (Coordinate p : positions) {
            if (p.equals(tile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * megnöveli a kígyó hosszát egy alma begyűjtése után
     */
    public void collectsApple() {
        positions.add(behindTail);
    }


    public Coordinate getSnakeHead() {return positions.getFirst();}
    public Direction getFacing() {return facing;}
    public void setFacing(Direction facing) {this.facing = facing;}
    public ArrayList<Coordinate> getPositions() {return positions;}
}
