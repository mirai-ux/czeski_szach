import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Forfeit {
  public Forfeit(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chessboard.fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 700, 700);

      scene.setFill(Color.TRANSPARENT);
      stage.initStyle(StageStyle.TRANSPARENT);
      System.out.println(scene.getFill());

      // scene.getStylesheets().add(getClass().getResource("/Styling.css").toExternalForm());

      stage.setTitle("Greatestest Chess of Every Time There Ever Was");
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
