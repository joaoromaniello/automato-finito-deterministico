package com.afd.view.output;

import com.afd.data.Automaton;
import com.afd.data.Rule;
import com.afd.repository.RuleRepository;
import com.afd.service.AutomatonService;
import com.afd.service.RuleService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StepView extends JFrame{

    private final JTextField textField;

    private final JPanel panel = new JPanel();
    private final JButton beforeButton = new JButton("<<<");
    private final JButton nextButton = new JButton(">>>");
    private final JButton finishButton = new JButton("FINALIZAR");
    private final JButton sendButton = new JButton("ENVIAR");

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

        setVisible(true);
        setResizable(false);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel text = new JLabel("CADEIA:");
        text.setBounds(20, 10, 55, 30);
        add(text);

        textField = new JTextField();
        textField.setBounds(78, 15, 205, 20);
        add(textField);

        sendButton.setBounds(290, 15, 90, 20);
        add(sendButton);

        panel.setBounds(20, 50, 360, 150);
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        panel.setBackground(new Color(255, 255, 255));
        add(panel);

        beforeButton.setBorder(BorderFactory.createEtchedBorder());
        beforeButton.setBounds(20, 220, 60, 20);
        add(beforeButton);

        finishButton.setBorder(BorderFactory.createEtchedBorder());
        finishButton.setBounds(160, 210, 80, 40);
        add(finishButton);

        nextButton.setBorder(BorderFactory.createEtchedBorder());
        nextButton.setBounds(320, 220, 60, 20);
        add(nextButton);

        enableOutputButtons(false);

        this.sendButtonAction();
        this.beforeButtonAction();
        this.nextButtonAction();
        this.finishButtonAction();
    }


    public void sendButtonAction() {
        sendButton.addActionListener(e -> {
            enableOutputButtons(true);
            clean();

            sequence = textField.getText();
            aux = 0;

            sequenceMethod(sequence);
            result();

            panel.add(resultPanel(result.get(aux)));
            panel.repaint();
        });
    }

    public void beforeButtonAction() {
        beforeButton.addActionListener(e -> {
            if(aux != 0 && aux != -1){
                aux = aux - 1;
                panel.remove(0);
                panel.add(resultPanel(result.get(aux)));
            }
            panel.repaint();
        });
    }

    public void nextButtonAction() {
        nextButton.addActionListener(e -> {
            if(aux != (result.size() - 1) && aux != -1){
                aux = aux + 1;
                panel.remove(0);
                panel.add(resultPanel(result.get(aux)));
            }
            panel.repaint();
        });
    }

    public void finishButtonAction() {
        finishButton.addActionListener(e -> {
            if(validateResult == 1){
                JOptionPane.showMessageDialog(null, "Pertence", "Info", JOptionPane.INFORMATION_MESSAGE);
            }else if (validateResult == -1) {
                JOptionPane.showMessageDialog(null, "Não pertence", "Info", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            clean();
            enableOutputButtons(false);
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
            panel.remove(0);
            panel.repaint();
            textField.setText("");
        }

        validateResult = 0;
        sequence = null;
        sequenceList = new ArrayList<>();
        result = null;
        ruleRepository.coveredRules = new ArrayList<>();
        aux = -1;
    }

    private void enableOutputButtons(boolean flag) {
        beforeButton.setEnabled(flag);
        finishButton.setEnabled(flag);
        nextButton.setEnabled(flag);
    }

}
