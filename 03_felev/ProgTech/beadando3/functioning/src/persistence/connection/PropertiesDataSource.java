package hu.elte.progtech.snake.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class PropertiesDataSource {
    /**
     * Where the database is located
     */
    private final String url;
    /**
     * The user's name
     */
    private final String user;
    /**
     * The score the user reached.
     */
    private final String password;

    PropertiesDataSource(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("The given properties file should not be null!");
        }
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = properties.getProperty("db.password");
    }

    /**
     * Creates a connection between the database and the program.
     * Through this channel we can access the information in the database.
     *
     *
     * @return creates the connection
     * @throws SQLException if the database couldn't be reached or a different kind of problem occured with the connection
     *      *               or the url wasn't loaded
     *
     */
    Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver couldn't be found: " + e.getMessage());
        }
        return DriverManager.getConnection(url, user, password);
    }
}