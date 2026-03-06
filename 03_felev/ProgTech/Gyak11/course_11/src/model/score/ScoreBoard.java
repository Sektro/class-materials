package model.score;

public class ScoreBoard {

    private int lines;
    private int score;
    private int topScore;
    private boolean gameOver = true;

    public void reset() {
        setTopScore();
        lines = 0;
        score = 0;
        gameOver = false;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
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

    public void addLines(int line) {

        switch (line) {
            case 1:
                addScore(10);
                break;
            case 2:
                addScore(20);
                break;
            case 3:
                addScore(30);
                break;
            case 4:
                addScore(40);
                break;
            default:
                return;
        }

        lines += line;
    }


    public int getLines() {
        return lines;
    }

    public int getScore() {
        return score;
    }
}