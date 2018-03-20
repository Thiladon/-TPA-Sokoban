package sample;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Loading{

  private ArrayList<String> liste = new ArrayList<>();
  private ArrayList<Character> liste = new ArrayList<>();//liste de char brute
  private HashMap<Character,Integer> dico = new HashMap<>();

  public Loading(){

    this.dico.put('-',0);//sol
    this.dico.put('$',1);//caisse
    this.dico.put('&',3);
    this.dico.put('#',2);//mur
    this.dico.put('@',5);//player
    this.dico.put('.',10);//cible
    this.dico.put('E',15);//cible
  }

  public void loadTxt (String fichier) throws IOException{
    /*
    *fonction de récupération qui lit un fichier.txt -ou autre-
    *met les info lut dans liste sous la forme de char
    */
    FileReader inputStream = null;
    try {
        inputStream = new FileReader(fichier);
        int c;
        while ((c = inputStream.read()) != -1) {
          this.liste.add((char)c);
        }
    }
    finally {
        if (inputStream != null) {
            inputStream.close();
        }
    }
  }

  public ArrayList<String> creatBoard(){
    /*
    *créé le tab3 à partir de liste
    *passe de list en char à liste2 en String en retirant les " "
    *passe de liste2 à tab3 en triant les caractères spéciaux et en transformant les String en int
    */
    //===================== de liste à liste2 =======================
    ArrayList<String> liste2 = new ArrayList<>();//liste de String sans " "
    for (Character num : this.liste) {
      String mot = Character.toString(num);
      if ((mot == " ")) {
        System.out.println(" ");
      }
      else {
        liste2.add(mot);
      }
    }
    return liste2;
  }

  public int[][] instanceBoard(ArrayList<Character> liste2){
    //===============================================================
    //************************** de liste2 à tab3 *******************
    //--------------- calcul pour l'initialistion de tab3 -----------
    int nbi = 0;//nombre de ligne
    int nbj = 0;//nombre de colonne
    //int tab3[][];//tableau
    int[][] tab3 = new int[50][50];//initialistion de tab3
    //----------------------------------------------------------------
    int rgL = 0;//rang liste2
    for (int i = 0; i<liste2.size(); ) {
      for (int j = 0; j < liste2.size(); ) {
        if (liste2.get(rgL).equals(",")) {
          j+=1;//passage colonne suivante
        }
        else if (liste2.get(rgL).equals("d")) {
          i+=1;//passage ligne suivante
          j=0;
        }
        else if (liste2.get(rgL).equals("E")) {//fin de la boucle
          i=liste2.size()+1;
          j=liste2.size()+1;
        }
        else{
          //System.out.println(i+" "+j);
          String number = liste2.get(rgL);
          int num = Integer.parseInt(number);//bug...
          tab3[i][j]=num;
        }
        rgL+=1;
      }
    }
    return tab3;
    //**************************************************************************
  }

  //=*=*=*=*=*=*=*=*=*=*=*=*=*== fonction usuel ==*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
  public void printChar(){
    /*
    *affiche tout les char de liste
    */
    for (Character num : this.liste) {
      System.out.println(num);
    }
  }

  /*public void printChar2(){
    /*
    *affiche tous les String de liste2
    /
    String lt2="";
    for (String num : this.liste2) {
      lt2+=num;
    }
    System.out.println(lt2);
  }*/

  /*public void printTabl(){
    /*
    *affiche ligne après ligne tab3
    /
    String aff = "";
    for (int i = 0;i<tab3.length ;i++ ) {
      aff="";
      for (int j = 0;j<tab3.length ;j++ ) {
        aff += tab3[i][j];
      }
      System.out.println(aff);
    }
  }*/
  //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
  public void printTab(int[][] sokoban){
    String res = "Sokoban : \n";

    for (int j = 0; j < 9; j++){
      for (int i = 0; i < 8; i++) {
        res += sokoban[j][i] + " ";
      }
      res += "\n";
    }

    System.out.println(res);
  }



  //============================================================================
  public int[][] xsbBoard(ArrayList<Character> liste){
    //===============================================================
    //************************** Parcours d'un fichier xsb *******************
    //---------------  -----------
    int nbi = 0;//nombre de ligne
    int nbj = 0;//nombre de colonne
    //int tab3[][];//tableau
    for (Character a : liste) {
      if (a == "&"){
        nbi+=1;
      }
    }

    while(Character b : liste != "&" ){
      nbj+=1;
    }

    int i = 0;
    int j = 0;
    int r = 0;
    int[][] tab = new int[nbi + 10][nbj + 10];

     while (i < nbi + 1){
       while (j < nbj + 1){
          if (dico.get(liste.get(r))== 3){
             j = 0;
             i += 1;
           }
           else if (dico.get(liste.get(r))== 15){
             j = nbj + 2;
             i = nbi + 2;
           }
           else {
             tab[i][j] = dico.get(c);
             j += 1;
           }
           r += 1;
         }
       }
       System.out.println(tab);
       return tab;
     }
}
