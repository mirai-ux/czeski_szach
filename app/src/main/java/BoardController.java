import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.text.Font;
// import javafx.scene.control.TextField;
import javafx.scene.image.Image;
// import javafx.scene.layout.Pane;
// import javafx.stage.Stage;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import java.io.IOException;
import java.util.*;

public class BoardController {
  private GameManager.GM gm = new GameManager.GM();

  int turnCount = 0;

  private List<LessStupidImageView> currentFigures = new ArrayList<>();
  private List<Pane> currentIndicators = new ArrayList<>();

  private LessStupidImageView selectedPiece = null;

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
          LessStupidImageView piece = new LessStupidImageView();
          char pieceFEN = gm.getPieceAt(col, row).getFENName();
          switch (pieceFEN) {
            case 'K':
              Image w_king = new Image("pieces/w_king.png");
              piece.setImage(w_king);
              piece.setTeam(true);
              break;
            case 'Q':
              Image w_queen = new Image("pieces/w_queen.png");
              piece.setImage(w_queen);
              piece.setTeam(true);
              break;
            case 'B':
              Image w_bishop = new Image("pieces/w_bishop.png");
              piece.setImage(w_bishop);
              piece.setTeam(true);
              break;
            case 'N':
              Image w_knight = new Image("pieces/w_knight.png");
              piece.setImage(w_knight);
              piece.setTeam(true);
              break;
            case 'R':
              Image w_rook = new Image("pieces/w_rook.png");
              piece.setImage(w_rook);
              piece.setTeam(true);
              break;
            case 'P':
              Image w_pawn = new Image("pieces/w_pawn.png");
              piece.setImage(w_pawn);
              piece.setTeam(true);
              break;
            case 'k':
              Image b_king = new Image("pieces/b_king.png");
              piece.setImage(b_king);
              piece.setTeam(false);
              break;
            case 'q':
              Image b_queen = new Image("pieces/b_queen.png");
              piece.setImage(b_queen);
              piece.setTeam(false);
              break;
            case 'b':
              Image b_bishop = new Image("pieces/b_bishop.png");
              piece.setImage(b_bishop);
              piece.setTeam(false);
              break;
            case 'n':
              Image b_knight = new Image("pieces/b_knight.png");
              piece.setImage(b_knight);
              piece.setTeam(false);
              break;
            case 'r':
              Image b_rook = new Image("pieces/b_rook.png");
              piece.setImage(b_rook);
              piece.setTeam(false);
              break;
            case 'p':
              Image b_pawn = new Image("pieces/b_pawn.png");
              piece.setImage(b_pawn);
              piece.setTeam(false);
              break;
            default:
              break;
          }
          ;

          tile.getChildren().add(piece);

          piece.setFitWidth(tileSize);
          piece.setFitHeight(tileSize);
          piece.setPickOnBounds(true);

          piece.setTileX(col);
          piece.setTileY(row);

          currentFigures.add(piece);

