package hu.elte.progtech.snake.model;

import hu.elte.progtech.snake.model.score.ScoreBoard;
import hu.elte.progtech.snake.model.snake.Snake;
import hu.elte.progtech.snake.persistence.dao.HighScoreDao;
import hu.elte.progtech.snake.persistence.entity.HighScore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class SnakeLogic {
    private int size;
    private String name;

    private int[][] grids;

    private Snake snake;

    private ScoreBoard scoreBoard;
    private HighScoreDao highScoreDao;


    public SnakeLogic() {this.highScoreDao = new HighScoreDao();}

    /**
     * elindítja a játékot (legenerálja a kígyót és a pontokat)
     * @param size
     * @param name
     */
    public void newGame(int size, String name){
        this.size = size;
        this.name = name;

        grids = new int[size][size];
        initGrid();

        initScoreBoard();

        snake = new Snake();
        snakeSpawn();

        appleSpawn();

        scoreBoard.reset();
    }
    private void snakeSpawn() {
        snake.Spawn(size / 2);
        ArrayList<Coordinate> snakePos = snake.getPositions();
        for (Coordinate p : snakePos) {
            grids[p.getY()][p.getX()] = GameConstants.SNAKE;
        }
    }

    /**
     * megnézi, hogy tud-e a kígyó egy adott irányba mozogni
     * @param direction
     * @return
     */
    public boolean canMove(Direction direction) {
        return !snake.cantMove(direction);
    }

    /**
     * elvégzi az irányváltást
     * @param direction
     */
    public void changeDirections(Direction direction) {
        snake.setFacing(direction);
    }

    /**
     * mozgatja a kígyót
     * @return
     */
    public boolean move() {
        if (snake.wouldBump(this)) {
            return true;
        }
        snake.move();
        if (grids[snake.getSnakeHead().getY()][snake.getSnakeHead().getX()] == GameConstants.APPLE) {
            scoreBoard.addScore(1);
            grids[snake.getSnakeHead().getY()][snake.getSnakeHead().getX()] = GameConstants.EMPTY;
            snake.collectsApple();
            appleSpawn();
        }
        return false;
    }

    private void appleSpawn() {
        Random rnd = new Random();
        boolean found = false;
        int row = 0;
        int column = 0;
        while (!found) {
            row = rnd.nextInt(grids.length-2)+1;
            column = rnd.nextInt(grids[0].length-2)+1;
            if (!snake.onSnake(row,column)) {found = true;}
        }
        grids[row][column] = GameConstants.APPLE;
    }



    private void initScoreBoard() {
        scoreBoard = new ScoreBoard();
        try {
            int topScore = highScoreDao.getTopScore();
            scoreBoard.setTopScore(topScore);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
    private void initGrid() {
        for (int i = 0; i < size; ++i) {
            Arrays.fill(grids[i], GameConstants.EMPTY);
            for (int j = 0; j < size; ++j) {
                if (i == 0 || j == 0 || j == size - 1 || i == size - 1) {
                    grids[i][j] = GameConstants.BORDER;
                }
            }
        }
    }

    /**
     * ha a játékos szerzett pontot, azt elmenti
     */
    public void saveScoreIfGreaterThan0() {
        int scoreValue = scoreBoard.getScore();
        if(scoreValue > 0) {
            try {
                HighScore score = new HighScore();
                score.setName(name);
                score.setScore(scoreValue);
                highScoreDao.add(score);
            } catch (SQLException ex) {
                throw new IllegalStateException("Failed to save high_score to database!", ex);
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }
    public int getSize() {
        return size;
    }
    public int getGridValue(int row, int col){
        return grids[row][col];
    }
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

}