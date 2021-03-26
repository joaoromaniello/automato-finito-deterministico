package com.afd.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import static javax.swing.SwingConstants.CENTER;

public class InitialView extends JFrame implements ActionListener {

    JLabel titleLabel = new JLabel("Autômato Finito Determinístico");
    JButton openFileButton = new JButton("Arquivo...");

    public InitialView(){
        setupFrame();
        setupTitle();
        setupButton();
    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
    }

    private void setupTitle() {
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 500, 20);
        add(titleLabel);
    }

    private void setupButton() {
        openFileButton.setBounds(190,120 ,120,40);
        openFileButton.addActionListener(this);
        add(openFileButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == openFileButton) {
            File file;
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                System.out.println(file);

            }


        }
    }
}
