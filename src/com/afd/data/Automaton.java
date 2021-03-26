package com.afd.data;

import java.util.HashSet;
import java.util.Set;


public class Automaton {
    private final Set<String> states;
    private final Set<Character> alphabet;
    private final Set<Rule> rules;
    private final String initialState;
    private final Set<String> finalStates;

    public Automaton(Set<String> states, Set<Character> alphabet, Set<Rule> rules, String initialState, Set<String> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.rules = rules;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public String getInitialState() {
        return initialState;
    }

    public Set<String> getFinalStates() {
        return finalStates;
    }


}
