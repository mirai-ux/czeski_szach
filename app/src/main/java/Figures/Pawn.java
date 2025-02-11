package Figures;

import java.util.List;

public class Pawn extends Figure {
    public Pawn( int x_, int y_, boolean team, GameManager.GM gm_) {
        super(x_, y_, team, gm_);
        type = 'P';
        value = 1;
    }

    // 69 if can't move there
    // 1  if can move there
    // 2  if can attack there
    public List< List< Integer >> getPossibleMoves( ){
        List< List< Integer >> moves = GameManager.Helpers.InitializeArray8x8();

        if( gm.EndGameCheck() ) return moves;
    
        
        List< List< Integer >> board = gm.getBoard();

        int direction = this.isWhite ? -1 : 1;

        // GameManager.Helpers.printArray8x8(board);
        // System.out.println( "\nx:" + this.x + " y:" + this.y + "\n");
        // System.out.println( "\n Przod: " + board.get( y-1 ).get( x ));

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
        // System.out.println( "Przed\n");
        // GameManager.Helpers.printArray8x8(moves);
        if( x == 0 )
            if( board.get( y+direction ).get( 0 ) != 69 )
                if( gm.getPieceAt( 1 , y+direction ).getTeam() != this.isWhite )
                    moves.get( y+direction ).set( 1, 2 );
        // System.out.println( "Po 0\n");
        // GameManager.Helpers.printArray8x8(moves);
        if( x == 7 )
            if( board.get( y+direction ).get( 7 ) != 69 )
                if( gm.getPieceAt( 6 , y+direction ).getTeam() != this.isWhite )
                    moves.get( y+direction ).set( 6, 2 );
        // System.out.println( "Po 7\n");
        // GameManager.Helpers.printArray8x8(moves);
        if( x > 0 && x < 7){
            if( board.get( y+direction ).get( x+1 ) != 69 )
                if( gm.getPieceAt( x+1, y+direction ).getTeam() != this.isWhite )
                    moves.get( y+direction ).set( x+1, 2 );
            if( board.get( y+direction ).get( x-1 ) != 69 )
                if( gm.getPieceAt( x-1, y+direction ).getTeam() != this.isWhite )
                    moves.get( y+direction ).set( x-1, 2 );
        }
        
        moves.get( y ).set( x, 3 );

        // GameManager.Helpers.printArray8x8(moves);

        return moves;
    }
    @ Override
    public void setPosition( int x_, int y_ ){
        x = x_;
        y = y_;
        int border = this.isWhite ? 0 : 7;
        if( y == border )
            gm.promotePawn( this );
      }
}
