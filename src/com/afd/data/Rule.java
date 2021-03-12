package com.afd.data;

public class Rule {
    private final String sourceState;
    private final char symbol;
    private final String targetState;

    public Rule(String sourceState, char symbol, String targetState) {
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

    public String getTargetState() {
        return targetState;
    }


}