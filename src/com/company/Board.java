package com.company;
import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)

public class Board
{
    private Mark[][] board;

    // Vérifier si la case est vide
    public boolean isEmpty(int row, int col) {
        if(board[row][col] == Mark.EMPTY){
            return true;
        }
        else{
            return false;
        }
    }
    private static final int SIZE = 3;

    // Ne pas changer la signature de cette méthode
    public Board(Mark[][] board) {
        this.board = board;
    }

    public Board(Board board) {
        this.board = board.board;
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        this.board[m.getRow()][m.getCol()] = mark;
    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) return 100;
            else if (board[i][0] != Mark.EMPTY && board[i][0] != mark && board[i][1] == board[i][0] && board[i][2] == board[i][0]) {
                return -100;
            }

            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) return 100;
            else if (board[0][i] != Mark.EMPTY && board[0][i] != mark && board[1][i] == board[0][i] && board[2][i] == board[0][i])
                return -100;
        }
//        // diagonales
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) return 100;
        else if (board[0][0] != Mark.EMPTY && board[0][0] != mark && board[1][1] == board[0][0] && board[2][2] == board[0][0])
            return -100;
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) return 100;
        else if (board[0][2] != Mark.EMPTY && board[0][2] != mark && board[1][1] == board[0][2] && board[2][0] == board[0][2])
            return -100;
//        return 0;

        return 0; // No winner yet
    }

}
