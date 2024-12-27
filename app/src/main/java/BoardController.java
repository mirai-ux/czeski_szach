import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class BoardController {
  @FXML
  private GridPane chessBoard;

  public void initialize() {
    int tileSize = 60; // Size of each tile
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Pane tile = new Pane();
        tile.setPrefSize(tileSize, tileSize);
        String color = (row + col) % 2 == 0 ? "white" : "black";
        tile.setStyle("-fx-background-color: " + color + ";");
        chessBoard.add(tile, col, row);
      }
    }
  }
}
