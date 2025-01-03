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
        List< List< Integer >> board = gm.getBoard();

        if( this.isWhite ){
            ;
        } else {
            ;
        }


        return moves;
    }
}
