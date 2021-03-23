package com.afd.data;

import java.util.HashSet;
import java.util.Set;

public class Run {
    public static String processSequence(String sequence, String initialState, Set<Rule> rules) throws Exception {
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

    public static boolean acceptableState(String state, Set<String> acceptableStates) {
        return acceptableStates.contains(state);
    }
}
