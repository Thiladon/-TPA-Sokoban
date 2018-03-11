package sample;

import javax.swing.*;
import java.awt.*;

public class mainUI extends Panel {

    private CardLayout c1 = new CardLayout();
    private JPanel panel = new JPanel();

    private JButton[] buttonTab = {
            new JButton("1 Joueur"),
            new JButton("2 Joueur"),
            new JButton("Options"),
            new JButton("Quitter")
    };

    public mainUI() {
        super();

        JPanel buttonPanel = new JPanel();

        for(JButton buttons : buttonTab) {
            buttonPanel.add(buttons);
        }

        panel.setLayout(c1);
        panel.add(buttonPanel, BorderLayout.NORTH);

        this.setVisible(true);
    }

}
