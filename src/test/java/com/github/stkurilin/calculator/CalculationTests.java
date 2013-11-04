package com.github.stkurilin.calculator;

import com.github.stkurilin.calculator.core.Calculator;
import com.github.stkurilin.calculator.core.exception.CalculatorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.fail;


public abstract class CalculationTests {
    protected static Calculator calculator;
    private Logger logger = LoggerFactory.getLogger(CalculationTests.class);

    @BeforeClass
    public static void init() {
        calculator = new Calculator();
    }

    protected double calc(String expr) {
        try {
            return calculator.calc(expr).doubleValue();
        } catch (CalculatorException e) {
            logger.error("unexpected exception" + Arrays.toString(e.getStackTrace()));
            fail("unexpected exception");
            return 0;
        }
    }

    protected void assertCalculation(String comment, Double expected, String expr) {
        Assert.assertEquals(comment, expected, calc(expr), 0.01);
    }

    protected void assertCalculation(String comment, int expected, String expr) {
        Assert.assertEquals(comment, expected, calc(expr), 0.01);
    }
}
