package hu.elte.progtech.snake.persistence.connection;

import hu.elte.progtech.snake.io.PropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;

public final class DataSource {

    private final PropertiesDataSource dbConnection;

    private DataSource() {
        this.dbConnection = new PropertiesDataSource(PropertiesReader.readProperties("./config/config.properties"));
    }

    /**
     * Returns the currently used connection (which is handled by "dbConnection")
     * @return returns connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }

    /**
     *
     * @return returns the datasource
     */
    public static DataSource getInstance() {
        return DataSourceInstanceHolder.INSTANCE;
    }

    private static class DataSourceInstanceHolder {
        private static final DataSource INSTANCE = new DataSource();
    }
}
