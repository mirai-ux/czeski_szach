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
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chessboard.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 700, 700);
    stage.setTitle("za czess!");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    // BoardController board_init = new BoardController();
    // board_init.initialize();
    System.out.println("no");
    GM gm = new GM();

    gm.tests();

    launch();
  }
}
