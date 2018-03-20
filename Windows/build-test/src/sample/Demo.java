package sample;
import java.io.IOException;
import java.util.ArrayList;

public class Demo{
  public static void main(String[] args) {
    String number = "1";
    int num = Integer.parseInt(number);
    System.out.println(num);
    Board soko = new Board(8,9);
    System.out.println(soko);
    Loading load = new Loading();
    try{
      load.loadTxt("sokotwo.txt");
      System.out.println("chargement !");
      //load.printChar();
      ArrayList<String> liste2 = load.creatBoard();
      int tab3[][] = load.instanceBoard(liste2);
      load.printTab(tab3);

    }
    catch(IOException e){
      System.out.println("echec de la lecture" + e);
    }
  }
}
