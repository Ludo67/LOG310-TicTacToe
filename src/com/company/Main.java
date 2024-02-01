package com.company;

import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scanner = new Scanner(System.in);

        boolean gameOver = false;
        boolean validInput = false;

        Mark[][] marks = new Mark[3][3];
        for (int r = 0; r < marks.length; r++) {

            for (int c = 0; c < marks[r].length; c++) {
                marks[r][c] = Mark.EMPTY;
            }
        }

        Board board = new Board(marks);
        CPUPlayer player = new CPUPlayer(Mark.X);
        int CpuGameState;
        int plrGameState;

        printBoard(board, marks);

        while (!gameOver) {

            int inputRow = 0;
            int inputCol = 0;
            while (!validInput) {
                System.out.println("Enter row: ");

                inputRow = scanner.nextInt();

                System.out.println("Enter column: ");

                inputCol = scanner.nextInt();

                if (board.isEmpty(inputRow, inputCol)) {
                    validInput = true;
                } else{
                    System.out.println("ALREADY TAKEN!");
                }
            }
            validInput = false;

            Move move = new Move(inputRow, inputCol);
            board.play(move, Mark.O);

            ArrayList<Move> bestMoveCpu = player.getNextMoveMinMax(board, true);

            if (!bestMoveCpu.isEmpty()) {
                System.out.println("============");
                System.out.println("CPU Plays: ");
                System.out.println("Col: " + bestMoveCpu.get(0).getCol() + " | Row: " + bestMoveCpu.get(0).getRow());
                System.out.println("============");
                board.play(bestMoveCpu.get(0), Mark.X);
            }

            printBoard(board, marks);

            if ((board.evaluate(Mark.X) != 0) || (board.evaluate(Mark.O) != 0)) {
                gameOver = true;
                CpuGameState = board.evaluate(Mark.X);
                plrGameState = board.evaluate(Mark.O);
                System.out.println("CPU: " + CpuGameState + " plr " + plrGameState);
            }

        }


    }

    public static void printBoard(Board board, Mark[][] marks) {
        int rowNum = 0;
        System.out.println("    0    1    2  ");
        for (int r = 0; r < marks.length; r++) {

            System.out.print(rowNum + " ");
            for (int c = 0; c < marks[r].length; c++) {
                if (board.getMark(r, c) == Mark.X) {
                    System.out.print("| X |");
                }
                else if (board.getMark(r, c) == Mark.O) {
                    System.out.print("| O |");
                }
                else {
                    System.out.print("|   |");
                }
            }
            System.out.println("");
            rowNum++;
        }
    }

}
