package com.company;

import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here

        // Declarer le scanner
        Scanner scanner = new Scanner(System.in);

        // Etats du jeu
        boolean gameOver = false;
        boolean validInput = false;

        // Settings
        Mark cpuMark = Mark.X;
        Mark plrMark = Mark.O;

        // Creation du jeu
        Mark[][] marks = new Mark[3][3];
        for (int r = 0; r < marks.length; r++) {

            for (int c = 0; c < marks[r].length; c++) {
                marks[r][c] = Mark.EMPTY;
            }
        }

        Board board = new Board(marks);
        CPUPlayer player = new CPUPlayer(cpuMark);

        // Afficher le jeu
        printBoard(board, marks);

        // Jouer le jeu tant que le jeu n'est pas finit
        while (!gameOver) {

            int inputRow = 0;
            int inputCol = 0;

            // Attendre le "input" du joueur et valider
            while (!validInput) {
                System.out.println("============");
                System.out.print("Enter row: ");

                inputRow = scanner.nextInt();

                System.out.print("Enter column: ");

                inputCol = scanner.nextInt();
                System.out.println("============");
                if (board.isEmpty(inputRow, inputCol)) {
                    validInput = true;
                } else{
                    System.out.println("LA CASE EST DEJA PRISE");
                }
            }
            validInput = false;

            // Jouer
            Move move = new Move(inputRow, inputCol);
            board.play(move, plrMark);

            // Obtenir le meilleur coup avec minimax
            ArrayList<Move> bestMoveCpu = player.getNextMoveMinMax(board, true);

            // Jouer le coup du CPU
            if (!bestMoveCpu.isEmpty()) {
                System.out.println("============");
                System.out.println("CPU joue: ");
                System.out.println("Row: " + bestMoveCpu.get(0).getRow() + " | Col: " + bestMoveCpu.get(0).getCol());
                System.out.println("============");
                board.play(bestMoveCpu.get(0), cpuMark);
            }

            // Afficher le jeu
            printBoard(board, marks);

            // Verifier l'état du jeu; si c'est finit
            int CpuGameState = board.evaluate(cpuMark);
            int plrGameState = board.evaluate(plrMark);

            if ((CpuGameState != 1) || (plrGameState != 1)) {
                gameOver = true;
                System.out.println("============");
                if (CpuGameState == 100) {
                    System.out.println("CPU a gagné!");
                }
                else if (plrGameState == 100) {
                    System.out.println("Joueur a gagné!");
                }
                else {
                    System.out.println("Égalité!");
                }
                System.out.println("============");
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
