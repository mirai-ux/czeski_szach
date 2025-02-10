package Figures;

import java.util.List;

public class Rook extends Figure{

    public Rook( int x_, int y_, boolean team, GameManager.GM gm_) {
      super(x_, y_, team, gm_);
        type = 'R';
        value = 5;
    }
    public List< List< Integer >> getPossibleMoves( ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();
        List< List< Integer >> board = gm.getBoard();

        // x+
        for (int i = x+1; i < 8; i++) {
            if( board.get( y ).get( i ) == 69 )
            moves.get( y ).set( i, 1 );
          else if ( board.get( y ).get( i ) != 69 && gm.getPieceAt( i, y ).getTeam() != this.isWhite ){
            moves.get( y ).set( i, 2 );
            break;
          }
          else
            break;
        }
        // x-
        for (int i = x-1; i >= 0; i--) {
            if( board.get( y ).get( i ) == 69 )
            moves.get( y ).set( i, 1 );
          else if ( board.get( y ).get( i ) != 69 && gm.getPieceAt( i, y ).getTeam() != this.isWhite ){
            moves.get( y ).set( i, 2 );
            break;
          }
          else
            break;
        }
        // y+
        for (int i = y+1; i < 8; i++) {
            if( board.get( i ).get( x ) == 69 )
            moves.get( i ).set( x, 1 );
          else if ( board.get( i ).get( x ) != 69 && gm.getPieceAt( x, i ).getTeam() != this.isWhite ){
            moves.get( i ).set( x, 2 );
            break;
          }
          else
            break;
        }
        // y-
        for (int i = y-1; i >= 0; i--) {
            if( board.get( i ).get( x ) == 69 )
            moves.get( i ).set( x, 1 );
          else if ( board.get( i ).get( x ) != 69 && gm.getPieceAt( x, i ).getTeam() != this.isWhite ){
            moves.get( i ).set( x, 2 );
            break;
          }
          else
            break;
        }

        moves.get( y ).set( x, 3 );

        return moves;
    }

    public boolean castlingPossible(){ return ( this.isActive && !this.haveIMoved ); }
}
