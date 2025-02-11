import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/tictactoe",
                    "postgres",
                    "akonchik"
            );
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных.");
            e.printStackTrace();
        }
    }


    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }


    public Connection getConnection() {
        return connection;
    }
}