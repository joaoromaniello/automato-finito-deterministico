package com.afd.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindows {

    private final JButton send;
    private final JTextField textTitle;
    private final JPanel panelTitle = new JPanel();
    private final JPanel panelText = new JPanel();
    private final JPanel panelButton = new JPanel();
    private final JPanel panel = new JPanel();

    protected JFrame frame;


    public InputWindows() {

        panelTitle.setLayout(new GridLayout(1, 2));
        panelButton.setLayout(new GridLayout(1, 1));

        panel.setLayout(new GridLayout(2, 1));

        JLabel labelTitle = new JLabel("Digite sua cadeia de {0,1}:", SwingConstants.CENTER);
        textTitle = new JTextField(40);
        send = new JButton("Enviar");

        panelTitle.add(labelTitle);
        panelTitle.add(textTitle);

        panelButton.add(send);

        panel.add(panelTitle);
        panel.add(panelButton);

        this.buttonSend();
    }

    public void buttonSend() {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SucessWindows();
                frame.setVisible(false);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
