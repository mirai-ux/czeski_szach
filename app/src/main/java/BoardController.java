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

import Figures.*;

public class BoardController {
  private GameManager.GM gm = new GameManager.GM();
  private List<List<Integer>> boardForController;
  private List<List<Integer>> possibleMovesForController;
  private List<Figure> AllFiguresForController;

  List<List<Pane>> tileList = new ArrayList<>();
  int tileSize = 60;

  @FXML
  private GridPane chessBoard;

  public void initialize() {
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

  private List<Pane> currentIndicators = new ArrayList<>();

  public void showPossibilities(int x, int y) {
    int moveSize = 25;
    int attackSize = 70;

    clearIndicators();
    System.out.println(gm.getPossibilities(x, y));

    String moveStyle = "-fx-background-color: rgba(0, 0, 0, 0.25); " +
        "-fx-background-radius: 50%; " +
        "-fx-background-insets: 25%;";
    String attackStyle = "-fx-background-color: transparent; " +
        "-fx-border-color: rgba(0, 0, 0, 0.25); " +
        "-fx-border-width: 5px; " +
        "-fx-border-radius: 50%;";
    String positionStyleModifier = (x + y) % 2 == 0 ? "rgba(245, 246, 129, 1)" : "rgba(185, 202, 66, 1)";
    String positionStyle = "-fx-background-color: " + positionStyleModifier;

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        Pane tile = tileList.get(j).get(i);
        Pane indicator = new Pane();

        final int iIndex = i;
        final int jIndex = j;

        switch (gm.getPossibilities(x, y).get(j).get(i)) {
          // returns 69 if piece cant move there
          // 1 if can move there
          // 2 if can attack there
          // 3 states where is the figure
          case 1:
            indicator.setPrefSize(moveSize, moveSize);
            indicator.setLayoutX((tileSize - moveSize) / 2);
            indicator.setLayoutY((tileSize - moveSize) / 2);
            indicator.setStyle(moveStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            // attack
            tile.setOnMouseClicked(event -> {
              gm.selectDestination(iIndex, jIndex);
              clearIndicators();
            });
            break;
          case 2:
            indicator.setPrefSize(attackSize, attackSize);
            indicator.setLayoutX((tileSize - attackSize) / 2);
            indicator.setLayoutY((tileSize - attackSize) / 2);
            indicator.setStyle(attackStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            // attack
            tile.setOnMouseClicked(event -> {
              gm.selectDestination(iIndex, jIndex);
              clearIndicators();
            });
            break;
          case 3:
            indicator.setPrefSize(tileSize, tileSize);
            indicator.setStyle(positionStyle);
            tile.getChildren().add(0, indicator); // the zero adds it as the 0th index to not display above the pieces
            currentIndicators.add(indicator);
            tile.setOnMouseClicked(event -> {
              clearIndicators();
            });
            break;
          default:
            break;
        }
      }
    }
  }

  private void clearIndicators() {
    for (Pane indicator : currentIndicators) {
      Pane parent = (Pane) indicator.getParent();
      if (parent != null) {
        parent.getChildren().remove(indicator);
      }
    }
    currentIndicators.clear();
  }
}
