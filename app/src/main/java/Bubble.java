import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.control.PopupControl;
import javafx.stage.Popup;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class Bubble extends Popup {
  private Label bubbleLabel;
  private StackPane wrapper = new StackPane();
  private Rectangle clip = new Rectangle();

  // testing exceptions in constructors
  public Bubble() {
    throw new IllegalArgumentException("Bubble cannot be created without any arguments");

    // Not sure if a no arg constructor is necessary anymore
    // this.setManaged(false);
    // this.setClip(null);
  }

  private Bubble(String bubbleText) {
    bubbleLabel = new Label(bubbleText);

    bubbleLabel.setStyle(
        "-fx-background-color: rgba(155, 183, 172, 1);" +
            "-fx-border-color: rgba(69, 133, 136);" +
            "-fx-padding: 5 20 5 20;");

    this.getContent().add(bubbleLabel);
  }

  public Bubble(String bubbleText, Pane parent) {
    this(bubbleText);
    // lose - lose
    // either i have white edges or clip into other panes
    // parent.getChildren().add(bubbleLabel);
    parent.getScene().setFill(Color.TRANSPARENT);

    // for some reason without this my monitors sometimes get confused where to
    // display popups
    Platform.runLater(() -> {
      Bounds bounds = parent.localToScreen(parent.getBoundsInLocal());

      double offset = (Math.random() * 20) - 10;
      double parentCenterX = (bounds.getMinX() + (bounds.getWidth() / 2)) + offset;

      offset = (Math.random() * 20) - 10;
      double parentCenterY = (bounds.getMinY() + (bounds.getHeight() / 2)) + offset;

      this.setAutoHide(true);
      this.show(parent, parentCenterX, parentCenterY);

      PauseTransition delay = new PauseTransition(Duration.seconds(1));
      delay.setOnFinished(e -> this.hide());
      delay.play();
    });
  }
}
