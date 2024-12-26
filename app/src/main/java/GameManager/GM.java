package GameManager;

import Figures.*;
import java.util.*;

public class GM {
  private int selectedPieceId;
  boolean isTurnWhite;
  List<List<Integer>> board;
  List<Figure> AllFigures;

  public GM() {
    // System.out.println("GM");
    Helpers helper = new Helpers();
    helper.testowa();
    selectedPieceId = -1;
    isTurnWhite = false;
    // Add option to choose save for load
    AllFigures = helper.ReadFromFile("newGameTemplate.txt");
    board = helper.InitializeArray8x8();
    board = helper.UpgradeArray8x8(AllFigures, board);
  }
}
