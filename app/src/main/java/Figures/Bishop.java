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

        if( this.isWhite ){
            ;
        } else {
            ;
        }


        return moves;
    }
}
