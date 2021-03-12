package com.afd;

import com.afd.data.Automaton;
import com.afd.data.Rule;
import java.util.Scanner;


import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Automaton M = Automaton.buildDefaultAutomaton();
        Scanner scan = new Scanner (System.in);
        while(1==1){
            System.out.println("Digte sua palavra -->");
            String sequence = scan.nextLine();
            String endState = processSequence(sequence, M.getInitialState(), M.getRules());

            if (acceptableState(endState, M.getAcceptanceStates())) {
                System.out.println("DEU BÃO");
            }
            else {
                System.out.println("DEU RUIM");
            }}
     }

    private static String processSequence(String sequence, String initialState, Set<Rule> rules) throws Exception {
        String currentState = initialState;
        for (char currentSymbol : sequence.toCharArray()) {
            Rule applicableRule = searchRule(rules, currentState, currentSymbol);
            currentState = applyRule(applicableRule);
        }
        return currentState;
    }

    private static Rule searchRule(Set<Rule> rules, String currentState, char currentSymbol) throws Exception {
        return rules.stream()
                .filter(rule -> isRuleApplicable(rule, currentState, currentSymbol))
                .findFirst()
                .orElseThrow(() -> new Exception("Regra não encontrada"));
    }

    private static boolean isRuleApplicable(Rule rule, String currentState, char currentSymbol) {
        return rule.getSourceState().equals(currentState) && rule.getSymbol() == currentSymbol;
    }

    private static String applyRule(Rule applicableRule) {
        return applicableRule.getTargetState();
    }

    private static boolean acceptableState(String state, Set<String> acceptableStates) {
        return acceptableStates.contains(state);
    }
}