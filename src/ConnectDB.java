import java.sql.*;

public class ConnectDB {
    private Connection c = null;
    public ConnectDB() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:datemuch.db");
            c.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
