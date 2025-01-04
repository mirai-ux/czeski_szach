package Figures;

import java.util.List;

public class Bishop extends Figure {
  public Bishop(int x_, int y_, boolean team) {
    super(x_, y_, team);
    type = 'B';
    value = 3;
  }
  public List< List< Integer >> getPossibleMoves( GameManager.GM gm ){
    List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();
    List< List< Integer >> board = gm.getBoard();

    // x+ y+
    for (int i = 1; ( x+i<8 && y+i<8 ); i++) {
      if( board.get( y+i ).get( x+i ) == 69 )
            moves.get( y+i ).set( x+i, 1 );
          else if ( board.get( y+i ).get( x+i ) != 69 && gm.getPieceAt( x+i, y+i ).getTeam() != this.isWhite ){
            moves.get( y+i ).set( x+i, 2 );
            break;
          }
          else
            ;
    }
    // x+ y-
    for (int i = 1; ( x+i<8 && y-i>=0 ); i++) {
      if( board.get( y-i ).get( x+i ) == 69 )
            moves.get( y-i ).set( x+i, 1 );
          else if ( board.get( y-i ).get( x+i ) != 69 && gm.getPieceAt( x+i, y-i ).getTeam() != this.isWhite ){
            moves.get( y-i ).set( x+i, 2 );
            break;
          }
          else
            ;
    }
    // x- y+
    for (int i = 1; ( x-i>=0 && y+i<8 ); i++) {
      if( board.get( y+i ).get( x-i ) == 69 )
            moves.get( y+i ).set( x-i, 1 );
          else if ( board.get( y+i ).get( x-i ) != 69 && gm.getPieceAt( x-i, y+i ).getTeam() != this.isWhite ){
            moves.get( y+i ).set( x-i, 2 );
            break;
          }
          else
            ;
    }
    // x- y-
    for (int i = 1; ( x-i>=0 && y-i>=0 ); i++) {
      if( board.get( y-i ).get( x-i ) == 69 )
            moves.get( y-i ).set( x-i, 1 );
          else if ( board.get( y-i ).get( x-i ) != 69 && gm.getPieceAt( x-i, y-i ).getTeam() != this.isWhite ){
            moves.get( y-i ).set( x-i, 2 );
            break;
          }
          else
            ;
    }




    return moves;
  }
}
