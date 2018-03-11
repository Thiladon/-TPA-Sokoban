package sample;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(String title) {

        /*
         *  (title) => { name of the Window }
         *  (size) => { 720px * 480px }
         *
         */

        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(720, 480));
        // this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Contains all the frame on our window

        this.addComponentToPane();

        /*
         * Allow the window to be dislay in top of all
         *
         */

        this.pack();
        this.setVisible(true);
    }

    public void addComponentToPane() {
        
        JPanel[] container = { new GamePanel(), new MainPanel(), new OptionPanel() };
        
        //Create the panel that contains the "cards".
        JPanel cards = new JPanel(new CardLayout());
        
        for(JPanel item : container) {
            cards.add(item, item.getName());    
        }
        
        this.getContentPane().add(cards, BorderLayout.CENTER);
    }
}
