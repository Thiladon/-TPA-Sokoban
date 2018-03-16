package sample;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final long serialVersionUID = -240840600533728354L;
    
    public Window(String title, int width, int height) {

        /*
         *  (title) => { name of the Window }
         *  (size) => { 720px * 480px }
         *
         */

        this.setTitle(title);

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
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
        
        JPanel[] container = { new GamePanel(this), new MainPanel(), new OptionPanel() };
        
        //Create the panel that contains the "cards".
        JPanel cards = new JPanel(new CardLayout());
        
        for(JPanel item : container) {
            cards.add(item, item.getName());    
        }
        
        this.getContentPane().add(cards, BorderLayout.CENTER);
    }
}
