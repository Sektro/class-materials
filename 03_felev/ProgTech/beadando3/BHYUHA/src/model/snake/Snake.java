package hu.elte.progtech.snake.model.snake;

import hu.elte.progtech.snake.model.Coordinate;
import hu.elte.progtech.snake.model.Direction;
import hu.elte.progtech.snake.model.GameConstants;
import hu.elte.progtech.snake.model.SnakeLogic;

import java.util.ArrayList;
import java.util.Random;

public class Snake {

    private ArrayList<Coordinate> positions;
    private Direction facing;
    private Coordinate behindTail;

    /**
     * Constructor of the snake.
     * Randomly sets which direction it's facing.
     */
    public Snake() {
        positions = new ArrayList<>();
        Random rnd = new Random();
        int dir = rnd.nextInt(4);
        switch (dir) {
            case 0 :
                facing = Direction.DOWN;
                break;
            case 1 :
                facing = Direction.LEFT;
                break;
            case 2 :
                facing = Direction.UP;
                break;
            case 3 :
                facing = Direction.RIGHT;
                break;
        }

    }

    /**
     * Moves the snake in the direction it's facing.
     */
    public void move() {
        Coordinate destination = new Coordinate(positions.get(0).getX() + facing.getX(),positions.get(0).getY() + facing.getY());
        Coordinate temp;
        for (int i = 0; i < positions.size(); ++i) {
            temp = positions.get(i);
            positions.set(i,destination);
            destination = temp;
        }
        behindTail = destination;
    }

    /**
     * Lengthens the snake.
     */
    public void collectsApple() {
        positions.add(behindTail);
    }

    /**
     * Decides whether the direction the snake would move is valid.
     * @param direction
     * @return returns true if the direction is one that the snake can take and false if it isn't
     */
    public boolean cantMove(Direction direction) {
        Coordinate destination = new Coordinate(positions.get(0).getX() + direction.getX(),positions.get(0).getY() + direction.getY());
        return (positions.get(1).getX() == destination.getX() && positions.get(1).getY() == destination.getY());
    }

    /**
     * Checks for collision before movement is calculated.
     * @param snakelogic
     * @return returns true if the snake would collide and false if it wouldn't
     */
    public boolean wouldBump(SnakeLogic snakelogic) {
        Coordinate destination = new Coordinate(positions.get(0).getX() + facing.getX(),positions.get(0).getY() + facing.getY());

        if (snakelogic.getGridValue(destination.getY(), destination.getX()) == GameConstants.BORDER || snakelogic.getGridValue(destination.getY(), destination.getX()) == GameConstants.STONE) {
            return true;
        }
        return onSnake(destination.getY(),destination.getX());
    }

    /**
     * Creates the snake and adds length to it based on the direction it's facing.
     * @param pos
     */
    public void Spawn(int pos) {
        positions.add(new Coordinate(pos,pos));
        switch (facing) {
            case DOWN:
                positions.add(new Coordinate(pos,pos-1));
                break;
            case LEFT:
                positions.add(new Coordinate(pos+1,pos));
                break;
            case UP:
                positions.add(new Coordinate(pos,pos+1));
                break;
            case RIGHT :
                positions.add(new Coordinate(pos-1,pos));
                break;
        }

    }

    /**
     * Decides whether the given "coordinate" is where the snake's body is.
     * @param row
     * @param column
     * @return returns true if the given "coordinate" was on the snake's body false if it wasn't
     */
    public boolean onSnake(int row, int column)  {
        Coordinate tile = new Coordinate(column, row);
        for (Coordinate p : positions) {
            if (tile.getX() == p.getX() && tile.getY() == p.getY()) {
                return true;
            }
        }
        return false;
    }


    public Coordinate getSnakeHead() {return positions.get(0);}
    public void setFacing(Direction facing) {this.facing = facing;}
    public ArrayList<Coordinate> getPositions() {return positions;}
}
