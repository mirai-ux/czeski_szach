import GameManager.*;
import Figures.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chessboard.fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 700, 700);
      stage.setTitle("czess");
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
