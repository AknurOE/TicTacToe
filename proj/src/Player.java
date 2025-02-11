import java.util.Random;
import java.util.Scanner;

// Polymorphism and Encapsulation!!!!!!!!
abstract class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract void makeMove(Board board);
}

// хуман
class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(char symbol) {
        super(symbol);
        scanner = new Scanner(System.in);
    }

    @Override
    public void makeMove(Board board) {
        boolean validMove = false;
        while (!validMove) {
            System.out.print("Enter row (0, 1, 2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0, 1, 2): ");
            int col = scanner.nextInt();

            if (board.makeMove(row, col, getSymbol())) {
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}

// ии плейер
class AIPlayer extends Player {
    private final Random random;

    public AIPlayer(char symbol) {
        super(symbol);
        random = new Random();
    }

    @Override
    public void makeMove(Board board) {
        boolean validMove = false;
        while (!validMove) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);

            if (board.makeMove(row, col, getSymbol())) {
                validMove = true;
                System.out.println("AI places " + getSymbol() + " at (" + row + ", " + col + ")");
            }
        }
    }
}