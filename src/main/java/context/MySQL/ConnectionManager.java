package context.MySQL;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionManager {

    private static Connection con;

    public static Connection getConnection() {
        InputStream input;
        input = ConnectionManager.class.getClassLoader().getResourceAsStream("application.properties");
        Properties prop = new Properties();
        try {
            prop.load(input);
            con = DriverManager.getConnection(prop.getProperty("host"), prop.getProperty("username"), prop.getProperty("password"));
            System.out.println("Database is verbonden!");
        } catch (IOException | SQLException ex ) {
            ex.printStackTrace();
        }
        return con;
    }
}
