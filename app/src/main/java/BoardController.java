import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.util.*;
import java.io.IOException;
import javafx.collections.ObservableList;

public class BoardController extends TurnAbstract {
  public BoardController() {
    super();
  }

  List<List<Pane>> tileList = new ArrayList<>();
  private List<LessStupidImageView> currentFigures = new ArrayList<>();
  private List<Pane> currentIndicators = new ArrayList<>();
  private LessStupidImageView selectedPiece = null;

  @FXML
  private Label quoteLabel;
  @FXML
  private GridPane chessBoard;

  public void initialize() {
    for (int row = 0; row < 8; row++) {
      List<Pane> tileListRow = new ArrayList<>();
      for (int col = 0; col < 8; col++) {
        Pane tile = new Pane();
        tileListRow.add(tile);

        tile.setPrefSize(Styling.tileSize, Styling.tileSize);
        String tileColor = (row + col) % 2 == 0 ? Styling.OffWhite : Styling.PaleGreen;
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

          piece.setFitWidth(Styling.tileSize);
          piece.setFitHeight(Styling.tileSize);
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
    loadHistory();
    turn();
  }

  public void displayPossibilities(int x, int y) {
    clearIndicators();

    // System.out.println(gm.getPossibilities(x, y));
    GameManager.Helpers.printArray8x8(gm.getPossibilities(x, y));

    String positionStyleModifier = (x + y) % 2 == 0 ? Styling.OffWhiteSelected : Styling.PaleGreenSelected;
    String positionStyle = "-fx-background-color: " + positionStyleModifier;

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
            indicator.setPrefSize(Styling.moveSize, Styling.moveSize);
            indicator.setLayoutX((Styling.tileSize - Styling.moveSize) / 2);
            indicator.setLayoutY((Styling.tileSize - Styling.moveSize) / 2);
            indicator.setStyle(Styling.moveStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            // move
            indicator.setOnMouseClicked(event -> {
              displayMove(tileList.get(y).get(x), iIndex, jIndex);
              gm.selectDestination(iIndex, jIndex);
              turn();
              quoteLabel.setText(quoteOfTheTurn());
              historyController.displayMoveHistory(gm.getNiceLastMove());
              clearIndicators();
            });
            break;
          case 2:
            indicator.setPrefSize(Styling.captureSize, Styling.captureSize);
            indicator.setLayoutX((Styling.tileSize - Styling.captureSize) / 2);
            indicator.setLayoutY((Styling.tileSize - Styling.captureSize) / 2);
            indicator.setStyle(Styling.captureStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            // capture
            indicator.setOnMouseClicked(event -> {
              displayCapture(tileList.get(y).get(x), iIndex, jIndex);
              gm.selectDestination(iIndex, jIndex);
              turn();
              quoteLabel.setText(quoteOfTheTurn());
              historyController.displayMoveHistory(gm.getNiceLastMove());
              clearIndicators();
            });
            break;
          case 5:
          case 8:
          case 3:
            // this Pane has no css attached to it, it just sits on top op the
            // figure so that you can deselect it while still preserving the
            // easy to click hitboxes of the figures and the background change
            Pane deselectIndicator = new Pane();

            deselectIndicator.setPrefSize(Styling.tileSize, Styling.tileSize);
            tile.getChildren().add(deselectIndicator);
            currentIndicators.add(deselectIndicator);

            indicator.setPrefSize(Styling.tileSize, Styling.tileSize);
            indicator.setStyle(positionStyle);
            tile.getChildren().add(0, indicator); // the zero adds it as the 0th index to not display above the pieces
            currentIndicators.add(indicator);

            new Bubble("Piss off mate", tile);

            deselectIndicator.setOnMouseClicked(event -> {
              clearIndicators();
            });
            break;
          case 4:
            indicator.setPrefSize(Styling.captureSize, Styling.captureSize);
            indicator.setLayoutX((Styling.tileSize - Styling.captureSize) / 2);
            indicator.setLayoutY((Styling.tileSize - Styling.captureSize) / 2);
            indicator.setStyle(Styling.captureStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            indicator.setOnMouseClicked(event -> {
              displayCastle("Q");
              turn();
              quoteLabel.setText(quoteOfTheTurn());
              historyController.displayMoveHistory(gm.getNiceLastMove());
              clearIndicators();
            });
            break;
          case 6:
            indicator.setPrefSize(Styling.captureSize, Styling.captureSize);
            indicator.setLayoutX((Styling.tileSize - Styling.captureSize) / 2);
            indicator.setLayoutY((Styling.tileSize - Styling.captureSize) / 2);
            indicator.setStyle(Styling.captureStyle);
            tile.getChildren().add(indicator);
            currentIndicators.add(indicator);

            indicator.setOnMouseClicked(event -> {
              displayCastle("K");
              turn();
              quoteLabel.setText(quoteOfTheTurn());
              historyController.displayMoveHistory(gm.getNiceLastMove());
              clearIndicators();
            });
            break;
          case 102:
            // this Pane has no css attached to it, it just sits on top op the
            // figure so that you can deselect it while still preserving the
            // easy to click hitboxes of the figures and the background change
            Pane inCheckIndicator = new Pane();

            inCheckIndicator.setPrefSize(Styling.tileSize, Styling.tileSize);
            tile.getChildren().add(inCheckIndicator);
            currentIndicators.add(inCheckIndicator);

            indicator.setPrefSize(Styling.tileSize, Styling.tileSize);
            indicator.setStyle(positionStyle);
            tile.getChildren().add(0, indicator); // the zero adds it as the 0th index to not display above the pieces
            currentIndicators.add(indicator);

            inCheckIndicator.setOnMouseClicked(event -> {
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

  private void styleCastle(Pane indicator, Pane tile) {
    indicator.setPrefSize(Styling.captureSize, Styling.captureSize);
    indicator.setLayoutX((Styling.tileSize - Styling.captureSize) / 2);
    indicator.setLayoutY((Styling.tileSize - Styling.captureSize) / 2);
    // indicator.setStyle(Styling.castleStyle);
    // indicator.getStylesheets().add("hollow-triangle");
    indicator.setStyle(Styling.captureStyle);
    tile.getChildren().add(indicator);
    currentIndicators.add(indicator);

    // capture
    indicator.setOnMouseClicked(event -> {
      displayCastle("Q");
      turn();
      quoteLabel.setText(quoteOfTheTurn());
      historyController.displayMoveHistory(gm.getNiceLastMove());
      clearIndicators();
    });
  }

  private void displayCastle(String where) {
    LessStupidImageView king;
    LessStupidImageView rook;
    Pane kingParent;
    Pane rookParent;

    // simply put if white castles, row = 7, if it's black who
    // castles, then row = 0;
    int row;
    int kingStartX;
    int rookStartX;
    int kingEndX;
    int rookEndX;

    switch (where) {
      case "Q":
        row = 7;
        kingStartX = 4;
        rookStartX = 0;
        kingEndX = 2;
        rookEndX = 3;
        break;
      case "K":
        row = 7;
        kingStartX = 4;
        rookStartX = 7;
        kingEndX = 6;
        rookEndX = 5;
        break;
      case "q":
        row = 0;
        kingStartX = 4;
        rookStartX = 0;
        kingEndX = 2;
        rookEndX = 3;
        break;
      case "k":
        row = 0;
        kingStartX = 4;
        rookStartX = 7;
        kingEndX = 6;
        rookEndX = 5;
        break;
      default:
        throw new IllegalArgumentException("Non-existant $where argument");
    }

    king = (LessStupidImageView) tileList.get(row).get(kingStartX).getChildren()
        .filtered(tileChild -> tileChild instanceof LessStupidImageView)
        .get(0);
    selectedPiece = king;

    rook = (LessStupidImageView) tileList.get(row).get(rookStartX).getChildren()
        .filtered(tileChild -> tileChild instanceof LessStupidImageView)
        .get(0);

    kingParent = (Pane) king.getParent();
    rookParent = (Pane) rook.getParent();

    kingParent.getChildren().remove(king);
    rookParent.getChildren().remove(rook);

    tileList.get(row).get(kingEndX).getChildren().add(king);
    tileList.get(row).get(rookEndX).getChildren().add(rook);

    gm.castling(where);
    selectedPiece = null;
  }

  @FXML
  private StackPane historyStackPane;

  private HistoryController historyController;

  private void loadHistory() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("history.fxml"));
      AnchorPane historyAnchorPane = fxmlLoader.load();
      historyController = fxmlLoader.getController();
      historyStackPane.getChildren().add(historyAnchorPane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
