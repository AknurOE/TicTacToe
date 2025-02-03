import java.util.Scanner;
import java.sql.*;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Крестики-нолики ===");
            System.out.println("1. Игрок vs Игрок");
            System.out.println("2. Игрок vs Компьютер");
            System.out.println("3. Список победителей");
            System.out.println("4. Выход");
            System.out.print("Выберите режим: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new GameController(true).startGame();  // Игрок vs Игрок
                    break;
                case 2:
                    new GameController(false).startGame();  // Игрок vs Компьютер
                    break;
                case 3:
                    showWinnerList();  // Отображение списка победителей
                    break;
                case 4:
                    exit = true;
                    System.out.println("Выход из игры...");
                    break;
                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
        scanner.close();
    }

    private static void showWinnerList() {
        String url = "jdbc:postgresql://localhost:5432/tictactoe";
        String username = "postgres";
        String password = "akonchik";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT winner FROM games ORDER BY id DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\nСписок победителей:");
            while (resultSet.next()) {
                String winner = resultSet.getString("winner");
                System.out.println(winner);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
