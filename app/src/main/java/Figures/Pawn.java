package Figures;
import GameManager.*;

import java.util.List;

public class Pawn extends Figure {
    public Pawn( int x_, int y_, boolean team ){
        super( x_, y_, team );
        type = 'P';
        value = 1;
    }

    // 69 if can't move there
    // 1  if can move there
    // 2  if can attack there
    public List< List< Integer >> getPossibleMoves( GameManager.GM gm ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();
        List< List< Integer >> board = gm.getBoard();

        int direction = -1;
        if( !isWhite ) direction = 1;

        // just moves
        if( isWhite ){
            if( board.get( y-1 ).get( x ) == 69 ){
                moves.get( y-1 ).set( x, 1 );
                if( y==6 && board.get( y-2 ).get( x ) == 69 )
                    moves.get( y-2 ).set( x, 1 );
            }
        } else {
            if( board.get( y+1 ).get( x ) == 69 ){
                moves.get( y+1 ).set( x, 1 );
                if( y==1 && board.get( y+2 ).get( x ) == 69 )
                    moves.get( y+2 ).set( x, 1 );
            }
        }
        // possible attacks
        if( x == 0 )
            if( board.get( 0 ).get( y+direction ) != 69 )
                if( gm.getPieceAt( 1 , y+direction ).getTeam() != this.isWhite )
                    moves.get( 0 ).set( y+direction, 2 );
        if( x == 7 )
            if( board.get( 7 ).get( y+direction ) != 69 )
                if( gm.getPieceAt( 6 , y+direction ).getTeam() != this.isWhite )
                    moves.get( 7 ).set( y+direction, 2 );
        if( x > 0 && x < 7){
            if( board.get( x+1 ).get( y+direction ) != 69 )
                if( gm.getPieceAt( x+1, y+direction ).getTeam() != this.isWhite )
                    moves.get( x+1 ).set( y+direction, 2 );
            if( board.get( x+1 ).get( y+direction ) != 69 )
                if( gm.getPieceAt( x-1, y+direction ).getTeam() != this.isWhite )
                    moves.get( x-1 ).set( y+direction, 2 );
        }

        return moves;
    }
}
