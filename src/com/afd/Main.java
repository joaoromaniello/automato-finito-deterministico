package com.afd;


import com.afd.repository.RuleRepository;
import com.afd.view.InitialView;

public class Main {

    public static void main(String[] args) {

        RuleRepository ruleRepository = new RuleRepository();
        new InitialView(ruleRepository);

    }
}