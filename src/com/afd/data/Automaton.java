package com.afd.data;

import java.util.List;


public class Automaton {
    private final List<String> states;
    private final String alphabet;
    private final List<Rule> rules;
    private final String initialState;
    private final List<String> finalStates;

    public Automaton(List<String> states, String alphabet, List<Rule> rules, String initialState, List<String> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.rules = rules;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public List<String> getStates() {
        return states;
    }

    public String getAlphabet() {
        return alphabet;
    }
}
