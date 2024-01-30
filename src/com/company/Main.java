package com.company;

import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        boolean gameOver = false;

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

        while (!gameOver) {
            for (int r = 0; r < marks.length; r++) {

                for (int c = 0; c < marks[r].length; c++) {
                    if (marks[r][c] == Mark.X) {
                        System.out.print("| X |");
                    }
                    else if (marks[r][c] == Mark.O) {
                        System.out.print("| O |");
                    }
                    else {
                        System.out.print("|   |");
                    }
                }
                System.out.println("");
            }
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter row: ");

            int row = scanner.nextInt();

            System.out.println("Enter column: ");

            int col = scanner.nextInt();

            Move move = new Move(row, col);
            board.play(move, Mark.O);

            ArrayList<Move> bestMoveCpu = player.getNextMoveMinMax(board, true);
            System.out.println("Col: " + bestMoveCpu.get(0).getCol());
            System.out.println("Row: " + bestMoveCpu.get(0).getRow());
            if (!bestMoveCpu.isEmpty()) {
                Move newMove = new Move(bestMoveCpu.get(0).getRow(), bestMoveCpu.get(0).getCol());
                board.play(newMove, Mark.X);
            }

//            if ((board.evaluate(Mark.X) != -100) || (board.evaluate(Mark.O) != -100)) {
//                gameOver = true;
//                CpuGameState = board.evaluate(Mark.X);
//                plrGameState = board.evaluate(Mark.O);
//                System.out.println("CPU: " + CpuGameState + " plr " + plrGameState);
//            }

        }


    }
}
