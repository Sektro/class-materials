package hu.elte.progtech.snake.model.score;

public class ScoreBoard {

    private int score;
    private int topScore;
    private boolean gameOver = true;

    /**
     * Resets the ScoreBoard's values to their starting value.
     */
    public void reset() {
        setTopScore();
        score = 0;
        gameOver = false;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * A method used for setting the top score from the database
     * @param topScore
     */
    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    /**
     * A method used for setting the top score only using the scores known to the scoreboard (not the database)
     */
    public void setTopScore() {
        if (score > topScore) {
            topScore = score;
        }
    }

    /**
     * Adds to the score of the player a given amount.
     * @param score
     */
    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}