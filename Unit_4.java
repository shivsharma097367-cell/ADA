import java.util.*;

public class NQueen {

    static int N = 4;

    // 🔹 Print Board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 🔹 Check if position is safe
    public static boolean isValid(int[][] board, int row, int col) {

        // Check left side of row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // 🔹 Solve using Backtracking
    public static boolean solveNQueen(int[][] board, int col) {

        if (col >= N)
            return true;

        for (int i = 0; i < N; i++) {

            if (isValid(board, i, col)) {
                board[i][col] = 1;

                if (solveNQueen(board, col + 1))
                    return true;

                // Backtrack
                board[i][col] = 0;
            }
        }
        return false;
    }

    // 🔹 Main Method
    public static void main(String[] args) {

        int[][] board = new int[N][N];

        if (!solveNQueen(board, 0)) {
            System.out.println("Solution does not exist");
        } else {
            printBoard(board);
        }
    }
}
