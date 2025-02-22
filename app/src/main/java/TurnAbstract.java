import javafx.fxml.FXML;
import java.util.Random;

public abstract class TurnAbstract {
  protected GameManager.GM gm = new GameManager.GM();
  // yes i know that static and instance bools default to false
  protected static boolean notifyPending = false;

  public TurnAbstract() {
    this.gm = new GameManager.GM();
  }

  public static int turnCount = 0;

  public void turn() {
    if (turnCount % 2 == 0) {
      gm.setTurn(true); // White
    } else {
      gm.setTurn(false); // Black
    }

    notifyPending = true;

    turnCount++;
    System.out.println("##############");
    System.out.println("  Turn no. " + turnCount);
    System.out.println("##############");
  }

  public String quoteOfTheTurn() {
    String quote[] = {
        "Exceptionally stupid",
        "Ridiculous",
        "Frowned upon",
        "Exhilirating",
        "Downright embarassing",
        "Are you braindamaged?",
        "You good?",
        "The hell is wrong with you?",
        "You bloody cretin!",
        "Watch your step amputee!",
        "Is your monitor good?",
        "Bri'ish ppl be like aight mate",
        "Is your monitor good?",
        "Is your monitor good?",
    };

    Random random = new Random();
    return quote[random.nextInt(quote.length)];
  }
}
