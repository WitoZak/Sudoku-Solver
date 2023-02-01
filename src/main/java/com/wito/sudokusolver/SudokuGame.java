package com.wito.sudokusolver;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuGame {

    private static final int GRID_SIZE = 9;
    private static int loopCounter = 0;
    private static int guessCounter = 0;

    public static void main(String[] args) {
        int[][] userInputBoard = new int[GRID_SIZE][GRID_SIZE];
        printEmptyBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your move (row, column, value) or type 'SUDOKU' to solve the board:");
            getUserInputBoard(scanner, userInputBoard);
        }
    }


    private static void getUserInputBoard(Scanner scanner, int[][] userInputBoard) {
        try {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("SUDOKU")) {
                boardSolver(userInputBoard);
                System.out.println("Solved successfully!");
                printBoard(userInputBoard);
                System.out.println("Thanks for playing! Difficulty rating: " + loopCounter + " loop iterations, " + guessCounter + " guess procedures");
                playAgain(scanner, userInputBoard);
            } else {
                String[] inputs = input.split(",");

                for (int i = 0; i < inputs.length; i += 3) {
                    int row = Integer.parseInt(inputs[i]) - 1;
                    int col = Integer.parseInt(inputs[i + 1]) - 1;
                    int var = Integer.parseInt(inputs[i + 2]);

                    if(userInputBoard[row][col] != 0 ||
                            inputs.length % 3 != 0) {
                        throw new IllegalArgumentException();
                    }
                    userInputBoard[row][col] = var;
                }
                printBoard(userInputBoard);

            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid command entered, Please enter 'SUDOKU' to solve the board or  'row, column, value' to make a move.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please enter a value between 1 and 9");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. The cell is already occupied.");
        }
    }

    private static void playAgain(Scanner scanner, int[][] userInputBoard) {
        System.out.println("Do you want to play again? (y/n)");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("y")) {
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    userInputBoard[i][j] = 0;
                }
            }
            printEmptyBoard();

        } else if (playAgain.equalsIgnoreCase("n")) {
            System.out.println("See You later!");
            System.exit(0);
        } else {
            System.out.println("Invalid input. Please enter 'y' to play again or 'n' to quit.");
            playAgain(scanner, userInputBoard);
        }
    }

    private static void printBoard(int[][] userInputBoard) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(userInputBoard[row][col]);
            }
            System.out.println();
        }
    }

    private static void printEmptyBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print("0");
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

    private static boolean boardSolver(int[][] userInputBoard) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (userInputBoard[row][col] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidMove(userInputBoard, numberToTry, row, col)) {
                            userInputBoard[row][col] = numberToTry;
                            loopCounter++;
                            if (boardSolver(userInputBoard)) {
                                guessCounter--;
                                return true;
                            } else {
                                userInputBoard[row][col] = 0;
                                guessCounter++;
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
