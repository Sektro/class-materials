package hu.elte.progtech.jdbc.mysql.examples.tetris.persistence.dao;

import hu.elte.progtech.jdbc.mysql.examples.tetris.persistence.connection.DataSource;
import hu.elte.progtech.jdbc.mysql.examples.tetris.persistence.entity.HighScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreDao implements IEntity<HighScore> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM high_score";
    private static final String INSERT_QUERY = "INSERT INTO high_score (name, score) VALUES (?, ?)";
    private static final String TOP_HIGH_SCORE_QUERY = "SELECT score FROM high_score ORDER BY score DESC LIMIT 1";

    private static final String ATTR_NAME_ID = "id";
    private static final String ATTR_NAME_NAME = "name";
    private static final String ATTR_NAME_SCORE = "score";

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

    @Override
    public void add(HighScore entity) throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getScore());

            preparedStatement.executeUpdate();
        }
    }

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
}
