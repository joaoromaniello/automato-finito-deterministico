package com.afd.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListWindows {

    private JFrame frame = new JFrame();
    private ArrayList<Object> elements = new ArrayList<Object>();
    protected ArrayList<String> texts = new ArrayList<String>();
    private ListPanel listPanel = new ListPanel(elements, texts);
    private JButton addButton;
    private JButton deleteButton;
    protected JButton sendButton;

    protected String messageAddButton;

    public ListWindows() {

        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        deleteButton = new JButton("Remove");
        sendButton = new JButton("Send");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sendButton);

        this.buttonAdd();
        this.buttonRemove();
        this.sendButton();

        frame.add(listPanel);
        frame.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public void sendButton(){
    }

    public void buttonAdd(){

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JTextField elementField = new JTextField(5);

                JPanel panel = new JPanel();
                panel.add(new JLabel(messageAddButton));
                panel.add(elementField);
                panel.add(Box.createHorizontalStrut(15));

                int result = JOptionPane.showConfirmDialog(frame, panel, "",
                        JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    listPanel.addElementToList(elementField.getText(), elementField.getText());
                }
            }
        });
    }

    public void buttonRemove(){

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listPanel.getList().getSelectedValue() != null) {
                    listPanel.removeElementFromList(listPanel.getList().getSelectedValue());
                }
            }
        });
    }
}

