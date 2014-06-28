package com.main.java.hexformatter.GUI;

import com.main.java.hexformatter.FileModel.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * Created by E. Mozharovsky on 28.06.14.
 */
public class Formatter extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JTextArea input;
    private JTextArea output;

    private Parser parser;

    public Formatter() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        init();

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setResizable(false);
        pack();
    }

    private void init() {
        initMenuBar();
        initUserPane();
        initButtons();
        initResultPane();
    }

    private void initUserPane() {
        JPanel userPane = new JPanel();

        JLabel hint = new JLabel("<html><br>Source data: </br>");
        userPane.add(hint);

        input = new JTextArea(5, 25);
        input.setWrapStyleWord(true);
        input.setLineWrap(true);

        JScrollPane scroll = new JScrollPane(input, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        userPane.add(scroll);

        getContentPane().add(BorderLayout.NORTH, userPane);
    }

    private void initResultPane() {
        JPanel resultPane = new JPanel();

        JLabel result = new JLabel("<html><br>Result data: </br>");
        resultPane.add(result);

        output = new JTextArea(5, 25);
        output.setWrapStyleWord(true);
        output.setLineWrap(true);
        output.setEnabled(false);

        JScrollPane scroll = new JScrollPane(output, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resultPane.add(scroll);

        getContentPane().add(BorderLayout.SOUTH, resultPane);
    }

    private void initButtons() {
        JPanel buttons = new JPanel();

        JButton format = new JButton("Format");
        format.addActionListener(e -> {
            parser = new Parser();

            output.setText(parser.getFormattedData(input.getText()));
        });

        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> {
            input.setText("");
            output.setText("");
        });

        JButton copy = new JButton("Copy");
        copy.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(output.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); // copy output to the clipboard
        });

        buttons.add(format);
        buttons.add(clear);
        buttons.add(copy);

        getContentPane().add(BorderLayout.CENTER, buttons);
    }

    private void initMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("Formatter");

        JMenuItem about = new JMenuItem("About me");
        about.addActionListener(e -> JOptionPane.showMessageDialog(getContentPane(), "Copyright (c) 2014 E. Mozharovsky. All rights reserved. \nThe app is written in Java 8 (jdk_1.8.X).\n\nContact us: mozharovsky@live.com", "About me", JOptionPane.INFORMATION_MESSAGE));

        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> dispose());

        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(e -> {
            input.setText("");
            output.setText("");
        });

        file.add(about);
        file.add(clear);
        file.addSeparator();
        file.add(close);

        bar.add(file);
        setJMenuBar(bar);
    }
}
