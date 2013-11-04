package com.github.stkurilin.calculator;

import com.github.stkurilin.calculator.core.Calculator;
import com.github.stkurilin.calculator.ui.CalculatorUI;
import com.github.stkurilin.calculator.ui.ConsoleUI;
import com.github.stkurilin.calculator.ui.GraphicalUI;
import com.github.stkurilin.calculator.ui.HelpUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runs program.
 */
public class Runner {
    private static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        logger.debug("start");
        final Calculator calculator = new Calculator();
        final CalculatorUI ui = createUi(args, calculator);
        ui.go();
        logger.debug("end");
    }

    private static CalculatorUI createUi(String[] args, Calculator calculator) {
        if (args.length == 1) {
            final String arg = args[0];
            if (arg.equals("help")) {
                logger.debug("help mode");
                return new HelpUI();
            }
            if (arg.equals("console")) {
                logger.debug("console mode");
                return new ConsoleUI(calculator);
            }
        }
        logger.debug("gui mode");
        return new GraphicalUI(calculator);
    }
}
