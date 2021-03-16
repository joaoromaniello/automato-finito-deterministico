package com.afd;

import com.afd.data.Automaton;
import com.afd.data.Run;


public class Main {

    public static void main(String[] args) throws Exception {
        Automaton M = Automaton.buildDefaultAutomaton();
        String sequence = "1011";

        String endState = Run.processSequence(sequence, M.getInitialState(), M.getRules());

        if (Run.acceptableState(endState, M.getAcceptanceStates())) {
            System.out.println("DEU BÃO");
        } else {
            System.out.println("DEU RUIM");
        }
    }
}