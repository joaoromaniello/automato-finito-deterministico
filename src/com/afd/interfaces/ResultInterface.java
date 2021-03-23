package com.afd.interfaces;

import javax.swing.*;
import java.awt.*;
import com.afd.Main;

public class ResultInterface {



    public static int offset = 40;
    public static int offset2 = 100;
    private JTextField  palavra= new JTextField(10);

    private JFrame frame = new JFrame("RESULTADO");
    private JPanel painel = new JPanel();
    private JButton button1 = new JButton("<<<");
    private JButton button2 = new JButton("FINALIZAR");
    private JButton button3 = new JButton(">>>");
    private JButton button4 = new JButton("VALIDAR");
    private JLabel text = new JLabel();



    ResultInterface(){
          this.frame.add(palavra);
          this.frame.setVisible(true);
          this.frame.setResizable(false);
          this.frame.setSize(400,300);
          this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.frame.setLayout(null);
          this.frame.setBackground(new Color(0,0,128));
          this.text.setText("PALAVRA: ");
          this.palavra = new JTextField();
          this.frame.add(palavra);
          this.palavra.setBounds(145,15,100,20);
          this.painel.setBounds(17,50,350,150);
          this.painel.setBackground(new Color(255,255,255));
          this.painel.setLayout(null);


          this.button1.setBorder(BorderFactory.createEtchedBorder());
          this.button2.setBorder(BorderFactory.createEtchedBorder());
          this.button3.setBorder(BorderFactory.createEtchedBorder());

          this.frame.add(button1);
          this.frame.add(button2);
          this.frame.add(button3);
          this.frame.add(button4);

          this.frame.add(text);
          this.text.setBounds(80,10,400,30);
          this.painel.setBorder(BorderFactory.createLineBorder(new Color(0,0,128)));

        this.button1.setBounds(-10 + offset,70 + offset2,60,20);
        this.button2.setBounds(120 + offset,110 + offset2,80,40);
        this.button3.setBounds(260+ offset,70 + offset2,60,20);
        this.button4.setBounds(250 ,15 ,90,20);

        this.frame.add(painel);




    }

}
