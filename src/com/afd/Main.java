package com.afd;

import com.afd.data.Automaton;
import com.afd.data.Rule;

public class Main {
    public static void main(String[] args) throws Exception {
        Automaton M = Automaton.buildDefaultAutomaton();
        String sequence = "01a";

        String currentState = M.getInitialState();
        boolean isValidSymbol = false;
        for (int currentPosition = 0; currentPosition < sequence.length(); currentPosition++) {
            isValidSymbol = false;
            for (Rule rule : M.getRules()) {
                if (rule.getSourceState().equals(currentState) && rule.getSymbol() == sequence.charAt(currentPosition)) {
                    currentState = rule.getTargetState();
                    isValidSymbol = true;
                    break;
                }
            }
            if (!isValidSymbol) {
                throw new Exception("Símbolo não está no alfabeto");
            }
        }

        if (M.getFinalStates().contains(currentState)) {
            System.out.println("DEU BÃO");
        } else {
            System.out.println("DEU RUIM");
        }
    }


}