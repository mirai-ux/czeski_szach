import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Main extends Application {
  private Forfeit forfeit;
  private Stage stage;

  // public void forfeitManager() {
  // this.stage.close();
  // Platform.runLater(() -> {
  // try {
  // start(new Stage());
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // });
  // }

  @Override
  public void start(Stage stage) throws IOException {
    // forfeit = new Forfeit(stage);
    this.stage = stage;
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chessboard.fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 700, 700);

      scene.setFill(Color.TRANSPARENT);
      stage.initStyle(StageStyle.TRANSPARENT);
      // System.out.println(scene.getFill());

      stage.setTitle("Greatestest Chess of Every Time There Ever Was");
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // final Button forfeitButton = new Button("🏳️");
    // forfeitButton.setOnAction(__ -> {
    // System.out.println("Restarting");
    // });

  }

  public static void main(String[] args) {
    launch();
  }
}
