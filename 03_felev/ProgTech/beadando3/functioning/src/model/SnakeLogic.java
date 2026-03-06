package hu.elte.progtech.snake.model;

import hu.elte.progtech.snake.model.score.ScoreBoard;
import hu.elte.progtech.snake.model.snake.Snake;
import hu.elte.progtech.snake.persistence.dao.HighScoreDao;
import hu.elte.progtech.snake.persistence.entity.HighScore;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class SnakeLogic {
    private int size;
    private String name;

    private HighScoreDao highScoreDao;

    private int[][] grids;

    private Snake snake;

    private ScoreBoard scoreBoard;
    private Coordinate applePos;
    private ArrayList<Coordinate> stonePos;

    /**
     * Constructor of SnakeLogic.
     * Initiates "highScoreDao" which is part of the connection between the code and the database.
     */
    public SnakeLogic() {this.highScoreDao = new HighScoreDao();}

    /**
     * Starts a new game with the given username and a boardsize.
     * @param size
     * @param name
     */
    public void newGame(int size, String name){
        this.size = size;
        this.name = name;

        applePos = new Coordinate(0,0);
        grids = new int[size][size];
        initGrid();

        initScoreBoard();
        stonePos = new ArrayList<>();
        stonesSpawn();

        snake = new Snake();
        snake.Spawn(size / 2);

        appleSpawn();
        updateGrid();
        scoreBoard.reset();
    }

    // SNAKE

    /**
     * Decides whether the snake can move in a given direction.
     * @param direction
     * @return
     */
    public boolean canMove(Direction direction) {
        return !snake.cantMove(direction);
    }

    /**
     * changes the direction the snake is facing.
     * @param direction
     */
    public void changeDirections(Direction direction) {
        snake.setFacing(direction);
    }

    /**
     * Moves the snake, or if it hit an obstacle, exits from the function.
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
        updateGrid();
        return false;
    }


    // GRID
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
    private void updateGrid() {
        for (int i = 0; i < size; ++i) {
            Arrays.fill(grids[i], GameConstants.EMPTY);
            for (int j = 0; j < size; ++j) {
                if (i == 0 || j == 0 || j == size - 1 || i == size - 1) {
                    grids[i][j] = GameConstants.BORDER;
                }
            }
        }
        ArrayList<Coordinate> snakePos = snake.getPositions();
        for (Coordinate snakePo : snakePos) {
            grids[snakePo.getY()][snakePo.getX()] = GameConstants.SNAKE;
        }
        grids[applePos.getY()][applePos.getX()] = GameConstants.APPLE;
        for (Coordinate stonePo : stonePos) {
            grids[stonePo.getY()][stonePo.getX()] = GameConstants.STONE;
        }
    }
    private void appleSpawn() {
        Random rnd = new Random();
        boolean found = false;
        int row = 0;
        int column = 0;
        while (!found) {
            row = rnd.nextInt(grids.length-2)+1;
            column = rnd.nextInt(grids[0].length-2)+1;
            if (!snake.onSnake(row,column) && !onStone(new Coordinate(column,row))) {found = true;}
        }
        applePos.setX(column);
        applePos.setY(row);
    }
    private void stonesSpawn() {
        ArrayList<Coordinate> options = new ArrayList<>();
        for (int i = 2; i < grids.length-2; ++i) {
            for (int j = 2; j < grids[0].length-2; ++j) {
                if (!(i >= grids.length/2-2 && i <= grids.length/2+2 && j >= grids[0].length/2-2 && j <= grids[0].length/2+2)) {
                    options.add(new Coordinate(j,i));
                }
            }
        }
        Random rnd = new Random();
        Coordinate temp;
        for (int i = 0; i < GameConstants.STONE_NUMBER; ++i) {
            if (options.isEmpty()) {return;}
            temp = options.get(rnd.nextInt(options.size()));
            stonePos.add(temp);
            int removeCount = 0;
            ArrayList<Integer> forRemoval = new ArrayList<>();
            for (int j = 0; j < options.size(); ++j) {
                if (options.get(j).getX() < (temp.getX() + 2) && options.get(j).getX() > (temp.getX() - 2) && options.get(j).getY() < (temp.getY() + 2) && options.get(j).getY() > (temp.getY() - 2)) {
                    forRemoval.add(j);
                }
            }
            for (int j : forRemoval) {
                options.remove(j-removeCount);
                ++removeCount;
            }
        }
    }
    private boolean onStone(Coordinate coordinate) {
        for (Coordinate s : stonePos) {
            if (coordinate.getX() == s.getX() && coordinate.getY() == s.getY()) {
                return true;
            }
        }
        return false;
    }



    // SCOREBOARD

    /**
     * Saves the player's score if it's greater than 0.
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
                throw new IllegalStateException("Failed to save highscore to database!", ex);
            }
        }
    }
    private void initScoreBoard() {
        scoreBoard = new ScoreBoard();
        var url = "jdbc:sqlite:db/highscore.db";

        var sql = "CREATE TABLE IF NOT EXISTS highscore ("
                + "	id INTEGER PRIMARY KEY,"
                + "	name Varchar(200),"
                + "	score INTEGER"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            int topScore = highScoreDao.getTopScore();
            scoreBoard.setTopScore(topScore);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }


    public Snake getSnake() {
        return snake;
    }
    public int getGridValue(int row, int col){
        return grids[row][col];
    }
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
    public HighScoreDao getHighScoreDao() {
        return highScoreDao;
    }

}