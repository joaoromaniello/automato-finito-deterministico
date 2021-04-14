package com.afnd.service;

import com.afnd.data.Automaton;
import com.afnd.data.Rule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.rmi.transport.ObjectTable;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileService {

    JSONArray jsonArray;
    JSONObject jsonField;

    public Automaton translateAutomaton() throws Exception {
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(workingDirectory);

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

        jsonArray = (JSONArray) jsonField.get("estados");
        List<String> states = parseArrayField(jsonArray);

        String alphabet = parseAlphabet();

        List<Rule> rules = parseRules();

        String initialState = parseInitialState();

        jsonArray = (JSONArray) jsonField.get("estadosFinais");
        List<String> finalStates = parseArrayField(jsonArray);

        return new Automaton(states, alphabet, rules, initialState, finalStates);
    }

    private List<String> parseArrayField(JSONArray jsonArray) {
        List<String> array = new ArrayList<>();
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
        for (Object rule : jsonArray) {
            JSONObject jsonRule = (JSONObject) rule;

            String sourceState = (String) jsonRule.get("estadoPartida");

            String symbol = (String) jsonRule.get("simbolo");

            JSONArray targets = (JSONArray) jsonRule.get("estadosDestino");
            List<String> targetStates = parseArrayField(targets);

            rules.add(new Rule(sourceState, symbol.charAt(0), targetStates));
        }
        return rules;
    }

    private String parseInitialState() {
        return (String) jsonField.get("estadoInicial");
    }
}

