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
    isTurnWhite = true;
    helper = new Helpers();

    AllFigures = helper.ReadFromFile( "testTemplate.txt" );
    board = helper.InitializeArray8x8();
    board = helper.UpgradeArray8x8( AllFigures, board );
    possibleMoves = helper.InitializeArray8x8();

  }

  //  1. Na szachownicy wybrać pionka i zaktualizowac tablice na mozliwe ruchy
  //       getPossibilities( xPola, yPola )
  public List<List<Integer>> getPossibilities ( int x_, int y_ ){
    selectedPiece = getPieceAt( x_, y_ );
    possibleMoves = selectedPiece.getPossibleMoves( this );
    piecePosition = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    System.out.println( "Pozycja wybranej figury: " + piecePosition );
    return possibleMoves;
  }
  //  2. Wybranie pola wywoluje selectDestination( xPola, yPola )
  public void selectDestination( int x_, int y_ ){
    pieceDestination = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    makeMove( piecePosition + pieceDestination );
  }
  // 3. Poźniej trzeba od nowa pobrać tablice z figurami od GM'a getBoard()

  // getters
  public List<List<Integer>> getBoard(){ return board; }
  public List<Figure> getFigures(){ return AllFigures; }
  public boolean getTurn(){ return isTurnWhite; }

  public Figure getPieceAt( int x_, int y_ ){
    selectedPieceId = board.get( y_ ).get( x_ );
    return AllFigures.get( selectedPieceId );
  }

  private void makeMove( String move ){
    int xPos = move.charAt( 0 ) - 'a';
    int yPos = 8 - move.charAt( 1 ) + '0';
    int xDest = move.charAt( 2 ) - 'a';
    int yDest = 8 - move.charAt( 3 ) + '0';

    int currentID = board.get( yPos ).get( xPos );

    // System.out.println( "\nPos: ( " + xPos + ", " + yPos + " ).");
    // System.out.println( currentID );
    // System.out.println( "Dest: ( " + xDest + ", " + yDest + " ).");

    Figure poorFigure = getPieceAt(xDest, yDest);
    poorFigure.setActivity( false );
    board.get( yPos ).set( xPos, 69);
    board.get( yDest ).set( xDest, currentID );
  }

  public void tests(){
    int x_ = 4;
    int y_ = 2;
    helper.printArray8x8(board);
    selectedPiece = getPieceAt( x_, y_ );
    System.out.println(selectedPiece.getType());
    System.out.println(selectedPiece.getTeam ());
    //piecePosition = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    System.out.println(helper.ArrayToFEN( this ));
    System.out.println( selectedPieceId );

    possibleMoves = getPossibilities(x_, y_);
    helper.printArray8x8(possibleMoves);

    makeMove( "e6f7" );

    helper.printArray8x8(board);
  }
}
