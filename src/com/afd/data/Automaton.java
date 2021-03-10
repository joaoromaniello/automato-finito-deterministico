package com.afd.data;

import java.util.Set;

public class Automaton {
    private Set<String> states;
    private Set<String> alphabet;
    private Set<Rule> rules;
    private String initialState;
    private Set<String> finalStates;
}
