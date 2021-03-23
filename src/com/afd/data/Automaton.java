package com.afd.data;

import java.util.HashSet;
import java.util.Set;


public class Automaton {
    private final Set<String> states;
    private final Set<String> alphabet;
    private final Set<Rule> rules;
    private final String initialState;
    private final Set<String> acceptanceStates;

    public Automaton(Set<String> states, Set<String> alphabet, Set<Rule> rules, String initialState, Set<String> acceptanceStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.rules = rules;
        this.initialState = initialState;
        this.acceptanceStates = acceptanceStates;

    }

    public static Automaton buildDefaultAutomaton() {
        Set<String> states = new HashSet<>();
        states.add("q0");
        states.add("q1");
        states.add("q2");

        Set<String> alphabet = new HashSet<>();
        alphabet.add("0");
        alphabet.add("1");

        Set<Rule> rules = new HashSet<>();
        Rule rule1 = new Rule("q0", '0', "q1");
        Rule rule2 = new Rule("q0", '1', "q0");
        Rule rule3 = new Rule("q1", '0', "q1");
        Rule rule4 = new Rule("q1", '1', "q2");
        Rule rule5 = new Rule("q2", '0', "q2");
        Rule rule6 = new Rule("q2", '1', "q2");
        rules.add(rule1);rules.add(rule2);rules.add(rule3);rules.add(rule4);rules.add(rule5);rules.add(rule6);

        String initialState = "q0";

        Set<String> finalStates = new HashSet<>();
        finalStates.add("q2");

        return new Automaton(states, alphabet, rules, initialState, finalStates);
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public String getInitialState() {
        return initialState;
    }

    public Set<String> getAcceptanceStates() {
        return acceptanceStates;
    }


}
