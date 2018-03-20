package sample;

public class Player{
  private int x;
  public int y;

  public Player(Board board){
    int[][] sok = board.sokoban;

    // chercher joueur
    for (int j = 0; j < board.getHeight(); j++)
      for (int i = 0; i < board.getWidth(); i++)
        if (sok[j][i] == 5){
          x = i;
          y = j;
          i = 100;
          j = 100;
        }
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public boolean move(int[][] sok, String dir, boolean force){
    int x = 0;
    int y = 0;

    switch (dir)
    {
      case "z":
        y--;
      case "d":
        x++;
      case "s":
        y++;
      case "q":
        x--;
    }
    return move(sok,x,y,force);
  }

  private boolean move(int[][] sok, int xMove, int yMove, boolean force){
    int toMove = sok[y + yMove][x + xMove];
    // String
    // vide ou cible
    if (toMove == Board.trad.get(toMove) || toMove == 10){
      sok[y][x] = 0;
      toMove = 5;
      return true;
    }
    // caisse
    else if (toMove == 1){
      toMove = sok[y+2*yMove][x+2*xMove];
      if (toMove == 2)
        return false;
      else if (toMove == 0 || toMove == 3){
        sok[y+2*yMove][x+2*xMove] = toMove + 1;
        return true;
      }
    }
    return false;
  }

  public String toString() {
    return x + " " + y;
  }
}
