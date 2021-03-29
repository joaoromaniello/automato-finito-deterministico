package com.afd.view.output;

import com.afd.data.Automaton;
import com.afd.data.Rule;
import com.afd.repository.RuleRepository;
import com.afd.service.RuleService;
import com.afd.services.AutomatonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StepView {

    public static int offset = 40;
    public static int offset2 = 100;
    private JTextField palavra = new JTextField(10);

    private JFrame frame = new JFrame("RESULTADO");
    private JPanel painel = new JPanel();
    private JButton button1 = new JButton("<<<");
    private JButton button2 = new JButton("FINALIZAR");
    private JButton button3 = new JButton(">>>");
    private JButton button4 = new JButton("VALIDAR");
    private JLabel text = new JLabel();


    private Automaton automaton;
    private RuleRepository ruleRepository;

    String sequence = null;
    List<String> sequenceList = new ArrayList<>();
    List<Rule> result;
    int aux = -1;

    public StepView(RuleRepository ruleRepository, Automaton automaton) {

        this.ruleRepository = ruleRepository;
        this.automaton = automaton;

        frame.add(palavra);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        text.setText("PALAVRA: ");

        palavra = new JTextField();
        frame.add(palavra);
        palavra.setBounds(145, 15, 100, 20);

        painel.setBounds(17, 50, 350, 150);
        painel.setBackground(new Color(255, 255, 255));

        button1.setBorder(BorderFactory.createEtchedBorder());
        button2.setBorder(BorderFactory.createEtchedBorder());
        button3.setBorder(BorderFactory.createEtchedBorder());

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

        frame.add(text);
        text.setBounds(80, 10, 400, 30);
        frame.add(text);

        painel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));

        button1.setBounds(-10 + offset, 70 + offset2, 60, 20);
        button2.setBounds(120 + offset, 110 + offset2, 80, 40);
        button3.setBounds(260 + offset, 70 + offset2, 60, 20);
        button4.setBounds(250, 15, 90, 20);

        frame.add(painel);

        this.buttonResult();
        this.buttonbutton1();
        this.buttonbutton3();
    }


    public void buttonResult() {
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clean();
                sequence = palavra.getText();

                aux = 0;
                sequenceMethod(sequence);
                result();

                System.out.println(result.get(aux).toString(aux));

                painel.add(resultPanel(result.get(aux)));
                painel.repaint();
            }
        });
    }

    public void buttonbutton1() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aux != 0 && aux != -1){
                    aux = aux - 1;
                    System.out.println(result.get(aux).toString(aux));

                    painel.remove(0);
                    painel.add(resultPanel(result.get(aux)));
                }
                painel.repaint();
            }
        });
    }

    public void buttonbutton3() {
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aux != result.size() && aux != -1){
                    aux = aux + 1;
                    System.out.println(result.get(aux).toString(aux));

                    System.out.println("AUX: " + aux);

                    painel.remove(0);
                    painel.add(resultPanel(result.get(aux)));
                }
                painel.repaint();
            }
        });
    }

    private void result() {

        RuleService ruleService = new RuleService(ruleRepository);
        AutomatonService automatonService = new AutomatonService(ruleService);

        try {
            automatonService.metodoQueVaiFicarDentroDaTelaDeOutput(sequence, automaton);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        result = ruleRepository.coveredRules;
    }

    private JPanel resultPanel(Rule rule) {

        JPanel panel = new JPanel();

        JLabel header = new JLabel();
        JLabel stateActual = new JLabel();
        JLabel symbolActual = new JLabel();
        JLabel ruleLabel = new JLabel();
        JLabel nextState = new JLabel();
        JLabel sequence = new JLabel();


        sequence.setText("Palavra: " + sequencePosition());
        sequence.setBounds(10, 0, 345, 20);

        header.setText("------- Passo " + aux + " -------");
        header.setBounds(0, 20, 345, 20);

        stateActual.setText("Estado atual: " + rule.getSourceState());
        stateActual.setBounds(0, 40, 345, 20);

        symbolActual.setText("Símbolo atual: " + rule.getSymbol());
        symbolActual.setBounds(0, 60, 345, 20);

        ruleLabel.setText("Regra: {" + rule.getSourceState() + ", " + rule.getSymbol() + " ," + rule.getTargetState() + "}");
        ruleLabel.setBounds(0, 80, 345, 20);

        nextState.setText("Próximo estado: " + rule.getTargetState());
        nextState.setBounds(0, 100, 345, 20);

        panel.add(sequence);
        panel.add(header);
        panel.add(stateActual);
        panel.add(symbolActual);
        panel.add(ruleLabel);
        panel.add(nextState);

        System.out.println("passou aqui");
        panel.setBounds(1, 1, 345, 120);
        panel.setBackground(new Color(255, 255, 255));

        return panel;

    }

    private void sequenceMethod(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            sequenceList.add(sequence.substring(i,i+1));
        }
    }

    private String sequencePosition(){

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sequence.length(); i++){
            if(i != aux) {
                result.append(sequenceList.get(i));
            }else{
                result.append("[").append(sequenceList.get(i)).append("]");
            }
        }

        return result.toString();
    }


    private void clean(){

        if(aux != -1){
            JPanel panel = new JPanel();
            panel.setBounds(1, 1, 345, 120);
            panel.setBackground(new Color(255, 255, 255));
            painel.remove(0);
            painel.add(panel);
            painel.repaint();
        }
        sequence = null;
        sequenceList = new ArrayList<>();
        result = null;
        ruleRepository.coveredRules = new ArrayList<>();
        aux = -1;
    }

}
