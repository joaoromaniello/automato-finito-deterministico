package com.afd.interfaces;

import javax.swing.*;
import java.awt.*;

public class Windows extends InputWindows {

    public Windows() {
        frame = new JFrame("Autômato finito determinístico");
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(1, 1));
        panel.add(this.getPanel());

        frame.setSize(450, 175);
        frame.setLocation(800, 400);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}