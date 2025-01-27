import java.sql.*;

class GameController {
    private final Board board;
    private final Player playerX;
    private final Player playerO;

    public GameController() {
        board = new Board();
        playerX = new HumanPlayer('X');
        playerO = new AIPlayer('O');
    }

    private void saveGameResult(char winner) {
        String url = "jdbc:postgresql://localhost:5432/tictactoe";
        String username = "postgres";
        String password = "akonchik";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO games (winner) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, winner == '-' ? "Draw" : String.valueOf(winner));
            statement.executeUpdate();
            System.out.println("Game result saved to database.");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
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
                saveGameResult(currentPlayer.getSymbol()); // сохранение резов
                gameActive = false;
            } else if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                saveGameResult('-'); // сохранение рез ничья
                gameActive = false;
            }

            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }
    }
}


