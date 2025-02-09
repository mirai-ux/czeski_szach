package GameManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.checkerframework.checker.units.qual.A;

import Figures.*;

public class Helpers {
  private GameManager.GM gm;

  public void setGM( GameManager.GM gm_ ){ gm = gm_;}
  private Figure interpretFromSave(String save) {
    char type = save.charAt(0);
    boolean team = save.charAt(3) == 't';
    int x = (int) save.charAt(1) - 48;
    int y = (int) save.charAt(2) - 48;
    Figure piece;
    switch (type) {
      case 'r':
        piece = new Rook(x, y, team, gm);
        break;
      case 'k':
        piece = new Knight(x, y, team, gm);
        break;
      case 'b':
        piece = new Bishop(x, y, team, gm);
        break;
      case 'l': // like lord. k is taken by knight
        piece = new King(x, y, team, gm);
        break;
      case 'q':
        piece = new Queen(x, y, team, gm);
        break;
      default:
        piece = new Pawn(x, y, team, gm);
        break;
    }

    return piece;
  }

  public List<Figure> ReadFromFile(String fileName) {
    List<Figure> figures = new ArrayList<Figure>();

    try {
      Scanner scanner = new Scanner(new File("src/main/java/GameManager/Saves/" + fileName));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        figures.add(interpretFromSave(line));
      }
      scanner.close();
      return figures;

    } catch (Exception e) {
      // TODO: handle exception
    }
    return figures;
  }

  public static List<List<Integer>> InitializeArray8x8() {
    List<List<Integer>> Array8x8 = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < 8; j++) {
        row.add(69);
      }
      Array8x8.add(row);
    }
    return Array8x8;
  }

  public List<List<Integer>> UpgradeArray8x8(List<Figure> figures, List<List<Integer>> Array8x8) {
    Figure elem;
    int val, x, y;
    // System.out.println("Siema");
    // for (int i = 0; i < 32; i++) {
    for (int i = 0; i < figures.size() ; i++) {
      elem = figures.get(i);
      x = elem.getX();
      y = elem.getY();
      // System.out.println("x: " + x + "|y: "+y);
      if (elem.getActivity())
        val = i;
      else
        val = 69;
      Array8x8.get(y).set(x, val);
    }

    return Array8x8;
  }

  public void printArray8x8(List<List<Integer>> array) {
    for (int i = 0; i < 8; i++) {
      System.out.println();
      for (int j = 0; j < 8; j++) {
        System.out.print(array.get(i).get(j));
        System.out.print("\t");
      }
    }
  }

  public static List<List<Integer>> mergeTwoArrays8x8(List<List<Integer>> array1, List<List<Integer>> array2) {
    List<List<Integer>> merged = InitializeArray8x8();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        int val1 = array1.get(i).get(j);
        int val2 = array2.get(i).get(j);
        if (val1 == 1 || val2 == 1) {
          merged.get(i).set(j, 1);
        }
        if (val1 == 2 || val2 == 2) {
          merged.get(i).set(j, 2);
        }

      }
    }
    return merged;
  }

  public String ArrayToFEN(GM gm) {
    // based on this -> https://www.chess.com/terms/fen-chess
    List<List<Integer>> board = gm.getBoard();
    List<Figure> figures = gm.getFigures();
    String resultFEN = "";

    // Loops for piece positions in FEN
    for (int i = 0; i < 8; i++) {
      int empty = 0;
      for (int j = 0; j < 8; j++) {
        int currentID = board.get(i).get(j);
        // System.out.println(currentID);
        if (currentID == 69) {
          empty += 1;
        } else if (empty != 0) {
          resultFEN += empty;
          resultFEN += figures.get(currentID).getFENName();
          empty = 0;
        } else {
          resultFEN += figures.get(currentID).getFENName();
          empty = 0;
        }
      }
      if (empty != 0) {
        resultFEN += empty;
        empty = 0;
      }
      if (i == 7)
        continue;
      resultFEN += "/";
    }
    // whose turn this is
    if (gm.getTurn())
      resultFEN += " w";
    else
      resultFEN += " b";

    // now code castling
    resultFEN += " " + gm.checkCastling_Q();
    resultFEN += gm.checkCastling_K();
    resultFEN += gm.checkCastling_q();
    resultFEN += gm.checkCastling_k();
    // not playing with en passant
    resultFEN += " -";
    // how many halfmoves
    int length = gm.getHistory().size();
    resultFEN += " " + length;
    // full turns
    resultFEN += " " + ((int) length / 2 + 1);

    return resultFEN;
  }
}
