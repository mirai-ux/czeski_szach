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
  private List<List<Integer>> tileList;

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

    tileList = new ArrayList<>();

    int tileSize = 60;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Pane tile = new Pane();
        tile.setPrefSize(tileSize, tileSize);
        String tileColor = (row + col) % 2 == 0 ? "white" : "black";
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

          piece.setOnMouseClicked(event -> {
            System.out.println("skobidi");
          });
        }

        chessBoard.add(tile, col, row);
      }

    }
  }

  //
  // public void turn() {
  //
  // }
}
