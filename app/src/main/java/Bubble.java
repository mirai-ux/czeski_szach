import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Bubble {
  public Label bubbleLabel;
  public StackPane bubble;

  public Bubble() {
    this("Are you stupid enough to forget about the constructor argument?");
  }

  public Bubble(String bubbleText) {
    bubbleLabel = new Label(bubbleText);
    bubbleLabel.setStyle("-fx-padding: 10px; -fx-background-color: lightblue; -fx-background-radius: 15px;");
    bubble = new StackPane(bubbleLabel);
  }
}
