package com.afd.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccessWindows {

    private final JButton comeBack;
    private final JFrame frame;

    public SuccessWindows() {
        frame = new JFrame("Sucesso");
        JPanel panel = new JPanel();
        JPanel panelMessage = new JPanel();
        JPanel panelButton = new JPanel();


        panelMessage.setLayout(new GridLayout(1, 1));
        panelButton.setLayout(new GridLayout(1, 1));

        panel.setLayout(new GridLayout(2, 1));

        JLabel labelMessage = new JLabel("DEU TUDO CERTO!", SwingConstants.CENTER);
        comeBack = new JButton("Voltar");

        panelMessage.add(labelMessage);

        panelButton.add(comeBack);

        panel.add(panelMessage);
        panel.add(panelButton);

        this.buttonComeBack();

        frame.setSize(250, 175);
        frame.setLocation(800, 400);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void buttonComeBack() {
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Windows();
                frame.setVisible(false);
            }
        });
    }
}
