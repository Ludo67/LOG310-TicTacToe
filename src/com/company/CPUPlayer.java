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
    public ArrayList<Move> getNextMoveMinMax(Board board, boolean isMaximizing)
    {
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        Board duplicateBoard = new Board(board);
        // Parcourir les cases vides du plateau
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(duplicateBoard.isEmpty(r, c)){
                    numExploredNodes += 1;
                    // Create move
                    Move move = new Move(r, c);

                    // TODO: Utiliser l'algorhitme minMax pour trier la liste des coups
                    // TODO: Retourner les meilleurs coups

                    // Check the current game state && see if game is already won
                    int gameState = duplicateBoard.evaluate(cpuPlr);

                    if (gameState != 0) {
                        bestScore = gameState;
                        return bestMoves;
                    }

                    duplicateBoard.play(move,cpuPlr);
                    // Run minimax algorithm
                    if (isMaximizing) {

                        if (duplicateBoard.evaluate(cpuPlr) == 100) {
                            bestScore = 100;
                            bestMoves.add(move);
                            
                        }
                        else{
                            ArrayList<Move> moves = getNextMoveMinMax(duplicateBoard, false);
                            for (int m = 0; m < moves.size(); m++) {
                                bestMoves.add(moves.get(m));
                            }
                        }


                    }
                    else {

                        if (duplicateBoard.evaluate(plr) == -100) {
                            bestScore = 100;
                            bestMoves.add(move);
                        }
                        else{
                            ArrayList<Move> moves = getNextMoveMinMax(duplicateBoard, false);
                            for (int m = 0; m < moves.size(); m++) {
                                bestMoves.add(moves.get(m));
                            }
                        }
                    }
                }
            }
        }
        return bestMoves;
    }

//    public Move minimax(Board board, boolean maximizing) {
//        Move move = new Move();
//
//        for(int r=0; r<3; r++) {
//            for (int c = 0; c < 3; c++) {
//                if (board.isEmpty(r, c)) {
//                    Move move = new Move(r, c);
//
//                }
//            }
//        }
//        return move;
//    }

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
