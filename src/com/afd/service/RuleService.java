package com.afd.service;

import com.afd.data.Rule;
import com.afd.repository.RuleRepository;

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

    public int countApplicableRules(List<Rule> rules, String currentState, char currentSymbol) {
        return (int) rules.stream()
                .filter(rule -> isRuleApplicable(rule, currentState, currentSymbol))
                .count();
    }

    private boolean isRuleApplicable(Rule rule, String currentState, char currentSymbol) {
        return rule.getSourceState().equals(currentState) && rule.getSymbol() == currentSymbol;
    }

    public void addCoveredRule(Rule applicableRule) {
        ruleRepository.coveredRules.add(applicableRule);
    }

    public String applyRule(Rule applicableRule) {
        return applicableRule.getTargetState();
    }

    public String getCoveredRules(List<Rule> coveredRules) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coveredRules.size(); i++) {
            result.append(coveredRules.get(i).toString(i));
        }
        return result.toString();
    }
}
