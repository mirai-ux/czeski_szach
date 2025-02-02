import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import java.util.*;

public class HistoryController extends TurnAbstract {
  // private GameManager.GM gm = new GameManager.GM();
  private List<HBox> moveHistory = new ArrayList<>();
  // full turn is counts whenever both sides make a move
  // not very useful for a computer, but easier to read as a human
  private int fullTurn;

  @FXML
  private AnchorPane historyPane;

  public void initialize() {
    System.out.println("yes");
  }

  private void fullTurnCounter() {
    if (turnCount % 2 == 0) {
      fullTurn++;
    }
  }

  public void displayMoveHistory(String move) {
    fullTurnCounter();
    HBox moveHBox = new HBox();
    // Label turnCountLabel = new Label(fullTurn + ".");
    // Label whiteMoveLabel = new Label(move);
    // Label blackMoveLabel = new Label(move);
    moveHBox.setPrefSize(60, 60);
    moveHBox.setStyle("-fx-background-color: rgba(235, 236, 208, 1);");
    moveHistory.add(moveHBox);

    // moveHBox.getChildren().add(turnCountLabel);
    // moveHBox.getChildren().add(whiteMoveLabel);
    // moveHBox.getChildren().add(blackMoveLabel);
    // historyScrollPane.setContent(moveHBox);
  }
}
