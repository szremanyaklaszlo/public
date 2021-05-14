package com.epam.training.queens;

import java.util.ArrayList;

public class EightQueens {

    public static void main(String[] args) {
        Board result = new Board();
        ArrayList<Board> solutions = solveAllNQueens(result, 0);
        System.out.println(solutions.size());
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nSolution " + (i + 1));
            Board board = solutions.get(i);
            System.out.println(board);
        }
    }

    private static ArrayList<Board> solveAllNQueens(Board board, int column) {
        ArrayList<Board> solutions = new ArrayList<>();
        if (isTheEndOfTheTable(column)) {
            solutions.add(board.copy());
        } else {
            putNextQueen(board, column, solutions);
        }
        return solutions;
    }

    private static void putNextQueen(Board board, int column, ArrayList<Board> solutions) {
        for (int row = 0; row < Board.SIZE; row++) {
            Queen queen = new Queen(new Coordinate(row, column));
            if (board.isSafe(queen)) {
                board.addQueen(queen);
                solutions.addAll(moveNextColumn(board, column));
                board.removeQueen(queen);
            }
        }
    }

    private static ArrayList<Board> moveNextColumn(Board board, int column) {
        return solveAllNQueens(board, column + 1);
    }

    private static boolean isTheEndOfTheTable(int column) {
        return column == Board.SIZE;
    }

}
