package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {


    private static final Connection connection;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String dbUser = "admin";
            String dbPass = "admin";
            String dbUrl = "jdbc:mysql://localhost:3306/project_management_db";
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
