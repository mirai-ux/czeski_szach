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
  private List<Move> history;
  private Move move;

  public GM() {
    isTurnWhite = true;
    helper = new Helpers();

    // AllFigures = helper.ReadFromFile("newGameTemplate.txt");
    AllFigures = helper.ReadFromFile("testTemplate.txt");
    board = helper.InitializeArray8x8();
    board = helper.UpgradeArray8x8(AllFigures, board);
    possibleMoves = helper.InitializeArray8x8();
    history = new ArrayList<>();
    move = new Move();
  }

  // 1. Na szachownicy wybrać pionka i zaktualizowac tablice na mozliwe ruchy
  // getPossibilities( xPola, yPola )
  public List<List<Integer>> getPossibilities(int x_, int y_) {
    selectedPiece = getPieceAt(x_, y_);

    possibleMoves = selectedPiece.getPossibleMoves(this);
    piecePosition = "" + (char) (x_ + 97) + (8 - y_);
    move.setStart(piecePosition);
    // System.out.println( "Pozycja wybranej figury: " + piecePosition );

    // look for possible castlings
    String castlings = possibleCastlings();
    // castlings for white
    if (selectedPiece.getFENName() == 'K' || selectedPiece.getFENName() == 'R') {
      if (castlings.indexOf("Q") != -1) {
        possibleMoves.get(7).set(0, 4);
        possibleMoves.get(7).set(4, 4);
      }
      if (castlings.indexOf("K") != -1) {
        possibleMoves.get(7).set(7, 5);
        possibleMoves.get(7).set(4, 5);
      }
    }
    // castling for black
    if (selectedPiece.getFENName() == 'k' || selectedPiece.getFENName() == 'r') {
      if (castlings.indexOf("q") != -1) {
        possibleMoves.get(0).set(0, 6);
        possibleMoves.get(0).set(4, 6);
      }
      if (castlings.indexOf("k") != -1) {
        possibleMoves.get(0).set(7, 7);
        possibleMoves.get(0).set(4, 7);
      }
    }
    return possibleMoves;
  }

  // 2. Wybranie pola wywoluje selectDestination( xPola, yPola )
  // jeśli mozliwa jest roszada, to wykona castling( arg ),
  // gdzie arg ma 4 mozliwosci -> Q lub K lub q lub k
  public void selectDestination(int x_, int y_) {
    pieceDestination = "" + (char) (x_ + 97) + (8 - y_);
    move.setStop(pieceDestination);
    history.add(move);
    move = new Move();
    makeMove(piecePosition + pieceDestination);
  }

  public void castling(String where) {
    if (where == "Q") {
      int rookID = board.get(7).get(0);
      int kingID = board.get(7).get(4);
      board.get(7).set(1, kingID);
      board.get(7).set(2, rookID);
      move = new Move("Q", "Q");
      history.add(move);
      move = new Move();
    }
    if (where == "K") {
      int rookID = board.get(7).get(7);
      int kingID = board.get(7).get(4);
      board.get(7).set(6, kingID);
      board.get(7).set(5, rookID);
      move = new Move("K", "K");
      history.add(move);
      move = new Move();
    }
    if (where == "q") {
      int rookID = board.get(0).get(0);
      int kingID = board.get(0).get(4);
      board.get(0).set(1, kingID);
      board.get(0).set(2, rookID);
      move = new Move("q", "q");
      history.add(move);
      move = new Move();
    }
    if (where == "k") {
      int rookID = board.get(0).get(7);
      int kingID = board.get(0).get(4);
      board.get(0).set(6, kingID);
      board.get(0).set(5, rookID);
      move = new Move("k", "k");
      history.add(move);
      move = new Move();
    }
  }
  // 3. Poźniej trzeba od nowa pobrać tablice z figurami od GM'a getBoard()

  // getters
  public List<List<Integer>> getBoard() {
    return board;
  }

  public List<List<Integer>> getMoves() {
    return possibleMoves;
  }

  public List<Figure> getFigures() {
    return AllFigures;
  }

  public boolean getTurn() {
    return isTurnWhite;
  }

  public List<Move> getHistory() {
    return history;
  }

  public Figure getPieceAt(int x_, int y_) {
    if (board.get(y_).get(x_) == 69)
      return null;
    selectedPieceId = board.get(y_).get(x_);
    return AllFigures.get(selectedPieceId);
  }

  private void makeMove(String move) {
    int xPos = move.charAt(0) - 'a';
    int yPos = 8 - move.charAt(1) + '0';
    int xDest = move.charAt(2) - 'a';
    int yDest = 8 - move.charAt(3) + '0';

    int currentID = board.get(yPos).get(xPos);

    // System.out.println( "\nPos: ( " + xPos + ", " + yPos + " ).");
    // System.out.println( currentID );
    // System.out.println( "Dest: ( " + xDest + ", " + yDest + " ).");

    // Figure poorFigure = getPieceAt(xDest, yDest);
    // poorFigure.setActivity(false);
    board.get(yPos).set(xPos, 69);
    board.get(yDest).set(xDest, currentID);

    System.out.println("Move: " + move);
    // helper.printArray8x8(board);
  }

  public String possibleCastlings() {
    String result = "";
    int size = history.size();
    String starts = "";
    String fullHistory = "-";

    for (int i = 0; i < size; i++) {
      starts += history.get(i).getStart();
      fullHistory += history.get(i).getMove() + "-";
    }

    if (starts.indexOf("e1") == -1) {
      if ((starts.indexOf("a1") + fullHistory.indexOf("e1a1") + fullHistory.indexOf("a1e1")) == -3) {
        result += ((board.get(7).get(1) + board.get(7).get(2) + board.get(7).get(3)) == 207) ? "Q" : "";
      }
      if ((starts.indexOf("h1") + fullHistory.indexOf("e1h1") + fullHistory.indexOf("h1e1")) == -3) {
        result += ((board.get(7).get(6) + board.get(7).get(5) == 138)) ? "K" : "";
      }
    }
    if (starts.indexOf("e8") == -1) {
      if ((starts.indexOf("a8") + fullHistory.indexOf("e1a8") + fullHistory.indexOf("a8e1")) == -3) {
        result += ((board.get(0).get(1) + board.get(0).get(2) + board.get(0).get(3)) == 207) ? "q" : "";
      }
      if ((starts.indexOf("h8") + fullHistory.indexOf("e1h8") + fullHistory.indexOf("h8e1")) == -3) {
        result += ((board.get(0).get(6) + board.get(0).get(5) == 138)) ? "k" : "";
      }
    }
    return result;
  }

  public void tests() {
    helper.printArray8x8(board);
    System.out.println(getPieceAt(1, 1).getFENName());

    // System.out.println( possibleCastlings() );
    // int x_ = 3;
    // int y_ = 5;
    // helper.printArray8x8(board);
    //
    // System.out.println( "\n");
    // selectedPiece = getPieceAt( x_, y_ );
    // //System.out.println(selectedPiece.getType());
    // //System.out.println(selectedPiece.getTeam ());
    // //piecePosition = "" + (char)( x_ + 97 ) + ( 8 - y_ );
    // //System.out.println(helper.ArrayToFEN( this ));
    // //System.out.println( selectedPieceId );
    //
    // possibleMoves = getPossibilities(x_, y_);
    // helper.printArray8x8(possibleMoves);
    // System.out.println( "\n");
    // makeMove( "e6f7" );
    //
    // helper.printArray8x8(board);
    //
    // System.out.println( "\n\n\n" );
  }
}
