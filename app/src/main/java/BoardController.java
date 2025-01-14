import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.text.Font;
// import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.Pane;
// import javafx.stage.Stage;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import java.io.IOException;

import java.util.*;

import GameManager.*;
import Figures.*;

public class BoardController {
  private GM gm;
  private List<List<Integer>> boardForController;
  private List<List<Integer>> possibleMovesForController;
  private List<Figure> AllFiguresForController;

  List<List<Pane>> tileList = new ArrayList<>();
  int tileSize = 60;

  @FXML
  private GridPane chessBoard;

  // private final String[][] initialPositions = {
  // { "b_rook", "b_knight", "b_bishop", "b_queen", "b_king", "b_bishop",
  // "b_knight", "b_rook" }, // Black pieces
  // { "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn", "b_pawn",
  // "b_pawn" }, // Black pawns
  // { null, null, null, null, null, null, null, null }, // Empty row
  // { null, null, null, null, null, null, null, null }, // Empty row
  // { null, null, null, null, null, null, null, null }, // Empty row
  // { null, null, null, null, null, null, null, null }, // Empty row
  // { "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn", "w_pawn",
  // "w_pawn" }, // White pawns
  // { "w_rook", "w_knight", "w_bishop", "w_queen", "w_king", "w_bishop",
  // "w_knight", "w_rook" } // White pieces
  // };

  public void initialize() {
    gm = new GM();
    boardForController = gm.getBoard();
    possibleMovesForController = gm.getMoves();
    AllFiguresForController = gm.getFigures();

    for (int row = 0; row < 8; row++) {
      List<Pane> tileListRow = new ArrayList<>();
      for (int col = 0; col < 8; col++) {
        Pane tile = new Pane();
        tileListRow.add(tile);

        tile.setPrefSize(tileSize, tileSize);
        String tileColor = (row + col) % 2 == 0 ? "rgba(235, 236, 208, 1)" : "rgba(115, 149, 82, 1)";
        tile.setStyle("-fx-background-color: " + tileColor + ";");

        if (gm.getPieceAt(col, row) != null) {
          ImageView piece = new ImageView();
          char pieceFEN = gm.getPieceAt(col, row).getFENName();
          switch (pieceFEN) {
            case 'K':
              Image w_king = new Image("pieces/w_king.png");
              piece.setImage(w_king);
              break;
            case 'Q':
              Image w_queen = new Image("pieces/w_queen.png");
              piece.setImage(w_queen);
              break;
            case 'B':
              Image w_bishop = new Image("pieces/w_bishop.png");
              piece.setImage(w_bishop);
              break;
            case 'N':
              Image w_knight = new Image("pieces/w_knight.png");
              piece.setImage(w_knight);
              break;
            case 'R':
              Image w_rook = new Image("pieces/w_rook.png");
              piece.setImage(w_rook);
              break;
            case 'P':
              Image w_pawn = new Image("pieces/w_pawn.png");
              piece.setImage(w_pawn);
              break;
            case 'k':
              Image b_king = new Image("pieces/b_king.png");
              piece.setImage(b_king);
              break;
            case 'q':
              Image b_queen = new Image("pieces/b_queen.png");
              piece.setImage(b_queen);
              break;
            case 'b':
              Image b_bishop = new Image("pieces/b_bishop.png");
              piece.setImage(b_bishop);
              break;
            case 'n':
              Image b_knight = new Image("pieces/b_knight.png");
              piece.setImage(b_knight);
              break;
            case 'r':
              Image b_rook = new Image("pieces/b_rook.png");
              piece.setImage(b_rook);
              break;
            case 'p':
              Image b_pawn = new Image("pieces/b_pawn.png");
              piece.setImage(b_pawn);
              break;
            default:
              break;
          }
          ;

          tile.getChildren().add(piece);

          piece.setFitWidth(tileSize);
          piece.setFitHeight(tileSize);
          piece.setPickOnBounds(true);

          final int rowIndex = row;
          final int colIndex = col;

          piece.setOnMouseClicked(event -> {
            showPossibilities(colIndex, rowIndex);
          });
        }

        chessBoard.add(tile, col, row);
      }

      tileList.add(tileListRow);
    }
  }

  public void showPossibilities(int x, int y) {
    int indicatorSize = 25;

    System.out.println(gm.getPossibilities(x, y));

    String indicatorStyle = "-fx-background-color: rgba(0, 0, 0, 0.25); " +
        "-fx-background-radius: 50%; " +
        "-fx-background-insets: 25%;";
    // returns 69 if piece cant move there
    // 1 if can move there
    // 2 if can attack there
    // 3 states where is the figure
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        Pane tile = tileList.get(j).get(i);

        switch (gm.getPossibilities(x, y).get(j).get(i)) {
          case 1:
            Pane indicator = new Pane();
            indicator.setPrefSize(indicatorSize, indicatorSize);
            indicator.setLayoutX((tileSize - indicatorSize) / 2);
            indicator.setLayoutY((tileSize - indicatorSize) / 2);
            tile.getChildren().add(indicator);
            indicator.setStyle(indicatorStyle);
            // "-fx-background-color: transparent;" + // Ensure the tile itself remains
            // visible
            // "-fx-shape: 'M50,25a25,25 0 1,0 50,0a25,25 0 1,0 -50,0';" + // Circle path
            // for styling
            // "-fx-background-insets: 0;" +
            // "-fx-opacity: 0.7;" // Semi-transparency effects
            break;
          case 2:
            tile.setStyle(
                "-fx-background-color: transparent;" + // Ensure the tile itself remains visible
                    "-fx-shape: 'M50,25a25,25 0 1,0 50,0a25,25 0 1,0 -50,0';" + // Circle path for styling
                    "-fx-background-insets: 0;" +
                    "-fx-opacity: 0.7;" // Semi-transparency effects
            );
            break;
          default:
            break;
        }
      }
    }
  }
}
