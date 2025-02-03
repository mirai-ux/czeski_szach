import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Bubbles {
  public Label bubbleLabel;
  public StackPane bubble;

  public Bubbles() {
    this("Are you stupid enough to forget about the constructor argument?");
  }

  public Bubbles(String bubbleText) {
    bubbleLabel = new Label(bubbleText);
    bubbleLabel.setStyle("-fx-padding: 10px; -fx-background-color: lightblue; -fx-background-radius: 15px;");
    bubble = new StackPane(bubbleLabel);
  }
}
