package com.afd.service;

import com.afd.data.Automaton;
import com.afd.data.Rule;
import com.afd.repository.RuleRepository;

import java.util.List;

public class AutomatonService {

    RuleService ruleService;

    public AutomatonService(RuleRepository ruleRepository) {
        this.ruleService = new RuleService(ruleRepository);
    }

    public boolean belongsToLanguage(String sequence, Automaton M) throws Exception {
        String endState = processSequence(sequence, M.getInitialState(), M.getRules());
        return isAcceptableState(endState, M.getFinalStates());
    }

    private String processSequence(String sequence, String initialState, List<Rule> rules) throws Exception {
        String currentState = initialState;
        for (char currentSymbol : sequence.toCharArray()) {
            Rule applicableRule = ruleService.getApplicableRule(rules, currentState, currentSymbol);
            ruleService.addCoveredRule(applicableRule);
            currentState = ruleService.applyRule(applicableRule);
        }
        return currentState;
    }

    private boolean isAcceptableState(String state, List<String> acceptableStates) {
        return acceptableStates.contains(state);
    }

    public void validadeAutomaton(Automaton automaton) throws Exception {
        List<String> states = automaton.getStates();
        List<String> finalStates = automaton.getFinalStates();
        String initialState = automaton.getInitialState();
        String alphabet = automaton.getAlphabet();
        List<Rule> rules = automaton.getRules();

        validateInitialStates(states, initialState);
        validateFinalStates(states, finalStates);
        validateRules(rules, states, alphabet);
        validateDeterminism(states, alphabet, rules);
    }

    private void validateInitialStates(List<String> states, String initialState) throws Exception {
        if(!states.contains(initialState))
            throw new Exception("Estado inicial deve pertencer ao conjunto de estados!");
    }

    private void validateFinalStates(List<String> states, List<String> finalStates) throws Exception {
        if (finalStates.isEmpty())
            throw new Exception("Deve haver pelo menos um estado final");
        for (String finalState : finalStates) {
            if (!states.contains(finalState))
                throw new Exception("Estados finais devem pertencer ao conjunto de estados!");
        }
    }

    private void validateRules(List<Rule> rules, List<String> states, String alphabet) throws Exception {
        for (Rule rule: rules) {
            if(!states.contains(rule.getTargetState()) || !states.contains(rule.getSourceState()))
                throw new Exception("Estados das regras devem pertencer ao conjunto de estados!");
            if(alphabet.indexOf(rule.getSymbol()) == -1)
                throw new Exception("Símbolos das regras devem pertencer ao alfabeto!");
        }
    }

    private void validateDeterminism(List<String> states, String alphabet, List<Rule> rules) throws Exception {
        for (String state : states) {
            for (int i = 0; i < alphabet.length(); i++) {
                int counter = ruleService.countApplicableRules(rules, state, alphabet.charAt(i));
                if (counter > 1) {
                    throw new Exception("AFD deve conter apenas uma função de transição a cada Estado x Símbolo!");
                } else if (counter == 0)
                    throw new Exception("AFD deve conter pelo menos uma função de transição a cada Estado x Símbolo!");
            }
        }
    }

    public void validateSequence(String alphabet, String sequence) throws Exception {
        for (int i = 0; i < sequence.length(); i++) {
            boolean found = false;
            for (int j = 0; j < alphabet.length(); j++) {
                if (sequence.charAt(i) == alphabet.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Elementos da cadeia devem pertencer ao alphabeto!");
            }
        }
    }
}
