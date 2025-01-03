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

    //System.out.println("\n"+getPieceAt(1, 1).getType());
    //System.out.println(getPieceAt(1, 1).getTeam ());
    selectedPiece = getPieceAt(1, 1);
    possibleMoves = selectedPiece.getPossibleMoves(this);
    helper.printArray8x8(possibleMoves);

  }
  public List<List<Integer>> getBoard(){ return board; }

  public Figure getPieceAt( int x_, int y_ ){
    int selectedPieceId = board.get( x_ ).get( y_ );
    return AllFigures.get( selectedPieceId );
  }
}
