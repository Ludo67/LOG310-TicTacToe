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
        boolean minimaxValidated = false;
        boolean isMinimax = true;

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

        // Choisir game mode:
        System.out.println("Selectionner mode de jeu:");
        System.out.println("Entrez 1 pour minimax, 2 pour alphabeta");

        while (!minimaxValidated)
        {
            int entry;
            // Valider si l'entrée est correcte
            while (!scanner.hasNextInt()) {
                System.out.println("VEUILLEZ ENTRER UN CHIFFRE.");
                scanner.next();
                System.out.println("Entrez 1 pour minimax, 2 pour alphabeta");
            }

            entry = scanner.nextInt();

            while (entry != 1 && entry != 2) {
                System.out.println("ENTRÉE INVALIDE. Entrez 1 pour minimax, 2 pour alphabeta");
                System.out.println(entry != 1 || entry != 2);
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrez 1 pour minimax, 2 pour alphabeta.");
                    scanner.next();
                }
                entry = scanner.nextInt();
            }

            if (entry == 1) {
                isMinimax = true;
            }
            else {
                isMinimax = false;
            }
            minimaxValidated = true;
        }

        // Afficher le jeu
        printBoard(board, marks);

        // Jouer le jeu tant que le jeu n'est pas finit
        while (!gameOver) {

            int inputRow = 0;
            int inputCol = 0;

            // Attendre le "input" du joueur et valider
            while (!validInput) {
                System.out.println("============");
                System.out.print("ENTRER LA RANGÉE: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("VEUILLEZ ENTRER UN CHIFFRE POUR LA RANGÉE.");
                    scanner.next();
                    System.out.print("ENTRER LA RANGÉE: ");
                }
                inputRow = scanner.nextInt();

                // Valider si l'entrée est correcte
                while (inputRow < 0 || inputRow >= 3) {
                    System.out.println("ENTRÉE INVALIDE. VEUILLEZ ENTRER UN CHIFFRE ENTRE 0 ET 2");
                    System.out.print("ENTRER LA RANGÉE: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("VEUILLEZ ENTRER UN CHIFFRE POUR LA RANGÉE.");
                        scanner.next();
                        System.out.print("ENTRER LA RANGÉE: ");
                    }
                    inputRow = scanner.nextInt();
                }

                System.out.print("ENTRER LA COLONNE: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("VEUILLEZ ENTRER UN CHIFFRE POUR LA COLONNE.");
                    scanner.next();
                    System.out.print("ENTRER LA COLONNE: ");
                }
                inputCol = scanner.nextInt();

                // Valider si l'entrée est correcte
                while (inputCol < 0 || inputCol >= 3) {
                    System.out.println("ENTRÉE INVALIDE. VEUILLEZ ENTRER UN CHIFFRE ENTRE 0 ET 2");
                    System.out.print("ENTRER LA COLONNE: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("VEUILLEZ ENTRER UN CHIFFRE POUR LA COLONNE.");
                        scanner.next();
                        System.out.print("ENTRER LA COLONNE: ");
                    }
                    inputCol = scanner.nextInt();
                }

                System.out.println("============");

                if (board.isEmpty(inputRow, inputCol)) {
                    validInput = true;
                } else {
                    System.out.println("LA CASE EST DÉJA PRISE !");
                }
            }
            validInput = false;

            // Jouer
            Move move = new Move(inputRow, inputCol);
            board.play(move, plrMark);

            ArrayList<Move> bestMoveCpu;
            if (isMinimax) {
                // Obtenir le meilleur coup avec minimax
                bestMoveCpu = player.getNextMoveMinMax(board);
            }
            else {
                // Obtenir le meilleur coup avec Alphabeta
                bestMoveCpu = player.getNextMoveAB(board);
            }

            // Jouer le coup du CPU
            if (!bestMoveCpu.isEmpty()) {
                System.out.println("============");
                System.out.println("Explored nodes: " + player.getNumOfExploredNodes());
                System.out.println("IA joue: ");
                System.out.println("Rangée: " + bestMoveCpu.get(0).getRow() + " | Colonne: " + bestMoveCpu.get(0).getCol());
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
                    System.out.println("IA a gagné!");
                }
                else if (plrGameState == 100) {
                    System.out.println("Joueur a gagné!");
                }
                else {
                    System.out.println("Partie nulle!");
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
