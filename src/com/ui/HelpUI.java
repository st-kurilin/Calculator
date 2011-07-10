package com.ui;

public class HelpUI implements CalculatorUI {
    public void go() {
        System.out.println("Run program with " +
                "\"console\" parameter to start CLI or " +
                "\"help\" too see this message.\n" +
                "Otherways GUI mode will be start");
    }
}
