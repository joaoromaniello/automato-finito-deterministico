package com.afd;


import com.afd.repository.RuleRepository;
import com.afd.service.AutomatonService;
import com.afd.service.RuleService;
import com.afd.view.InitialView;

public class Main {

    public static void main(String[] args) throws Exception {

        new InitialView();

//        RuleRepository ruleRepository = new RuleRepository();
//        RuleService ruleService = new RuleService(ruleRepository);
//        AutomatonService automatonService = new AutomatonService(ruleService);
//        automatonService.metodoQueVaiFicarDentroDaTelaDeOutput(sequence, M);
//        System.out.println("\n\nCadeia: " + sequence + "\n\n");
//        System.out.println(ruleService.getCoveredRules(ruleRepository.coveredRules));
    }
}