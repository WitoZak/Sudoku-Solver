package com.wito.sudokusolver;

public class SudokuGame {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
                {0, 2, 8, 9, 0, 5, 7, 0, 4},
                {0, 0, 4, 1, 0, 0, 5, 9, 6},
                {9, 1, 0, 6, 4, 0, 0, 2, 3},
                {0, 4, 2, 8, 0, 0, 6, 3, 5},
                {1, 9, 6, 3, 0, 4, 2, 0, 7},
                {0, 8, 3, 0, 0, 0, 1, 0, 0},
                {2, 6, 0, 0, 0, 3, 4, 7, 0},
                {0, 0, 0, 4, 0, 1, 9, 5, 2},
                {0, 0, 0, 7, 0, 0, 3, 0, 0},
        };

        printBoard(board);


        if (boardSolver(board)) {
            System.out.println("""

                    Solved successfully!
                    """);
        } else {
            System.out.println("""

                    Unsolvable board!
                    """);
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }


    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInCol(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberIn3x3(int[][] board, int number, int row, int col) {
        int local3x3Row = row - row % 3;
        int local3x3Col = col - col % 3;

        for (int i = local3x3Row; i < local3x3Row + 3; i++) {
            for (int j = local3x3Col; j < local3x3Col + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidMove(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInCol(board, number, col) &&
                !isNumberIn3x3(board, number, row, col);
    }

    private static boolean boardSolver(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidMove(board, numberToTry, row, col)) {
                            board[row][col] = numberToTry;

                            if (boardSolver(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
