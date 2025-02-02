import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import java.util.*;

public class HistoryController extends TurnAbstract {
  // private GameManager.GM gm = new GameManager.GM();
  private List<Label> moveHistory = new ArrayList<>();
  // full turn is counts whenever both sides make a move
  // is also the index for the currently operated row in gridpane
  private int fullTurn;
  private int columnIndex;

  @FXML
  private GridPane historyGridPane;

  @FXML
  private ScrollPane historyScrollPane;

  private String fontColor;

  public void initialize() {
    System.out.println("history is being made");
    historyScrollPane.setContent(historyGridPane);

    fullTurn = 0;
    columnIndex = 1;
  }

  // private void fullTurnCounter() {
  // if (turnCount % 2 == 0) {
  // fullTurn++;
  // }
  // }

  public void displayMoveHistory(String move) {
    Label turnCountLabel = new Label((fullTurn) + ".");
    Label whiteMoveLabel = new Label(move);
    Label blackMoveLabel = new Label(move);
    turnCountLabel.col
    // modulo to receive the specific types, not sure if necessary to store them
    // moveHistory.add(turnCountLabel);
    // moveHistory.add(whiteMoveLabel);
    // moveHistory.add(blackMoveLabel);

    System.out.println("col ind: " + columnIndex);
    System.out.println("full turn: " + fullTurn);
    System.out.println("turn: " + turnCount);

    switch (columnIndex) {
      case 1:
        historyGridPane.add(turnCountLabel, 0, fullTurn);
        historyGridPane.add(whiteMoveLabel, 1, fullTurn);
        columnIndex = 2;
        break;
      case 2:
        historyGridPane.add(blackMoveLabel, 2, fullTurn);
        fullTurn++;
        columnIndex = 1;
        break;
      default:
        System.err.println("oh");
        break;
    }
  }
}
