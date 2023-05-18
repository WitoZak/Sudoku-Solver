# Sudoku Solver

This is a Java program that allows you to play Sudoku and solve the Sudoku board using a backtracking algorithm. The program provides a command-line interface for interacting with the game.

## How to Play

1. Run the `SudokuGame` class to start the game.
2. An empty Sudoku board will be displayed, represented by a 9x9 grid.
3. Enter your moves in the format `row, column, value` to place a number on the board. For example, to place the number 5 in the second row and third column, enter `2, 3, 5`. You can enter multiple moves in a single input by separating them with commas.
4. To solve the Sudoku board, type `SUDOKU` and press Enter. The program will attempt to solve the board using a backtracking algorithm.
5. If the board is solvable, the solved board will be displayed, along with the number of loop iterations and guess procedures performed.
6. After the game ends, you will be asked if you want to play again. Enter `y` to play again or `n` to quit.

## Rules of Sudoku

The goal of Sudoku is to fill the 9x9 grid with numbers from 1 to 9 so that each row, each column, and each of the nine 3x3 sub-grids contains all the numbers from 1 to 9 without any repetition.

## Implementation Details

The program uses a backtracking algorithm to solve the Sudoku board. It starts by finding an empty cell and tries different numbers from 1 to 9 in that cell. If a number is valid (i.e., it doesn't violate any Sudoku rules), it is placed in the cell. The algorithm then moves to the next empty cell and repeats the process. If a number doesn't lead to a solution, it backtracks and tries the next number until a solution is found or all possibilities are exhausted.

The program also includes input validation to ensure that the user's moves are valid. It checks for duplicate entries in rows, columns, and 3x3 sub-grids, and handles invalid inputs gracefully.

![Sudoku](https://github.com/WitoZak/Sudoku-Solver/assets/113088417/eaf43216-58fa-4149-a6f9-fc6ea92d16b4)
