package sample;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JTabbedPane container;

    public Window(String title) {
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On créer ici plusieurs container :

        Panel[] tPanel = { new mainUI()};

        // Création du contenair des Panel :

        container = new JTabbedPane();

        int i = 0;
        for(Panel tPan : tPanel) {
            container.add("Onglet n° "+(++i), tPan);
        }
        this.getContentPane().add(container);
        this.setVisible(true);
    }
}
