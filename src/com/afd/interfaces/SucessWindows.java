package com.afd.interfaces;

import javax.swing.*;
import java.awt.*;

public class SucessWindows {

    public SucessWindows() {
        JFrame frame = new JFrame("Sucesso");
        JPanel panel = new JPanel();
        JPanel panelMessage = new JPanel();

        panel.setLayout(new GridLayout(2, 1));
        panelMessage.setLayout(new GridLayout(1, 1));

        JLabel labelMessage = new JLabel("DEU TUDO CERTO!", SwingConstants.CENTER);

        panelMessage.add(labelMessage);

        panel.add(panelMessage);

        frame.setSize(250, 175);
        frame.setLocation(800, 400);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
