package com.github.stkurilin.calculator;

import com.github.stkurilin.calculator.core.exception.CalculatorException;
import com.github.stkurilin.calculator.core.token.RuntimeLoadTokenFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ExtraTokenTests extends CalculationTests {
    static RuntimeLoadTokenFactory factory;

    @Before
    public void createExtraFactory() {
        factory = new RuntimeLoadTokenFactory();
        calculator.addTokenFactory(factory);
    }

    @Test
    public void addToken() throws CalculatorException {
        try {
            calculator.calc("sin(30)");
            fail("function lexeme must be unrecognizable before test");
        } catch (CalculatorException e) {
            factory.addTokenInfo("sin", ".\\target\\classes\\Sin.class");
            assertCalculation("new token doesn't calc correct", 0.5, "sin(30)");
        }
    }
}
