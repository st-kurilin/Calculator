package com;

import com.calc.Calculator;
import com.ui.CalculatorUI;
import com.ui.ConsoleUI;
import com.ui.GraphicalUI;
import com.ui.HelpUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Runner {
    private static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        logger.debug("start");
        Calculator calculator = new Calculator();
        CalculatorUI ui;
        if (presentOnly(args, "help")) {
            logger.debug("help mode");
            ui = new HelpUI();
        } else {
            if (presentOnly(args, "console")) {
                logger.debug("console mode");
                ui = new ConsoleUI(calculator);
            } else {
                logger.debug("gui mode");
                ui = new GraphicalUI(calculator);
            }
        }
        ui.go();
        logger.debug("end");
    }

    private static boolean presentOnly(String[] args, String s) {
        return args.length == 1 && args[0].equalsIgnoreCase(s);
    }
}
