import java.sql.*;

class GameController {
    private final Board board;
    private final Player playerX;
    private final Player playerO;

    public GameController(Player playerX, Player playerO) {  // Внедрение зависимостей
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
    }

    private void saveGameResult(char winner) {
        Connection connection = DatabaseManager.getInstance().getConnection();
        if (connection == null) {
            System.out.println("Ошибка: нет подключения к базе данных.");
            return;
        }

        try {
            String query = "INSERT INTO games (winner) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, winner == '-' ? "Draw" : String.valueOf(winner));
            statement.executeUpdate();
            System.out.println("Результат игры сохранён в базе данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении результата.");
            e.printStackTrace();
        }
    }

    public void startGame() {
        Player currentPlayer = playerX;
        boolean gameActive = true;

        while (gameActive) {
            board.display();
            System.out.println("Player " + currentPlayer.getSymbol() + "'s turn.");
            currentPlayer.makeMove(board);

            if (board.checkWin(currentPlayer.getSymbol())) {
                board.display();
                System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                saveGameResult(currentPlayer.getSymbol()); // сохранение результата
                gameActive = false;
            } else if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                saveGameResult('-'); // сохранение результата ничьей
                gameActive = false;
            }

            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }
    }
}