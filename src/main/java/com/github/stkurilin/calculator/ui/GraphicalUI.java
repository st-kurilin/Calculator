package com.github.stkurilin.calculator.ui;

import com.github.stkurilin.calculator.core.Calculator;
import com.github.stkurilin.calculator.core.exception.CalculatorException;
import com.github.stkurilin.calculator.core.token.RuntimeLoadTokenFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GraphicalUI implements ActionListener, CalculatorUI {
    private Calculator calculator;
    private JTextField inputText;
    private JTextField outputText;
    private JLabel errors;
    private JButton calcButton;

    private JButton addTokenButton;
    private JButton addAlliasButton;
    private RuntimeLoadTokenFactory factory;


    public GraphicalUI(Calculator calculator) {
        this.calculator = calculator;
        factory = new RuntimeLoadTokenFactory();
        calculator.addTokenFactory(factory);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                if (e.getSource() == calcButton) {
                    String text = inputText.getText();

                    Number res = calculator.calc(text);
                    outputText.setText(res.toString());
                    hideErrorLabel();

                }
                if (e.getSource() == addTokenButton) {
                    String lexeme = JOptionPane.showInputDialog(null, "input alias");
                    if ((lexeme != null) && (lexeme.length() > 0)) {
                        JFileChooser fc = new JFileChooser();
                        int returnVal = fc.showOpenDialog(null);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            factory.addTokenInfo(lexeme, file.getAbsolutePath());
                        }
                    }


                }
                if (e.getSource() == addAlliasButton) {
                    String old = JOptionPane.showInputDialog(null, "input old lexeme");
                    if ((old != null) && (old.length() > 0)) {
                        String alias = JOptionPane.showInputDialog(null, "input alias");
                        if ((alias != null) && (alias.length() > 0)) {
                            calculator.addAlias(old, alias);
                        }
                    }
                }

            }
        } catch (CalculatorException ex) {
            errorPresent(ex.getMessage(), ex.getPosition());
        }
    }

    public void go() {
        final GraphicalUI some = this;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame();
                f.setTitle("JCalc");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setResizable(false);
                f.setSize(315, 150);


                JPanel panel = new JPanel();
                f.add(panel);

                inputText = new JTextField(15);
                panel.add(inputText);

                calcButton = new JButton("Go!");
                calcButton.addActionListener(some);
                panel.add(calcButton);

                outputText = new JTextField(5);
                outputText.setEditable(false);
                panel.add(outputText);


                addTokenButton = new JButton("add token");
                addTokenButton.addActionListener(some);
                panel.add(addTokenButton);

                addAlliasButton = new JButton("add alias");
                addAlliasButton.addActionListener(some);
                panel.add(addAlliasButton);


                errors = new JLabel();
                errors.setSize(90, 400);
                hideErrorLabel();
                panel.add(errors);

                f.setVisible(true);


            }
        });
    }

    private void hideErrorLabel() {
        errors.setText("Label for error msg presenting");
        errors.setVisible(false);
    }

    private void errorPresent(String msg, int pos) {
        outputText.setText("");
        System.out.println(msg);
        errors.setText(msg);
        inputText.setCaretPosition(pos);
        inputText.requestFocus();
        errors.setSize(90, 400);
        errors.setVisible(true);
    }
}

