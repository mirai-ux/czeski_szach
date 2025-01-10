package GameManager;

import Figures.*;
import java.util.*;

public class GM {
  private int selectedPieceId;
  private boolean isTurnWhite;
  private List<List<Integer>> board;
  private List<List<Integer>> possibleMoves;
  private List<Figure> AllFigures;
  private Figure selectedPiece;
  private Helpers helper;
  private String piecePosition;
  private String pieceDestination;

  public GM() {
    /*
    // System.out.println("GM");
    helper = new Helpers();
    // selectedPieceId = -1;
    isTurnWhite = true;
    asddddddd
    // Add option to choose save for load
    AllFigures = helper.ReadFromFile("testTemplate.txt");
    board = helper.InitializeArray8x8();
    board = helper.UpgradeArray8x8(AllFigures, board);
    helper.printArray8x8(board);
    System.out.println("\n");

    
    selectedPiece = getPieceAt(4,2);
    System.out.println(selectedPiece.getType());
    System.out.println(selectedPiece.getTeam ());

    System.out.println(helper.ArrayToFEN( this ));

    possibleMoves = selectedPiece.getPossibleMoves(this);
    helper.printArray8x8(possibleMoves);
     */
    isTurnWhite = true;
    helper = new Helpers();

    AllFigures = helper.ReadFromFile( "testTemplate.txt" );
    board = helper.InitializeArray8x8();
    board = helper.UpgradeArray8x8( AllFigures, board );
    possibleMoves = helper.InitializeArray8x8();

  }
  // getters
  public List<List<Integer>> getBoard(){ return board; }
  public List<Figure> getFigures(){ return AllFigures; }
  public boolean getTurn(){ return isTurnWhite; }

  public Figure getPieceAt( int x_, int y_ ){
    int selectedPieceId = board.get( y_ ).get( x_ );
    return AllFigures.get( selectedPieceId );
  }
  public List<List<Integer>> getPossibilities ( int x_, int y_ ){
    selectedPiece = getPieceAt( x_, y_ );
    selectedPieceId = board.get( y_ ).get( x_ );
    possibleMoves = selectedPiece.getPossibleMoves( this );
    piecePosition = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    return possibleMoves;
  }
  public void selectDestination( int x_, int y_ ){
    pieceDestination = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    makeMove( piecePosition + pieceDestination );
  }

  private void makeMove( String move ){
    int xPos = (int) move.charAt( 0 ) - 97;
    int yPos = (int) move.charAt( 1 ) - 52;
    int xDest = (int) move.charAt( 2 ) - 97;
    int yDest = (int) move.charAt( 3 ) - 52;
    System.out.println( "\n " + xPos + yPos  );
    if( board.get(yDest).get(xDest) == 69 ){
      selectedPiece.setPosition( xDest, yDest );
      board.get( yPos ).set( xPos, 69 );
      board.get( yDest ).set( xDest, selectedPieceId );
    } else {
      Figure poorFigure = getPieceAt( xDest, yDest );
      poorFigure.setActivity( false );
      selectedPiece.setPosition( xDest, yDest );
      board.get( yPos ).set( xPos, 69 );
      board.get( yDest ).set( xDest, selectedPieceId );
    }
  }

  public void tests(){
    int x_ = 4;
    int y_ = 2;
    helper.printArray8x8(board);
    selectedPiece = getPieceAt( x_, y_ );
    System.out.println(selectedPiece.getType());
    System.out.println(selectedPiece.getTeam ());
    piecePosition = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    System.out.println(helper.ArrayToFEN( this ));
    System.out.println( piecePosition );

    possibleMoves = selectedPiece.getPossibleMoves(this);
    helper.printArray8x8(possibleMoves);

    makeMove( "e6e5" );
  }
}
