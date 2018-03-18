package sample;

import javax.swing.*;
import java.awt.*;

public class Main {
	
    private static final long serialVersionUID = -240840600533728354L;
	
	public static void main(String[] args) {

        JFrame frame = new JFrame("Sokoban");

        int width = 720, height = 480;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Contains all the frame on our window
        GamePanel gamePanel = new GamePanel(frame, width, height);
        MainPanel mainPanel = new MainPanel();
        OptionPanel optionPanel = new OptionPanel();

        JPanel[] container = { gamePanel, mainPanel, optionPanel };
        
        //Create the panel that contains the "cards".
        JPanel cards = new JPanel(new CardLayout());
        
        for(JPanel item : container) {
            cards.add(item, item.getName());    
        }
        
        frame.getContentPane().add(cards, BorderLayout.CENTER);
        gamePanel.game.start();

        /*
         * Allow the window to be dislay in top of all
         *
         */

        frame.pack();
        frame.setVisible(true);

	}
}

