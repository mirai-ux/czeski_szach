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

  public GM() {
    // System.out.println("GM");
    Helpers helper = new Helpers();
    helper.testowa();
    selectedPieceId = -1;
    isTurnWhite = false;
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

  }
  public List<List<Integer>> getBoard(){ return board; }
  public List<Figure> getFigures(){ return AllFigures; }
  public boolean getTurn(){ return isTurnWhite; }

  public Figure getPieceAt( int x_, int y_ ){
    int selectedPieceId = board.get( y_ ).get( x_ );
    return AllFigures.get( selectedPieceId );
  }
}
