package com.afd.interfaces;

import javax.swing.*;
import java.awt.*;

public class resultInterface {

    private JFrame frame = new JFrame();
    private JPanel painel = new JPanel();
    private JButton button1 = new JButton("VOLTAR");
    private JButton button2 = new JButton("VALIDAR");
    private JButton button3 = new JButton("LIMPAR");
    private JLabel text = new JLabel();

    resultInterface(){
          this.frame.setVisible(true);
          this.frame.setSize(400,200);
          this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.frame.setLayout(new GridLayout(1,3,10,10));


          this.painel.setBounds(150,150,200,200);
          this.painel.setBackground(Color.gray);
          this.painel.setLayout(new FlowLayout());

          this.button1.setBounds(40,30,250,250);
          this.button2.setSize(80,60);
          this.button3.setSize(40,30);

          this.button1.setBorder(BorderFactory.createEtchedBorder());
          this.button2.setBorder(BorderFactory.createEtchedBorder());
          this.button3.setBorder(BorderFactory.createEtchedBorder());

          this.painel.add(button1);
          this.painel.add(button2);
          this.painel.add(button3);

          this.frame.add(painel);




    }

}
