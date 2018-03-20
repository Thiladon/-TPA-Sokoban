package sample;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Window mainWindow = new Window("Sokoban");

        Board board = new Board();
        board.locatePlayer();

        System.out.println(board);

        Scanner input = new Scanner(System.in);
        String msg;

        while (!board.isFinished())
        {
            System.out.print("where : ");
            msg = input.nextLine();

            if (msg.equals("stop"))
            {
                System.out.println("PEACE OUT");
                return;
            }

            else
            {
                board.move(msg);
                board.locatePlayer();
                
                System.out.println(board);
            }
        }
        
        System.out.println("Gagnos");
    }
}
