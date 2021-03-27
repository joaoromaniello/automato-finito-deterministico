package com.afd.service;

import com.afd.data.Automaton;
import com.afd.data.Rule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileService {

    JSONArray jsonArray;
    JSONObject jsonField;

    public Automaton translateAutomaton() throws Exception {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                return parseFile(fileChooser.getSelectedFile().getAbsolutePath());
            } catch (IOException | ParseException | NullPointerException e) {
                throw new Exception("Arquivo inválido!");
            }
        }
        return null;
    }

    private Automaton parseFile(String absolutePath) throws IOException, ParseException {
        jsonField = (JSONObject) new JSONParser().parse(new FileReader(absolutePath));

        List<String> states = parseArrayField("estados");
        String alphabet = parseAlphabet();
        List<Rule> rules = parseRules();
        String initialState = parseInitialState();
        List<String> finalStates = parseArrayField("estadosFinais");

        return new Automaton(states, alphabet, rules, initialState, finalStates);

    }

    private List<String> parseArrayField(String field) {
        List<String> array = new ArrayList<>();
        jsonArray = (JSONArray) jsonField.get(field);
        for (Object o : jsonArray) {
            array.add((String) o);
        }
        return array;
    }

    private String parseAlphabet() {
        return (String) jsonField.get("alfabeto");
    }

    private List<Rule> parseRules() {
        List<Rule> rules = new ArrayList<>();
        jsonArray = (JSONArray) jsonField.get("regras");
        for (Object o : jsonArray) {
            JSONObject jsonRule = (JSONObject) o;

            String sourceState = (String) jsonRule.get("estadoPartida");
            String symbol = (String) jsonRule.get("simbolo");
            String targetState = (String) jsonRule.get("estadoDestino");
            rules.add(new Rule(sourceState, symbol.charAt(0), targetState));
        }
        return rules;
    }

    private String parseInitialState() {
        return (String) jsonField.get("estadoInicial");
    }
}

