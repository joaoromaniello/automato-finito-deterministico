package com.afnd.service;

import com.afnd.data.AFDAutomaton;
import com.afnd.data.AFNDAutomaton;

import java.util.ArrayList;

public class AFNDConverter {
    static AFDAutomaton mapFrom(AFNDAutomaton afnd) {
        return new AFDAutomaton(
                new ArrayList<>(),
                "alphabet",
                new ArrayList<>(),
                "initialState",
                new ArrayList<>()
        );
    }
}
