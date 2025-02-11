// боард класс для инкапсуляции поля
class Board {
    private final char[][] grid; // массив для хранения состояния поля
    private static final int SIZE = 3;

    public Board() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '-'; // ячейки пустые
            }
        }
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == '-') {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                    (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true;
            }
        }
        return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
                (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
    }

    public boolean isFull() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }


    public void display() {

        System.out.println("\n=================================");
        System.out.println("      TIC-TAC-TOE GAME");
        System.out.println("=================================\n");

        System.out.println("  +---+---+---+");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("  | ");
            for (int j = 0; j < SIZE; j++) {
                char symbol = getCell(i, j);

                System.out.print((symbol == 'X' ? "X" : symbol == 'O' ? "O" : " ") + " | ");
            }
            System.out.println("\n  +---+---+---+");
        }
    }
}
