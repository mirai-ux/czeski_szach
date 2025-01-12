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

        int[][] possibilities = {{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1}};

        for (int i = 0; i < 8; i++) {
          int newX = x + possibilities[i][0];
          int newY = y + possibilities[i][1];
          if( (newX<8 && newX>=0) && (newY<8 && newY>=0)){
            if( board.get( newY ).get( newX ) == 69 )
              moves.get( newY ).set( newX, 1 );
            else if ( board.get( newY ).get( newX ) != 69 && gm.getPieceAt( newX, newY ).getTeam() != this.isWhite )
              moves.get( newY ).set( newX, 2 );
            else
              ;
          }
          
        }

        moves.get( y ).set( x, 3 );
        
        return moves;
    }
}
