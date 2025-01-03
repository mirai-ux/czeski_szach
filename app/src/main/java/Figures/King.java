package Figures;

import java.util.List;

public class King extends Figure{
    public King( int x_, int y_, boolean team ){
        super( x_, y_, team );
        type = 'K';
        value = 200;
    }
    public List< List< Integer >> getPossibleMoves( GameManager.GM gm ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();
        List< List< Integer >> board = gm.getBoard();

        if( this.isWhite ){
            ;
        } else {
            ;
        }


        return moves;
    }
}
