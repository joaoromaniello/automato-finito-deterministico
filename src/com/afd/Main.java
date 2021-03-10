package com.afd;

import com.afd.data.Automaton;
import com.afd.data.Rule;

public class Main {
    public static void main(String[] args) throws Exception {
        Automaton M = Automaton.buildDefaultAutomaton();
        String sequence = "101010101000";

        String currentState = M.getInitialState();
        for (int currentPosition = 0; currentPosition < sequence.length(); currentPosition++) {
            for (Rule rule : M.getRules()) {
                if (rule.getSourceState().equals(currentState) && rule.getSymbol() == sequence.charAt(currentPosition)) {
                    currentState = rule.getTargetState();
                    break;
                }
            }
        }

        if (M.getFinalStates().contains(currentState)) {
            System.out.println("DEU BÃO");
        } else {
            System.out.println("DEU RUIM");
        }
    }


}