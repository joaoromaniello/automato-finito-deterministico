package com.afd.services;

import com.afd.data.Automaton;
import com.afd.data.Rule;

import java.util.Set;

public class AutomatonService {

    RuleService ruleService;

    public AutomatonService(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    public void metodoQueVaiFicarDentroDaTelaDeOutput(String sequence, Automaton M) throws Exception {
        //TODO this.automatonService.processSequence......
        String endState = processSequence(sequence, M.getInitialState(), M.getRules());
        if (isAcceptableState(endState, M.getFinalStates())) {
            System.out.println("JOptionalPane de sucesso falando que pertence");
        } else {
            System.out.println("JOptionalPane de falha falando que nao pertence");
        }
    }

    private String processSequence(String sequence, String initialState, Set<Rule> rules) throws Exception {
        String currentState = initialState;
        for (char currentSymbol : sequence.toCharArray()) {
            Rule applicableRule = ruleService.getApplicableRule(rules, currentState, currentSymbol);
            ruleService.addCoveredRule(applicableRule);
            currentState = ruleService.applyRule(applicableRule);
        }
        return currentState;
    }

    private boolean isAcceptableState(String state, Set<String> acceptableStates) {
        return acceptableStates.contains(state);
    }
}
