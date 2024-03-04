public class TTTBoard {
    private static final int ROW = 3;
    private static final int COL = 3;
    private String[][] board = new String[ROW][COL];
    private String currentPlayer = "X";

    public TTTBoard() {
        resetGame();
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    public boolean isGameOver() {
        return isWin("X") || isWin("O") || isTie();
    }

    public boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private boolean isColWin(String player) {
        // Check for a column win for the specified player
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player) {
        // Check for a row win for the specified player
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        // Check for a diagonal win for the specified player
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private boolean isTie() {
        // Check for a tie before the board is filled
        return !isWin("X") && !isWin("O") &&
                board[0][0].equals("X") && board[0][1].equals("O") && board[0][2].equals("X") &&
                board[1][0].equals("O") && board[1][1].equals("X") && board[1][2].equals("O") &&
                board[2][0].equals("X") && board[2][1].equals("O") && board[2][2].equals("X");
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void resetGame() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
        currentPlayer = "X";
    }
}
