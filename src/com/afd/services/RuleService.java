package com.afd.services;

import com.afd.data.Rule;
import com.afd.repository.RuleRepository;

import java.util.List;
import java.util.Set;

public class RuleService {
    RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Rule getApplicableRule(Set<Rule> rules, String currentState, char currentSymbol) throws Exception {
        return rules.stream()
                .filter(rule -> isRuleApplicable(rule, currentState, currentSymbol))
                .findFirst()
                .orElseThrow(() -> new Exception("Regra não encontrada"));
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
