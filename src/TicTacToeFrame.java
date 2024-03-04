import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private static final int ROW = 3;
    private static final int COL = 3;
    private JButton[][] buttons = new JButton[ROW][COL];
    private String currentPlayer = "X";
    private static String[][] board = new String[ROW][COL];

    public TicTacToeFrame() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(ROW, COL));
        ActionListener buttonListener = new ButtonListener();

        // Initialize the GUI board and the logical board
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
                JButton button = new JButton();
                buttons[i][j] = button;
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                button.setActionCommand(i + "," + j);  // Set the button's action command to its row,col
                button.addActionListener(buttonListener);
                add(button);
            }
        }
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            int row = Integer.parseInt(command.split(",")[0]);
            int col = Integer.parseInt(command.split(",")[1]);
            JButton button = buttons[row][col];

            if (!button.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid move! The cell is already occupied.");
                return;
            }

            if (button.getText().equals("") && !isGameOver()) {
                board[row][col] = currentPlayer;
                button.setText(currentPlayer);
                if (checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    int playAgain = JOptionPane.showConfirmDialog(null, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                    if (playAgain == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(null, "The game is a draw!");
                    int playAgain = JOptionPane.showConfirmDialog(null, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                    if (playAgain == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                } else {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                }
            }
        }
    }

    private boolean isGameOver() {
        return checkWin("X") || checkWin("O") || checkDraw();
    }

    private void resetGame() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
                buttons[i][j].setText("");
            }
        }
    }

    private static boolean checkWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        // checks for a col win for specified player
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {
                return true;
            }
        }
        return false; // no col win
    }

    private static boolean isRowWin(String player) {
        // checks for a row win for the specified player
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {
                return true;
            }
        }
        return false; // no row win
    }

    private static boolean isDiagonalWin(String player) {
        // checks for a diagonal win for the specified player
        return (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) ||
                (board[0][2].equals(player) &&
                        board[1][1].equals(player) &&
                        board[2][0].equals(player));
    }

    private static boolean checkDraw() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false; // The game is not a draw, there are still empty cells
                }
            }
        }
        return true; // All cells are filled, and no player has won
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}
