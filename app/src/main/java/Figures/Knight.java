package Figures;

import java.util.List;

public class Knight extends Figure {
    public Knight( int x_, int y_, boolean team ){
        super( x_, y_, team );
        type = 'N';
        value = 3;
    }
    public List< List< Integer >> getPossibleMoves( GameManager.GM gm ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();
        List< List< Integer >> board = gm.getBoard();

        // x++ y+
        if ( (x+2<8) && (y+1<8) ){
          if( board.get( y+1 ).get( x+2 ) == 69 )
            moves.get( y+1 ).set( x+2, 1 );
          else if ( board.get( y+1 ).get( x+2 ) != 69 && gm.getPieceAt( x+2, y+1 ).getTeam() != this.isWhite )
            moves.get( y+1 ).set( x+2, 2 );
          else
            ;
        }
        // x+ y++
        if ( (x+1<8) && (y+2<8) ){
          if( board.get( y+2 ).get( x+1 ) == 69 )
            moves.get( y+2 ).set( x+1, 1 );
          else if ( board.get( y+2 ).get( x+1 ) != 69 && gm.getPieceAt( x+1, y+2 ).getTeam() != this.isWhite )
            moves.get( y+2 ).set( x+1, 2 );
          else
            ;
        }
        
        // x++ y-
        if ( (x+2<8) && (y-1>=0) ){
          if( board.get( y-1 ).get( x+2 ) == 69 )
            moves.get( y-1 ).set( x+2, 1 );
          else if ( board.get( y-1 ).get( x+2 ) != 69 && gm.getPieceAt( x+2, y-1 ).getTeam() != this.isWhite )
            moves.get( y-1 ).set( x+2, 2 );
          else
            ;
        }
        // x+ y--
        if ( (x+1<8) && (y-2>=0) ){
          if( board.get( y-2 ).get( x+1 ) == 69 )
            moves.get( y-2 ).set( x+1, 1 );
          else if ( board.get( y-2 ).get( x+1 ) != 69 && gm.getPieceAt( x+1, y-2 ).getTeam() != this.isWhite )
            moves.get( y-2 ).set( x+1, 2 );
          else
            ;
        }
        // x-- y+
        if ( (x-2>=0) && (y+1<8) ){
          if( board.get( y+1 ).get( x-2 ) == 69 )
            moves.get( y+1 ).set( x-2, 1 );
          else if ( board.get( y+1 ).get( x-2 ) != 69 && gm.getPieceAt( x-2, y+1 ).getTeam() != this.isWhite )
            moves.get( y+1 ).set( x-2, 2 );
          else
            ;
        }
        // x- y++
        if ( (x-1>=0) && (y+2<8) ){
          if( board.get( y+2 ).get( x-1 ) == 69 )
            moves.get( y+2 ).set( x-1, 1 );
          else if ( board.get( y+2 ).get( x-1 ) != 69 && gm.getPieceAt( x-1, y+2 ).getTeam() != this.isWhite )
            moves.get( y+2 ).set( x-1, 2 );
          else
            ;
        }
        // x-- y-
        if ( (x-2>=0) && (y-1>=0) ){
          if( board.get( y-1 ).get( x-2 ) == 69 )
            moves.get( y-1 ).set( x-2, 1 );
          else if ( board.get( y-1 ).get( x-2 ) != 69 && gm.getPieceAt( x-2, y-1 ).getTeam() != this.isWhite )
            moves.get( y-1 ).set( x-2, 2 );
          else
            ;
        }
        // x- y--
        if ( (x-1>=0) && (y-2>=0) ){
          if( board.get( y-2 ).get( x-1 ) == 69 )
            moves.get( y-2 ).set( x-1, 1 );
          else if ( board.get( y-2 ).get( x-1 ) != 69 && gm.getPieceAt( x-1, y-2 ).getTeam() != this.isWhite )
            moves.get( y-2 ).set( x-1, 2 );
          else
            ;
        }
        return moves;
    }
}
