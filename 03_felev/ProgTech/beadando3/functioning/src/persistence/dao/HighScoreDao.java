package hu.elte.progtech.snake.persistence.dao;

import hu.elte.progtech.snake.persistence.connection.DataSource;
import hu.elte.progtech.snake.persistence.entity.HighScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreDao implements IEntity<HighScore> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM highscore";
    private static final String INSERT_QUERY = "INSERT INTO highscore (name, score) VALUES (?, ?)";
    private static final String TOP_HIGH_SCORE_QUERY = "SELECT score FROM highscore ORDER BY score DESC LIMIT 1";
    private static final String TOP_10_HIGH_SCORE_QUERY = "SELECT * FROM highscore ORDER BY score DESC LIMIT 10";

    private static final String ATTR_NAME_ID = "id";
    private static final String ATTR_NAME_NAME = "name";
    private static final String ATTR_NAME_SCORE = "score";

    /**
     * Lists all users with their respective scores and ids
     * @return returns the list of the scores
     * @throws SQLException
     */
    @Override
    public List<HighScore> getAll() throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            final List<HighScore> entities = new ArrayList<>();
            while(resultSet.next()) {
                final HighScore entity = new HighScore();
                entity.setId(resultSet.getLong(ATTR_NAME_ID));
                entity.setName(resultSet.getString(ATTR_NAME_NAME));
                entity.setScore(resultSet.getInt(ATTR_NAME_SCORE));
                entities.add(entity);
            }
            return entities;
        }
    }

    /**
     * Adds a new score to the table (along with the username).
     * @param entity
     * @throws SQLException
     */
    @Override
    public void add(HighScore entity) throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getScore());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * A method used for seeing who's the best.
     * @return returns the highest score on the table.
     * @throws SQLException
     */
    public int getTopScore() throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOP_HIGH_SCORE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if(resultSet.next()) {
                return resultSet.getInt(ATTR_NAME_SCORE);
            }
        }
        return 0;
    }

    /**
     * Lists the top 10 highest scores and the players' usernames who've reached them.
     * @return returns 10 highest scores and the usernames with them
     * @throws SQLException
     */
    public ArrayList<HighScore> getTop10() throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(TOP_10_HIGH_SCORE_QUERY)) {

            final ArrayList<HighScore> entities = new ArrayList<>();
            while(resultSet.next()) {
                final HighScore entity = new HighScore();
                entity.setId(resultSet.getLong(ATTR_NAME_ID));
                entity.setName(resultSet.getString(ATTR_NAME_NAME));
                entity.setScore(resultSet.getInt(ATTR_NAME_SCORE));
                entities.add(entity);
            }
            return entities;
        }
    }
}
