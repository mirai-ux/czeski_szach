import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.stage.Screen;

public class Bubble extends Popup {
  private Label bubbleLabel;

  private Bubble() {
    // Not sure if a no arg constructor is necessary anymore
    // this.setManaged(false);
    // this.setClip(null);
  }

  private Bubble(String bubbleText) {
    this();

    bubbleLabel = new Label(bubbleText);
    bubbleLabel.setStyle("-fx-padding: 10px; -fx-background-color: lightblue; -fx-background-radius: 15px;");

    this.getContent().add(bubbleLabel);
  }

  public Bubble(String bubbleText, Pane parent) {
    this(bubbleText);

    Platform.runLater(() -> {
      Bounds bounds = parent.localToScreen(parent.getBoundsInLocal());

      double parentCenterX = bounds.getMinX() + (bounds.getWidth() / 2);
      double parentCenterY = bounds.getMinY() + (bounds.getHeight() / 2);

      this.setAutoHide(true);
      this.show(parent, parentCenterX, parentCenterY);
    });
  }
}
