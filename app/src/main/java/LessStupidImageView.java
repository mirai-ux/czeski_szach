import javafx.scene.image.ImageView;

public class LessStupidImageView extends ImageView {
  private int x;
  private int y;

  public void setTileX(int xIndex) {
    x = xIndex;
  }

  public void setTileY(int yIndex) {
    y = yIndex;
  }

  public int getTileX() {
    return x;
  }

  public int getTileY() {
    return y;
  }
}
