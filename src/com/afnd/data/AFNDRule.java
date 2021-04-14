package com.afnd.data;

import java.util.List;

public class AFNDRule {
    private final String sourceState;
    private final char symbol;
    private final List<String> targetState;

    public AFNDRule(String sourceState, char symbol, List<String> targetState) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
    }

    public String getSourceState() {
        return sourceState;
    }

    public char getSymbol() {
        return symbol;
    }

    public List<String> getTargetStates() {
        return targetState;
    }
}