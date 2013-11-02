package com.github.stkurilin.calculator.ui;

import com.github.stkurilin.calculator.core.Calculator;
import com.github.stkurilin.calculator.core.exception.CalculatorException;

import java.util.Scanner;

public class ConsoleUI implements CalculatorUI {
    private Calculator calculator;

    public ConsoleUI(Calculator calculator) {
        this.calculator = calculator;

    }

    public void go() {
        System.out.println("Type quit to exit");

        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        String in;
        while (!done) {
            System.out.print(">>");
            in = scanner.nextLine();
            if (!in.equalsIgnoreCase("quit")) {
                try {
                    System.out.println(calculator.calc(in));
                } catch (CalculatorException e) {
                    System.out.println(e.getMessage() + " at char # " + e.getPosition());
                }
            } else {
                done = true;
            }
        }
        System.out.println("Bue");

    }
}
