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
    private JButton before = new JButton("<<<");
    private JButton next = new JButton(">>>");
    private JButton validate = new JButton("ENVIAR");
    private JButton toView = new JButton("VISUALIZAR");
    private JLabel text = new JLabel();


    private Automaton automaton;
    private RuleRepository ruleRepository;

    String sequence = null;
    List<String> sequenceList = new ArrayList<>();
    List<Rule> result;
    int aux = -1;
    int validateResult = 0;

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

        before.setBorder(BorderFactory.createEtchedBorder());
        validate.setBorder(BorderFactory.createEtchedBorder());
        next.setBorder(BorderFactory.createEtchedBorder());

        frame.add(before);
        frame.add(validate);
        frame.add(next);
        frame.add(toView);

        frame.add(text);
        text.setBounds(80, 10, 400, 30);
        frame.add(text);

        painel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));

        before.setBounds(-10 + offset, 120 + offset2, 60, 20);
        validate.setBounds(120 + offset, 110 + offset2, 80, 40);
        next.setBounds(260 + offset, 120 + offset2, 60, 20);
        toView.setBounds(250, 15, 90, 20);

        frame.add(painel);

        this.buttonToView();
        this.buttonBefore();
        this.buttoNext();
        this.buttonValidate();
    }


    public void buttonToView() {
        toView.addActionListener(new ActionListener() {
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

    public void buttonBefore() {
        before.addActionListener(new ActionListener() {
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

    public void buttoNext() {
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aux != (result.size() - 1) && aux != -1){
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

    public void buttonValidate() {
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
                if(validateResult == 1){
                    JOptionPane.showMessageDialog(null, "Pertence", "Info", JOptionPane.INFORMATION_MESSAGE);
                }else if (validateResult == -1) {
                    JOptionPane.showMessageDialog(null, "Não pertence", "Info", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void result() {

        RuleService ruleService = new RuleService(ruleRepository);
        AutomatonService automatonService = new AutomatonService(ruleService);

        try {
            validateResult = automatonService.metodoQueVaiFicarDentroDaTelaDeOutput(sequence, automaton);
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
            painel.remove(0);
            painel.repaint();
        }

        validateResult = 0;
        sequence = null;
        sequenceList = new ArrayList<>();
        result = null;
        ruleRepository.coveredRules = new ArrayList<>();
        aux = -1;
    }

}
