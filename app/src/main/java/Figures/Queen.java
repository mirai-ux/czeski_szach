package Figures;

import java.util.List;

public class Queen extends Figure{
    public Queen( int x_, int y_, boolean team, GameManager.GM gm_) {
        super(x_, y_, team, gm_);
        type = 'Q';
        value = 9;
    }
    public List< List< Integer >> getPossibleMoves( ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();

        Rook r = new Rook(this.x, this.y, this.isWhite, gm);
        List< List< Integer >> movesRook = GameManager.Helpers.InitializeArray8x8();
        Bishop b = new Bishop(this.x, this.y, this.isWhite, gm);
        List< List< Integer >> movesBishop = GameManager.Helpers.InitializeArray8x8();

        movesRook = r.getPossibleMoves();
        movesBishop = b.getPossibleMoves();

        moves = GameManager.Helpers.mergeTwoArrays8x8(movesRook, movesBishop);

        moves.get( y ).set( x, 3 );
        
        return moves;
    }
}
