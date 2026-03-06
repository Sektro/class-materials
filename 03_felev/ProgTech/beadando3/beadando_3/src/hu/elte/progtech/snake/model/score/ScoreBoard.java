package hu.elte.progtech.snake.model.score;

public class ScoreBoard {

    private int score;
    private int topScore;
    private boolean gameOver = true;

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

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public void setTopScore() {
        if (score > topScore) {
            topScore = score;
        }
    }

    public int getTopScore() {
        return topScore;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}