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

    private final Automaton automaton;
    private final RuleService ruleService;
    private final AutomatonService automatonService;

    String sequence = null;
    List<String> sequenceList = new ArrayList<>();
    List<Rule> result;
    int aux = -1;
    int validateResult = 0;

    public StepView(RuleRepository ruleRepository, Automaton automaton) {
        this.ruleService = new RuleService(ruleRepository);
        this.automatonService = new AutomatonService(ruleRepository);
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

    private void enableOutputButtons(boolean flag) {
        beforeButton.setEnabled(flag);
        finishButton.setEnabled(flag);
        nextButton.setEnabled(flag);
    }

    public void sendButtonAction() {
        sendButton.addActionListener(e -> {
            enableOutputButtons(true);
            clean();

            sequence = textField.getText();
            aux = 0;

            result();

            panel.add(resultPanel(result));
            panel.repaint();
        });
    }

    private void result() {
        try {
            validateResult = automatonService.belongsToLanguage(sequence, automaton);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        result = ruleService.getCoveredRules();
    }

    public void beforeButtonAction() {
        beforeButton.addActionListener(e -> {
            if(aux != 0 && aux != -1){
                aux = aux - 1;
                panel.remove(0);
                panel.add(resultPanel(result));
            }
            panel.repaint();
        });
    }

    public void nextButtonAction() {
        nextButton.addActionListener(e -> {
            if(aux != (result.size() - 1) && aux != -1){
                aux = aux + 1;
                panel.remove(0);
                panel.add(resultPanel(result));
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

    private JPanel resultPanel(List<Rule> rules) {

        JPanel panel = new JPanel();

        JTextField sequenceLabel = new JTextField();
        JLabel currentStateLabel = new JLabel();
        JLabel ruleLabel = new JLabel();
        JLabel acceptableStatesLabel = new JLabel();

        sequenceLabel.setBounds(10, 10, 345, 25);
        sequenceLabel.setBorder(BorderFactory.createLineBorder(Color.white));

        currentStateLabel.setBounds(10, 50, 345, 20);
        acceptableStatesLabel.setBounds(10, 70, 345, 20);

        if (rules.isEmpty()) {
            sequenceLabel.setText("Cadeia vazia");
            sequenceLabel.setFont(new Font(null, Font.PLAIN, 15));
            currentStateLabel.setText("Estado inicial: " + automaton.getInitialState());
            currentStateLabel.setForeground(setColorByAcceptance(automaton.getInitialState()));
        } else {
            Rule rule = rules.get(aux);

            String sequenceWithBrackets = getSequenceWithBrackets();
            sequenceLabel.setFont(new Font(null, Font.PLAIN, 25));
            sequenceLabel.setText(sequenceWithBrackets);
            sequenceLabel.setCaretPosition(aux+3);

            currentStateLabel.setForeground(setColorByAcceptance(rule.getTargetState()));
            currentStateLabel.setText("Estado atual: " + rule.getTargetState());

            ruleLabel.setBounds(140, 110, 100, 20);
            ruleLabel.setText("{"
                    + rule.getSourceState() + ", "
                    + rule.getSymbol() + " ,"
                    + rule.getTargetState() + "}");
            panel.add(ruleLabel);
        }
        acceptableStatesLabel.setText("Estados de aceitação: " + automaton.getFinalStates());

        panel.add(sequenceLabel);
        panel.add(currentStateLabel);
        panel.add(acceptableStatesLabel);

        panel.setBounds(1, 1, 345, 145);
        panel.setBackground(new Color(255, 255, 255));

        return panel;
    }

    private Color setColorByAcceptance(String state) {
        if (automaton.getFinalStates().contains(state))
            return new Color(0,200,0);
        else
            return new Color(200,0,0);
    }

    private String getSequenceWithBrackets(){
        return sequence.substring(0, aux) +
                "[" + sequence.charAt(aux) + "]"
                + sequence.substring(aux+1);
    }

    private void clean(){

        if(aux != -1){
            panel.remove(0);
            panel.repaint();
        }

        validateResult = 0;
        sequence = null;
        sequenceList = new ArrayList<>();
        result = null;
        ruleService.cleanCoveredRules();
        aux = -1;
    }
}
