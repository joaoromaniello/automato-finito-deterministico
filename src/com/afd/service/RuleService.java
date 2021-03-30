package com.afd.service;

import com.afd.data.Rule;
import com.afd.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

public class RuleService {
    RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Rule getApplicableRule(List<Rule> rules, String currentState, char currentSymbol) throws Exception {
        return rules.stream()
                .filter(rule -> isRuleApplicable(rule, currentState, currentSymbol))
                .findFirst()
                .orElseThrow(() -> new Exception("Regra não encontrada"));
    }

    private boolean isRuleApplicable(Rule rule, String currentState, char currentSymbol) {
        return rule.getSourceState().equals(currentState) && rule.getSymbol() == currentSymbol;
    }

    public int countApplicableRules(List<Rule> rules, String currentState, char currentSymbol) {
        return (int) rules.stream()
                .filter(rule -> isRuleApplicable(rule, currentState, currentSymbol))
                .count();
    }

    public void addCoveredRule(Rule applicableRule) {
        ruleRepository.coveredRules.add(applicableRule);
    }

    public String applyRule(Rule applicableRule) {
        return applicableRule.getTargetState();
    }


    public List<Rule> getCoveredRules() {
        return ruleRepository.coveredRules;
    }

    public void cleanCoveredRules() {
        ruleRepository.coveredRules = new ArrayList<>();
    }
}
