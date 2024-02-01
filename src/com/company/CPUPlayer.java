package com.company;
import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark cpuPlr;
    private Mark plr;
    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.cpuPlr = cpu;

        if (cpuPlr == Mark.X) {
            this.plr = Mark.O;
        }
        else {
            this.plr = Mark.X;
        }

    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board, boolean isMaximizing) {
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        // Parcourir les cases vides du plateau
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                // Verifier si cest vide
                if(board.isEmpty(r, c)) {
                    numExploredNodes += 1;

                    Move move = new Move(r, c);

                    board.play(move, cpuPlr);
                    int score = minimax(board, 0, false);
                    board.play(move, Mark.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        bestMoves.clear();
                        bestMoves.add(move);
                    }

                }
            }
        }
        return bestMoves;
    }

    public int minimax(Board board, int depth, boolean isMaximizing) {
        // Regarder si le jeu est finit
        int gameState = board.evaluate(cpuPlr);

        // Calculer le score selon le depth du code recursif
        if (gameState != 1) {
            return gameState - depth;
        }

        // Minimax
        if (isMaximizing) {
            // Code pour maximiser
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Verifier si on peut jouer
                    if (board.isEmpty(i, j)) {
                        Move move = new Move(i, j);
                        board.play(move, cpuPlr);
                        int score = minimax(board, depth + 1, false);
                        board.play(move, Mark.EMPTY);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            // Code pour minimiser
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Verifier si on peut jouer
                    if (board.isEmpty(i, j)) {
                        Move move = new Move(i, j);
                        board.play(move, plr);
                        int score = minimax(board, depth + 1, true);
                        board.play(move, Mark.EMPTY);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public int alphabeta(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        // Regarder si le jeu est fini
        int gameState = board.evaluate(cpuPlr);

        // Calculer le score selon le depth du code recursif
        if (gameState != 1) {
            return gameState - depth;
        }

        // Minimax
        if (isMaximizing) {
            // Code pour maximiser
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Verifier si on peut jouer
                    if (board.isEmpty(i, j)) {
                        Move move = new Move(i, j);
                        board.play(move, cpuPlr);
                        int score = alphabeta(board, depth + 1, alpha, beta, false);
                        board.play(move, Mark.EMPTY); // Remettre la case dans son état vide
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isEmpty(i, j)) {
                        Move move = new Move(i, j);
                        board.play(move, plr);
                        int score = alphabeta(board, depth + 1, alpha, beta, true);
                        board.play(move, Mark.EMPTY); // Remettre la case dans son état vide
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, bestScore);
                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }
            return bestScore;
        }
    }


    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seulement si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board, boolean isMaximizing){
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        // Parcourir les cases vides du plateau
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(board.isEmpty(r, c)) {
                    numExploredNodes += 1;

                    Move move = new Move(r, c);

                    board.play(move, cpuPlr);
                    int score = alphabeta(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    board.play(move, Mark.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        bestMoves.clear();
                        bestMoves.add(move);
                    }

                }

            }
        }
        return bestMoves;
    }

}
