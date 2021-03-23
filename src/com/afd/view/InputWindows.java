package com.afd.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindows {

    private final JButton send;

    private final JTextField setOfStates;
    private final JTextField initialState;
    private final JTextField finalState;
    private final JTextField alphabet;
    private final JTextField setOfFunctions;

    private final JPanel panelSetOfStates = new JPanel();
    private final JPanel panelSetOfFunctions = new JPanel();
    private final JPanel panelInitialState= new JPanel();
    private final JPanel panelFinalState = new JPanel();
    private final JPanel panelAlphabet = new JPanel();
    private final JPanel panelButton = new JPanel();

    private final JPanel panel = new JPanel();

    protected JFrame frame;


    public InputWindows() {

        panelSetOfStates.setLayout(new GridLayout(1, 2));
        panelSetOfFunctions.setLayout(new GridLayout(1, 2));
        panelInitialState.setLayout(new GridLayout(1, 2));
        panelFinalState.setLayout(new GridLayout(1, 2));
        panelAlphabet.setLayout(new GridLayout(1, 2));

        panelButton.setLayout(new GridLayout(1, 1));

        panel.setLayout(new GridLayout(6, 1));

        JLabel labelSetOfStates = new JLabel("Conjunto de estados:", SwingConstants.CENTER);
        setOfStates = new JTextField(40);

        JLabel labelInitialState = new JLabel("Estado inicial:", SwingConstants.CENTER);
        initialState = new JTextField(40);

        JLabel labelFinalState = new JLabel("Estado final:", SwingConstants.CENTER);
        finalState = new JTextField(40);

        JLabel labelAlphabet = new JLabel("alfabeto:", SwingConstants.CENTER);
        alphabet = new JTextField(40);

        JLabel labelSetOfFunctions = new JLabel("Conjuto de funções:", SwingConstants.CENTER);
        setOfFunctions = new JTextField(40);

        send = new JButton("Enviar");

        panelSetOfStates.add(labelSetOfStates);
        panelSetOfStates.add(setOfStates);

        panelInitialState.add(labelInitialState);
        panelInitialState.add(initialState);

        panelFinalState.add(labelFinalState);
        panelFinalState.add(finalState);

        panelAlphabet.add(labelAlphabet);
        panelAlphabet.add(alphabet);

        panelSetOfFunctions.add(labelSetOfFunctions);
        panelSetOfFunctions.add(setOfFunctions);

        panelButton.add(send);

        panel.add(panelSetOfStates);
        panel.add(panelInitialState);
        panel.add(panelFinalState);
        panel.add(panelAlphabet);
        panel.add(panelSetOfFunctions);

        panel.add(panelButton);

        this.buttonSend();
    }

    public void buttonSend() {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SuccessWindows();
                frame.setVisible(false);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
