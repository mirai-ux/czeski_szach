import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
// import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.Pane;
// import javafx.stage.Stage;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import java.io.IOException;

public class BoardController {
  @FXML
  private GridPane chessBoard;

  private final String[][] initialPositions = {
      { "b_rook", "b_knight", "b_bishop", "b_queen", "b_king", "b_bishop", "b_knight", "b_rook" }, // Black pieces
      { "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn" }, // Black pawns
      { null, null, null, null, null, null, null, null }, // Empty row
      { null, null, null, null, null, null, null, null }, // Empty row
      { null, null, null, null, null, null, null, null }, // Empty row
      { null, null, null, null, null, null, null, null }, // Empty row
      { "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn" }, // White pawns
      { "w_rook", "w_knight", "w_bishop", "w_queen", "w_king", "w_bishop", "w_knight", "w_rook" } // White pieces
  };

  public void initialize() {
    int tileSize = 60; // Size of each tile
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        // Create the tile
        Pane tile = new Pane();
        tile.setPrefSize(tileSize, tileSize);
        String color = (row + col) % 2 == 0 ? "white" : "black";
        tile.setStyle("-fx-background-color: " + color + ";");

        // Add the chess piece if present
        if (initialPositions[row][col] != null) {
          ImageView piece = new ImageView();
          switch (initialPositions[row][col]) {
            case "w_king":
              Image w_king = new Image("pieces/w_king.png");
              piece.setImage(w_king);
              break;
            case "w_queen":
              Image w_queen = new Image("pieces/w_queen.png");
              piece.setImage(w_queen);
              break;
            case "w_bishop":
              Image w_bishop = new Image("pieces/w_bishop.png");
              piece.setImage(w_bishop);
              break;
            case "w_knight":
              Image w_knight = new Image("pieces/w_knight.png");
              piece.setImage(w_knight);
              break;
            case "w_rook":
              Image w_rook = new Image("pieces/w_rook.png");
              piece.setImage(w_rook);
              break;
            case "w_pawn":
              Image w_pawn = new Image("pieces/w_pawn.png");
              piece.setImage(w_pawn);
              break;
            case "b_king":
              Image b_king = new Image("pieces/b_king");
              piece.setImage(b_king);
              break;
            case "b_queen":
              Image b_queen = new Image("pieces/b_queen.png");
              piece.setImage(b_queen);
              break;
            case "b_bishop":
              Image b_bishop = new Image("pieces/b_bishop.png");
              piece.setImage(b_bishop);
              break;
            case "b_knight":
              Image b_knight = new Image("pieces/b_knight.png");
              piece.setImage(b_knight);
              break;
            case "b_rook":
              Image b_rook = new Image("pieces/b_rook.png");
              piece.setImage(b_rook);
              break;
            case "b_pawn":
              Image b_pawn = new Image("pieces/b_pawn.png");
              piece.setImage(b_pawn);
              break;
            default:
              break;
          }
          ;
          tile.getChildren().add(piece);
          // Label piece = new Label(initialPositions[row][col]);
          // piece.setFont(new Font(60)); // Adjust font size for visibility
          // piece.setStyle("-fx-text-fill: " + (color.equals("white") ? "black" :
          // "white") + ";");
          // tile.getChildren().add(piece);
          // piece.setLayoutX((tileSize - 60) / 2.0); // Center horizontally
          // piece.setLayoutY((tileSize - 60) / 4.0); // Center vertically
        }

        // Add the tile to the GridPane
        chessBoard.add(tile, col, row);
      }
    }
  }
}
