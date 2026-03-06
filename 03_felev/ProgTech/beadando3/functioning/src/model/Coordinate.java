package hu.elte.progtech.snake.model;

public class Coordinate {
    private int x;
    private int y;

    /**
     * Constructor of Coordinate.
     * The class itself is used to represent the tiles of the board.
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
}