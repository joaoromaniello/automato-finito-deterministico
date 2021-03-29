package com.afd;


import com.afd.repository.RuleRepository;
import com.afd.view.input.InitialView;

public class Main {

    public static void main(String[] args) throws Exception {

        RuleRepository ruleRepository = new RuleRepository();
        new InitialView(ruleRepository);

//        RuleService ruleService = new RuleService(ruleRepository);
//        AutomatonService automatonService = new AutomatonService(ruleService);
//        automatonService.metodoQueVaiFicarDentroDaTelaDeOutput(sequence, M);
//        System.out.println("\n\nCadeia: " + sequence + "\n\n");
//        System.out.println(ruleService.getCoveredRules(ruleRepository.coveredRules));
    }
}