package com.afd;

import com.afd.data.Automaton;
import com.afd.repository.RuleRepository;
import com.afd.services.AutomatonService;
import com.afd.services.RuleService;


public class Main {

    public static void main(String[] args) throws Exception {
        RuleRepository ruleRepository = new RuleRepository();

        // TODO esses bagulho vão ser chamados pelas telas de input que chama a de output
        Automaton M = Automaton.buildDefaultAutomaton();
        String sequence = "1001";

        // TODO esses bagulho vão ser chamados pela tela de output
        RuleService ruleService = new RuleService(ruleRepository);
        AutomatonService automatonService = new AutomatonService(ruleService);
        automatonService.metodoQueVaiFicarDentroDaTelaDeOutput(sequence, M);
        System.out.println("\n\nCadeia: " + sequence + "\n\n");
        System.out.println(ruleService.getCoveredRules(ruleRepository.coveredRules));
    }
}