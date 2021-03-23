package com.afd.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListPanel extends JPanel {
    private JList<Object> list;
    private ArrayList<String> texts;

    public ListPanel(ArrayList<Object> elements, ArrayList<String> texts) {

        this.texts = texts;


        Timer timer = new Timer(100, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JFrame) SwingUtilities.getWindowAncestor(ListPanel.this)) != null) {
                    timer.stop();
                }
            }
        });
        timer.start();

        list = new JList<Object>(new DefaultListModel<Object>());
        for (Object element : elements) {
            ((DefaultListModel<Object>) list.getModel()).addElement(element);
        }

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(null);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollPane);
    }

    public void removeElementFromList(Object element) {
        int index = getElementIndex(element);
        if (((DefaultListModel<Object>) getList().getModel()).getElementAt(getElementIndex(element)) != null) {
            ((DefaultListModel<Object>) getList().getModel()).removeElementAt(index);
            getTexts().remove(index);
        }
    }

    public void removeElementFromList(int index) {
        if (((DefaultListModel<Object>) getList().getModel()).getElementAt(index) != null) {
            ((DefaultListModel<Object>) getList().getModel()).removeElementAt(index);
            getTexts().remove(index);
        }
    }

    private Integer getElementIndex(Object element) {
        for (int i = 0; i < ((DefaultListModel<Object>) getList().getModel()).getSize(); i++) {
            if (((DefaultListModel<Object>) getList().getModel()).getElementAt(i).equals(element)) {
                return i;
            }
        }
        return null;
    }

    public void addElementToList(Object element, String text) {
        ((DefaultListModel<Object>) list.getModel()).addElement(element);
        getTexts().add(text);
    }

    public JList<Object> getList() {
        return list;
    }

    public ArrayList<String> getTexts() {
        return texts;
    }

}
