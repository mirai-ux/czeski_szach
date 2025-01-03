package Figures;

import java.util.*;
import GameManager.*;

public abstract class Figure {
  protected int x; // X coordinate
  protected int y; // Y coordinate
  protected boolean isActive; // Wether piece is still in-game
  protected boolean isWhite; // Team White(1) or Black(0)
  protected int value; // Worth of piece
  protected char type; // Type of figure

  public Figure(int x_, int y_, boolean team) {
    x = x_;
    y = y_;
    isWhite = team;
    isActive = true;
  }

  public int[][] pathDisplay() {
    int start_x = x;
    int start_y = y;
    // Parse the starting values through a pathing function unique to each
    // and every figure
    int end_x = 0;
    int end_y = 0;
    int[][] end_xy = new int[end_x][end_y];
    return end_xy;
  }

  // Getters
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean getTeam() {
    return isWhite;
  }

  public boolean getActivity() {
    return isActive;
  }

  public int getValue() {
    return value;
  }

  public char getType() {
    return type;
  }

  // Setters
  public void setX(int x_) {
    x = x_;
  }

  public void setY(int y_) {
    y = y_;
  }

  public void setActivity(boolean status) {
    isActive = status;
  }

  public void setActivity(int x_, int y_, boolean status) {
    x = x_;
    y = y_;
    isActive = status;
  }

  // returns 69 if piece cant move there
  //         1  if can move there
  //         2  if can attack there 
  public abstract List< List< Integer >> getPossibleMoves( GameManager.GM gm );
}
