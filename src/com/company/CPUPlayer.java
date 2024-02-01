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
                if(board.isEmpty(r, c)) {
                    numExploredNodes += 1;

                    Move move = new Move(r, c);

                    board.play(move, cpuPlr);
                    int score = minimax(board, 0, false);
                    System.out.println(score + " " + bestScore);
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
        int gameState = board.evaluate(cpuPlr);

        if (gameState != 0) {
            return gameState; // Return the evaluation score directly
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
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
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
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


    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();

        // Parcourir les cases vides du plateau
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(board.isEmpty(r, c)){
                    // Create move
                    Move move = new Move(r, c);

                    // TODO: Utiliser l'algorhitme minMax pour trier la liste des coups
                    // TODO: Retourner les meilleurs coups

                }

            }
        }




        return bestMoves;
    }

}
