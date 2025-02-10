package Figures;

import java.util.List;

public class King extends Figure{
    public King( int x_, int y_, boolean team, GameManager.GM gm_) {
      super(x_, y_, team, gm_);
        type = 'K';
        value = 200;
    }
    public List< List< Integer >> getPossibleMoves( ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();
        List< List< Integer >> board = gm.getBoard();

    moves.get(y).set(x, 3);

    return moves;
  }

        moves.get( y ).set( x, 3 );
        
        return moves;
    }
    // adds 100 or 200 to value on gm.board where is the piece that is a danger to king
    public void inDanger( ){
      List< Figure > figures = gm.getFigures();
      int marker = this.isWhite ? 100 : 200;
      for( Figure f : figures){
        // if its the opponent and still active
        if( f.getTeam() != this.isWhite && f.getActivity() ){
          // if it can kill the king
          if( f.getPossibleMoves( ).get( this.y ).get( this.x ) == 2){
            int fx = f.getX();
            int fy = f.getY();
            int newVal = gm.getBoard().get( fy ).get( fx ) + marker;
            gm.updateBoard( fx, fy, newVal );
          }
        }
      }
    }
  }

  public boolean castlingPossible() {
    return (this.isActive && !this.haveIMoved);
  }
}
