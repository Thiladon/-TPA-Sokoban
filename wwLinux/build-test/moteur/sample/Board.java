package sample;

import java.util.HashMap;

public class Board
{
    public static HashMap<Integer, Character> intToChar;
    public static HashMap<Character, Integer> charToInt;
    private static final int cible = 10;
    public int[][] grid;
    private final int width;
    private final int height;
    private int xPlr;
    public int getPlrX() { return xPlr; }
    private int yPlr;
    public int getPlrY() { return yPlr; }

    public Board()
    {
        intToChar = new HashMap<>();
        charToInt = new HashMap<>();

        intToChar.put(0, ' ');  // rien
        charToInt.put(' ', 0);

        intToChar.put(1, '$');  // caisse
        charToInt.put('$', 1);

        intToChar.put(2, '#');  // mur
        charToInt.put('#', 2);

        intToChar.put(5, '@');  // player
        charToInt.put('@', 5);

        intToChar.put(10, '.');  // cible
        charToInt.put('.', 10);

        intToChar.put(11, '*');  // caisse sur cible
        charToInt.put('*', 11);

        intToChar.put(15, '+');  // player sur cible
        charToInt.put('+', 15);

        int[][] sokoban =
        {
            {0,0,2,2,2,2,2,0},
            {2,2,2,0,5,0,2,0},
            {2,10,0,0,1,0,2,0},
            {2,2,2,0,1,10,2,0},
            {2,10,2,2,1,0,2,0},
            {2,0,2,0,10,0,2,2},
            {2,0,2,0,10,0,0,2},
            {2,0,0,0,10,0,0,2},
            {2,2,2,2,2,2,2,2}
        };

        grid = sokoban;
        height = sokoban.length;
        width = sokoban[0].length;
    }

    public void locatePlayer()
    {
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                if (grid[j][i] % 10 == 5)
                {
                    xPlr = i;
                    yPlr = j;
                }
    }

    public boolean isFinished()
    {
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                if (grid[j][i] == 1)
                    return false;
        
        return true;
    }

    public boolean move(String dir)
    {
        int xMove = 0;
        int yMove = 0;

        switch (dir)
        {
            case "z":
                yMove--;
                break;

            case "d":
                xMove++;
                break;

            case "s":
                yMove++;
                break;

            case "q":
                xMove--;
                break;
        }

        char toMove = intToChar.get(grid[yPlr + yMove][xPlr + xMove]);

        // vide ou cible
        if (toMove == ' ' || toMove == '.' || toMove == '$')
        {
            if (toMove == '$')
            {
                char toDisplace = intToChar.get(grid[yPlr+2*yMove][xPlr+2*xMove]);

                if (toDisplace == ' ' || toDisplace == '.')
                {
                    grid[yPlr+2*yMove][xPlr+2*xMove] += charToInt.get('$');
                    grid[yPlr-yMove][xPlr+xMove] -= charToInt.get('$');
                }

                else
                    return false;
            }

            grid[yPlr][xPlr] -= 5;
            grid[yPlr+yMove][xPlr+xMove] += 5;

            return true;
        }

        return false;
    }


    @Override
    public String toString()
    {
        String res = "\nSokoban : \n\n";

        for (int j = 0; j < 9; j++)
        {
            for (int i = 0; i < 8; i++)
            {
                String add = intToChar.get(grid[j][i]) + " ";
                res += add;
                
                if ("null".equals(add))
                    System.out.println("who him iz : " + add);
            }
            
            res += "\n";
        }

        return res;
    }
}