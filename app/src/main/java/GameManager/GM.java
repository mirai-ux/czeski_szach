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
  private boolean isGameFinished = false;

  public GM() {
    isTurnWhite = true;
    helper = new Helpers();
    helper.setGM(this);

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

    possibleMoves = selectedPiece.getPossibleMoves();
    piecePosition = "" + (char) (x_ + 97) + (8 - y_);
    move.setStart(piecePosition);
    // System.out.println( "Pozycja wybranej figury: " + piecePosition );

    // look for possible castlings
    String castlings = possibleCastlings(selectedPiece);
    // castlings for white
    if (selectedPiece.getFENName() == 'K' || selectedPiece.getFENName() == 'R') {
      if (castlings.indexOf("Q") != -1) {
        possibleMoves.get(7).set(0, 4);
        possibleMoves.get(7).set(4, 5);
      }
      if (castlings.indexOf("K") != -1) {
        possibleMoves.get(7).set(7, 6);
        possibleMoves.get(7).set(4, 5);
      }
    }
    // castling for black
    if (selectedPiece.getFENName() == 'k' || selectedPiece.getFENName() == 'r') {
      if (castlings.indexOf("q") != -1) {
        possibleMoves.get(0).set(0, 7);
        possibleMoves.get(0).set(4, 8);
      }
      if (castlings.indexOf("k") != -1) {
        possibleMoves.get(0).set(7, 9);
        possibleMoves.get(0).set(4, 8);
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

  // private String position(int x, int y) {
  // return ("" + (char) (x + 97) + (8 - y));
  // }

  public void castling(String where) {
    // if (where == "Q") {
    if (where.equals("Q")) {
      int rookID = board.get(7).get(0) % 100;
      int kingID = board.get(7).get(4) % 100;
      board.get(7).set(0, 69);
      board.get(7).set(4, 69);
      board.get(7).set(2, kingID);
      board.get(7).set(3, rookID);
      AllFigures.get(kingID).setPosition(2, 7);
      AllFigures.get(rookID).setPosition(3, 7);

      // // king's move
      // makeMove(position(4, 7) + position(2, 7));
      // // rook's move
      // makeMove(position(0, 7) + position(3, 7));

      move = new Move("Q", "Q");
      history.add(move);
      move = new Move();
    }

    if (where.equals("K")) {
      int rookID = board.get(7).get(7) % 100;
      int kingID = board.get(7).get(4) % 100;
      board.get(7).set(7, 69);
      board.get(7).set(4, 69);
      board.get(7).set(6, kingID);
      board.get(7).set(5, rookID);
      AllFigures.get(kingID).setPosition(6, 7);
      AllFigures.get(rookID).setPosition(5, 7);
      move = new Move("K", "K");
      history.add(move);
      move = new Move();
    }
    if (where.equals("q")) {
      int rookID = board.get(0).get(0) % 100;
      int kingID = board.get(0).get(4) % 100;
      board.get(0).set(0, 69);
      board.get(0).set(4, 69);
      board.get(0).set(2, kingID);
      board.get(0).set(3, rookID);
      AllFigures.get(kingID).setPosition(2, 0);
      AllFigures.get(rookID).setPosition(3, 0);
      move = new Move("q", "q");
      history.add(move);
      move = new Move();
    }
    if (where.equals("k")) {
      int rookID = board.get(0).get(7) % 100;
      int kingID = board.get(0).get(4) % 100;
      board.get(0).set(7, 69);
      board.get(0).set(4, 69);
      board.get(0).set(6, kingID);
      board.get(0).set(5, rookID);
      AllFigures.get(kingID).setPosition(6, 0);
      AllFigures.get(rookID).setPosition(5, 0);
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

  public List<String> getNiceHistoryForMrWithRustyHair() {
    List<String> hist = new ArrayList<String>();

    for (Move m : history)
      hist.add(m.toString());

    return hist;
  }

  public String getNiceLastMove() {
    return history.getLast().toString();
  }

  public List<List<Integer>> getMoves() {
    return possibleMoves;
  }

  public List<Figure> getFigures() {
    return AllFigures;
  }

  public void setTurn(boolean isTurnWhiteIndex) {
    isTurnWhite = isTurnWhiteIndex;
  }

  public boolean getTurn() {
    return isTurnWhite;
  }

  public List<Move> getHistory() {
    return history;
  }

  public boolean EndGameCheck() {
    return isGameFinished;
  }

  public Figure getPieceAt(int x_, int y_) {
    if (board.get(y_).get(x_) == 69)
      return null;
    selectedPieceId = board.get(y_).get(x_) % 100;
    return AllFigures.get(selectedPieceId);
  }

  private void makeMove(String move) {
    int xPos = move.charAt(0) - 'a';
    int yPos = 8 - move.charAt(1) + '0';
    int xDest = move.charAt(2) - 'a';
    int yDest = 8 - move.charAt(3) + '0';

    int currentID = board.get(yPos).get(xPos) % 100;

    // System.out.println( "\nPos: ( " + xPos + ", " + yPos + " ).");
    // System.out.println( currentID );
    // System.out.println( "Dest: ( " + xDest + ", " + yDest + " ).");

    Figure poorFigure = getPieceAt(xDest, yDest);
    if (poorFigure != null) {
      poorFigure.setActivity(false);
      if (poorFigure.getType() == 'K')
        isGameFinished = true;
    }

    board.get(yPos).set(xPos, 69);
    board.get(yDest).set(xDest, currentID);

    // System.out.println("Move: " + move);

    selectedPiece.setPosition(xDest, yDest);
    // selectedPiece.moved();

    // they always are at 0 and 1 index.
    // See NOTE in /Saves/
    King WhiteKing = (King) AllFigures.get(0);
    King BlackKing = (King) AllFigures.get(1);
    WhiteKing.inDanger();
    BlackKing.inDanger();

    // helper.printArray8x8(board);
  }

  public String checkCastling_Q() {
    King K = getPieceAt(4, 7) instanceof King ? (King) getPieceAt(4, 7) : null;
    if (K != null) {
      Rook RQ = getPieceAt(0, 7) instanceof Rook ? (Rook) getPieceAt(0, 7) : null;
      if (RQ != null) {
        boolean clean_Q = ((board.get(7).get(1) + board.get(7).get(2) + board.get(7).get(3)) == 207);
        if (clean_Q && RQ.castlingPossible() && K.castlingPossible())
          return "Q";
      }
    }
    return "";
  }

  public String checkCastling_K() {
    King K = getPieceAt(4, 7) instanceof King ? (King) getPieceAt(4, 7) : null;
    if (K != null) {
      Rook RK = getPieceAt(7, 7) instanceof Rook ? (Rook) getPieceAt(7, 7) : null;
      if (RK != null) {
        boolean clean_K = ((board.get(7).get(6) + board.get(7).get(5)) == 138);
        if (clean_K && RK.castlingPossible() && K.castlingPossible())
          return "K";
      }
    }
    return "";
  }

  public String checkCastling_q() {
    King k = getPieceAt(4, 0) instanceof King ? (King) getPieceAt(4, 0) : null;
    if (k != null) {
      Rook rq = getPieceAt(0, 0) instanceof Rook ? (Rook) getPieceAt(0, 0) : null;
      if (rq != null) {
        boolean clean_q = ((board.get(0).get(1) + board.get(0).get(2) + board.get(0).get(3)) == 207);
        if (clean_q && rq.castlingPossible() && k.castlingPossible())
          return "q";
      }
    }
    return "";
  }

  public String checkCastling_k() {
    King k = getPieceAt(4, 0) instanceof King ? (King) getPieceAt(4, 0) : null;
    if (k != null) {
      Rook rk = getPieceAt(7, 0) instanceof Rook ? (Rook) getPieceAt(7, 0) : null;
      if (rk != null) {
        boolean clean_k = ((board.get(0).get(6) + board.get(0).get(5)) == 138);
        if (clean_k && rk.castlingPossible() && k.castlingPossible())
          return "k";
      }
    }
    return "";
  }

  public String possibleCastlings(Figure f) {
    String result = "";
    if (!(f instanceof Rook || f instanceof King))
      return result;

    if (f.getTeam()) {
      if (f instanceof King)
        result += (checkCastling_Q() + checkCastling_K());
      if (f instanceof Rook && f.getX() == 0)
        result += checkCastling_Q();
      if (f instanceof Rook && f.getX() == 7)
        result += checkCastling_K();
    } else {
      if (f instanceof King)
        result += (checkCastling_q() + checkCastling_k());
      if (f instanceof Rook && f.getX() == 0)
        result += checkCastling_q();
      if (f instanceof Rook && f.getX() == 7)
        result += checkCastling_k();
    }

    // King K = getPieceAt(4, 7) instanceof King ? (King) getPieceAt(4, 7) : null;
    // if( K != null ){
    // Rook RQ = getPieceAt(0, 7) instanceof Rook ? (Rook) getPieceAt(0, 7) : null;
    // if( RQ != null ){
    // boolean clean_Q = ((board.get(7).get(1) + board.get(7).get(2) +
    // board.get(7).get(3)) == 207);
    // result += ( clean_Q && RQ.castlingPossible() && K.castlingPossible() ) ? "Q"
    // : "";
    // }
    // Rook RK = getPieceAt(7, 7) instanceof Rook ? (Rook) getPieceAt(7, 7) : null;
    // if( RK != null ){
    // boolean clean_K = ((board.get(7).get(6) + board.get(7).get(5)) == 138);
    // result += ( clean_K && RK.castlingPossible() && K.castlingPossible() ) ? "K"
    // : "";
    // }
    // }
    // King k = getPieceAt(4, 0) instanceof King ? (King) getPieceAt(4, 0) : null;
    // if( k != null ){
    // Rook rq = getPieceAt(0, 0) instanceof Rook ? (Rook) getPieceAt(0, 0) : null;
    // if( rq != null ){
    // boolean clean_q = ((board.get(0).get(1) + board.get(0).get(2) +
    // board.get(0).get(3)) == 207);
    // result += ( clean_q && rq.castlingPossible() && k.castlingPossible() ) ? "q"
    // : "";
    // }
    // Rook rk = getPieceAt(7, 0) instanceof Rook ? (Rook) getPieceAt(7, 0) : null;
    // if( rk != null ){
    // boolean clean_k = ((board.get(0).get(6) + board.get(0).get(5)) == 138);
    // result += ( clean_k && rk.castlingPossible() && k.castlingPossible() ) ? "k"
    // : "";
    // }
    // }

    // int size = history.size();
    // String starts = "";
    // String fullHistory = "-";

    // for (int i = 0; i < size; i++) {
    // starts += history.get(i).getStart();
    // fullHistory += history.get(i).getMove() + "-";
    // }

    // if (starts.indexOf("e1") == -1) {
    // if ((starts.indexOf("a1") + fullHistory.indexOf("e1a1") +
    // fullHistory.indexOf("a1e1")) == -3) {
    // result += ((board.get(7).get(1) + board.get(7).get(2) + board.get(7).get(3))
    // == 207) ? "Q" : "";
    // }
    // if ((starts.indexOf("h1") + fullHistory.indexOf("e1h1") +
    // fullHistory.indexOf("h1e1")) == -3) {
    // result += ((board.get(7).get(6) + board.get(7).get(5) == 138)) ? "K" : "";
    // }
    // }
    // if (starts.indexOf("e8") == -1) {
    // if ((starts.indexOf("a8") + fullHistory.indexOf("e1a8") +
    // fullHistory.indexOf("a8e1")) == -3) {
    // result += ((board.get(0).get(1) + board.get(0).get(2) + board.get(0).get(3))
    // == 207) ? "q" : "";
    // }
    // if ((starts.indexOf("h8") + fullHistory.indexOf("e1h8") +
    // fullHistory.indexOf("h8e1")) == -3) {
    // result += ((board.get(0).get(6) + board.get(0).get(5) == 138)) ? "k" : "";
    // }
    // }
    return result;
  }

  public void updateBoard(int x_, int y_, int nV) {
    board.get(y_).set(x_, nV);
  }

  public void promotePawn(Pawn pawn) {
    // make new queen
    int x_ = pawn.getX();
    int y_ = pawn.getY();
    boolean team_ = pawn.getTeam();
    Queen newQueen = new Queen(x_, y_, team_, this);
    AllFigures.add(newQueen);
    // remove old pawn
    pawn.setActivity(false);
    // replace old pawn
    updateBoard(x_, y_, AllFigures.size() - 1);
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
