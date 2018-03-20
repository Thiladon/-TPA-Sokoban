package sample;

import java.util.ArrayList;

public class Board
{
  public int sokoban[][];
  private int width;
  private int height;

  public Board(int width, int height)
  {
    this.width = width;
    this.height = height;

    int sokob[][] = {
      {0,0,2,2,2,2,2,0},
      {2,2,2,0,0,0,2,0},
      {2,3,0,0,1,0,2,0},
      {2,2,2,0,1,3,2,0},
      {2,3,2,2,1,0,2,0},
      {2,0,2,0,3,0,2,2},
      {2,0,2,0,3,0,2,2},
      {2,0,0,0,3,0,0,2},
      {2,2,2,2,2,2,2,2}
    };
    
    this.sokoban = sokob;
  }

  public String toString(){
    String res = "Sokoban : \n";

    for (int j = 0; j < 9; j++){
      for (int i = 0; i < 8; i++) {
        res += sokoban[j][i] + " ";
      }
      res += "\n";
    }

    return res.toString();
  }
}
