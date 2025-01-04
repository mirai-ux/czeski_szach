package Figures;

import java.util.List;

public class Queen extends Figure{
    public Queen( int x_, int y_, boolean team ){
        super( x_, y_, team );
        type = 'Q';
        value = 9;
    }
    public List< List< Integer >> getPossibleMoves( GameManager.GM gm ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();

        Rook r = new Rook(this.x, this.y, this.isWhite);
        List< List< Integer >> movesRook = GameManager.Helpers.InitializeArray8x8();
        Bishop b = new Bishop(this.x, this.y, this.isWhite);
        List< List< Integer >> movesBishop = GameManager.Helpers.InitializeArray8x8();

        movesRook = r.getPossibleMoves(gm);
        movesBishop = b.getPossibleMoves(gm);

        moves = GameManager.Helpers.mergeTwoArrays8x8(movesRook, movesBishop);

        return moves;
    }
}
