package com.epam.training.exercise3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EightQueens {
    private static final char EMPTY_CELL = '.';
    private static final char QUEEN_CELL = 'q';
    private static final int BOARD_SIZE = 8;

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();

        List<char[][]> solutions = new ArrayList<>();
        char[][] initiatedBoard = eightQueens.initializeBoard();
        eightQueens.solveAllNQueens(initiatedBoard, 0, solutions);
        eightQueens.output(solutions);
    }

    private char[][] initializeBoard() {
        char[][] initiatedBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int r1 = 0; r1 < BOARD_SIZE; r1++) {
            Arrays.fill(initiatedBoard[r1], EMPTY_CELL);
        }
        return initiatedBoard;
    }

    private void copyToResults(char[][] board, List<char[][]> solutions) {
        char[][] copy = new char[board.length][board[0].length];
        IntStream.range(0, board.length)
                .forEach(r -> System.arraycopy(board[r], 0, copy[r], 0, board[0].length));
        solutions.add(copy);
    }

    private void solveAllNQueens(char[][] board, int col, List<char[][]> solutions) {
        if (col == board.length) {
            copyToResults(board, solutions);
        } else {
            for (int row = 0; row < board.length; row++) {
                boolean canBeSafe = placeQueen(board, row, col);
                if (canBeSafe) {
                    solveAllNQueens(board, col + 1, solutions);
                }
                board[row][col] = EMPTY_CELL;
            }
        }
    }

    private boolean checkQueenCanBePlacedThisCell(char[][] board, int row, int column) {
        return checkRowIsSafe(board, row)
                && checkColumnIsSafe(board, column)
                && checkFirstDiagonalIsSafe(board, row - column)
                && checkSecondDiagonalIsSafe(board, row + column);
    }

    private boolean placeQueen(char[][] board, int row, int column) {
        if (checkQueenCanBePlacedThisCell(board, row, column)) {
            board[row][column] = QUEEN_CELL;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRowIsSafe(char[][] board, int row) {
        for (char cell : board[row]) {
            if (cell == QUEEN_CELL) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumnIsSafe(char[][] board, int column) {
        for (char[] row : board) {
            if (row[column] == QUEEN_CELL) {
                return false;
            }
        }
        return true;
    }

    private boolean checkFirstDiagonalIsSafe(char[][] board, int pointOfSuggest) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isOutsideBounds(board, pointOfSuggest + i, i)) {
                continue;
            }
            if (board[i + pointOfSuggest][i] == QUEEN_CELL) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSecondDiagonalIsSafe(char[][] board, int pointOfSuggest) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isOutsideBounds(board, i, pointOfSuggest - i)) {
                continue;
            }
            if (board[i][pointOfSuggest - i] == QUEEN_CELL) {
                return false;
            }
        }
        return true;
    }


    private boolean isOutsideBounds(char[][] board, int row, int column) {
        return row < 0 || row >= board.length || column < 0
                || column >= board.length;
    }

    private void output(List<char[][]> solutions) {
        System.out.println("\nThere are " + solutions.size() + " solutions found.");
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nSolution #" + (i + 1));

            char[][] board = solutions.get(i);

            for (char[] chars : board) {
                for (char ch : chars) System.out.print(ch);
                System.out.println();
            }
        }
    }

}