          // White
          if (piece.getTeam()) {
            piece.setOnMouseClicked(event -> {
              if (gm.getTurn()) {
                selectedPiece = piece;
                displayPossibilities(piece.getTileX(), piece.getTileY());
              }
            });
          } else {
            piece.setOnMouseClicked(event -> {
              if (!gm.getTurn()) {
                selectedPiece = piece;
                displayPossibilities(piece.getTileX(), piece.getTileY());
              }
            });
          }
        }

        chessBoard.add(tile, col, row);
      }

      tileList.add(tileListRow);
    }

    turn();
  }

  public void turn() {
    if (turnCount % 2 == 0) {
      gm.setTurn(true); // White
    } else {
      gm.setTurn(false); // Black
    }

    turnCount++;
    System.out.println("##############");
    System.out.println("  Turn no. " + turnCount);
    System.out.println(" History: \n" + gm.getHistory());
    System.out.println("##############");
  }

  public void displayPossibilities(int x, int y) {
    int moveSize = 25;
    int captureSize = 70;
    System.out.println("x = " + x);
    System.out.println("y = " + y);

    clearIndicators();

    String moveStyle = "-fx-background-color: rgba(0, 0, 0, 0.25); " +
        "-fx-background-radius: 50%; " +
        "-fx-background-insets: 25%;";
    String captureStyle = "-fx-background-color: transparent; " +
        "-fx-border-color: rgba(0, 0, 0, 0.25); " +
        "-fx-border-width: 5px; " +
        "-fx-border-radius: 50%;";
    String positionStyleModifier = (x + y) % 2 == 0 ? "rgba(245, 246, 129, 1)" : "rgba(185, 202, 66, 1)";
    String positionStyle = "-fx-background-color: " + positionStyleModifier;

    // System.out.println("Before move");
    // printListOfLists(gm.getPossibilities(x, y));

    // System.out.println("Before move | getBoard");
    // printListOfLists(gm.getBoard());
    // printListOfLists(gm.getMoves());

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
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

            // move
            indicator.setOnMouseClicked(event -> {
              displayMove(tileList.get(y).get(x), iIndex, jIndex);
              gm.selectDestination(iIndex, jIndex);
              turn();
              clearIndicators();

              // System.out.println("iIndex = " + iIndex);
              // System.out.println("jIndex = " + jIndex);
              // System.out.println("After move | getPossibilities");
              // printListOfLists(gm.getPossibilities(iIndex, jIndex));
            });
            break;
          case 2:
            indicator.setPrefSize(captureSize, captureSize);
            indicator.setLayoutX((tileSize - captureSize) / 2);
            indicator.setLayoutY((tileSize - captureSize) / 2);
            indicator.setStyle(captureStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            // capture
            indicator.setOnMouseClicked(event -> {
              displayCapture(tileList.get(y).get(x), iIndex, jIndex);
              gm.selectDestination(iIndex, jIndex);
              turn();
              clearIndicators();
            });
            break;
          case 3:
            // this Pane has no css attached to it, it just sits on top op the
            // figure so that you can deselect it while still preserving the
            // easy to click hitboxes of the figures and the background change
            Pane deselectIndicator = new Pane();

            deselectIndicator.setPrefSize(tileSize, tileSize);
            tile.getChildren().add(deselectIndicator);
            currentIndicators.add(deselectIndicator);

            indicator.setPrefSize(tileSize, tileSize);
            indicator.setStyle(positionStyle);
            tile.getChildren().add(0, indicator); // the zero adds it as the 0th index to not display above the pieces
            currentIndicators.add(indicator);

            deselectIndicator.setOnMouseClicked(event -> {
              // System.out.println("succ");
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

  private void displayMove(Pane sourceTile, int xDest, int yDest) {
    sourceTile.getChildren().remove(selectedPiece);

    tileList.get(yDest).get(xDest).getChildren().add(selectedPiece);

    selectedPiece.setTileX(xDest);
    selectedPiece.setTileY(yDest);
    selectedPiece = null;
  }

  private void displayCapture(Pane sourceTile, int xDest, int yDest) {
    for (int index = 0; index < currentFigures.size(); index++) {
      if ((currentFigures.get(index).getTileX() == xDest) && (currentFigures.get(index).getTileY() == yDest)) {
        tileList.get(yDest).get(xDest).getChildren().remove(currentFigures.get(index));
        currentFigures.remove(index);
      }
    }

    // rest of the logic is same as displayMove
    sourceTile.getChildren().remove(selectedPiece);

    tileList.get(yDest).get(xDest).getChildren().add(selectedPiece);

    selectedPiece.setTileX(xDest);
    selectedPiece.setTileY(yDest);
    selectedPiece = null;

  }

  public static void printListOfLists(List<List<Integer>> list) {
    for (List<Integer> row : list) {
      for (int num : row) {
        System.out.print(num + "\t");
      }
      System.out.println();
    }
  }
}
