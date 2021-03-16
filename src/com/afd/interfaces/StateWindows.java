package com.afd.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StateWindows extends ListWindows {

    public StateWindows() {
        super();
        this.messageAddButton = "Adicione um estado:";
    }

    @Override
    public void sendButton(){
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(texts);
                new SuccessWindows();
            }
        });
    }
}
