package com.tests;

import com.calc.exception.CalculatorException;
import org.junit.Test;

import static java.lang.Math.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;


public class FunctionTests extends CalculationTests {
    @Test
    public void sqrtTest() {
        assertCalculation("check sqrt function", sqrt(9d), "sqrt(9)");
    }

    @Test
    public void sqrtInExpressionTest() {
        assertCalculation("check sqrt function in expression", 1 + sqrt(9d) * 2, "1 + sqrt(9) * 2");
    }

    @Test
    public void sqrtWithExpressionAsArgumentsTest() {
        assertCalculation("check sqrt function with expression as argument",
                1 + sqrt(10 + 6) * 2, "1 + sqrt(10 + 6) * 2");
    }

    @Test
    public void innerSqrtTest() {
        assertCalculation("result of  sqrt function is argument of another sqrt function",
                1 + sqrt(sqrt(100) + sqrt(36)) * 2, "1 + sqrt(sqrt(100) + sqrt(36)) * 2");
    }

    @Test
    public void minFromTwoArgumentsTest() {
        assertCalculation("check min function", min(4, 4.5) + 2, "min(4, 4.5) + 2");
    }

    @Test
    public void minFromSeveralArgumentsTest() {
        assertCalculation("check min function", min(min(10, 2 + 1), 4.5), "min(10, 2+1, 4.5)");
    }

    @Test
    public void maxFunctionTest() {
        assertCalculation("check max function",
                2 + max(10, max(max(2, max(1, 2)), 4.5)) * 3, "2 + max(10, max(1, 2 + max(1, 2)), 4.5) * 3");
    }

    @Test
    public void severalFunctionsTest() {
        assertCalculation("check expresion with several functions",
                max(2, min(sqrt(9), 10)) + sqrt(max(16, 9)), "max(2, min(sqrt(9), 10)) + sqrt(max(16, 9))");
    }

    @Test
    public void unrecognizedFunctionTest() {
        try {
            calculator.calc("foo(1, 2)");
            fail("Unknown function name");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Unrecognized lexeme", e.getMessage());
            assertEquals("Check position", 0, e.getPosition());
        }
    }

    @Test
    public void illegalNumberOfArgumentsTest() {
        try {
            calculator.calc("sqrt(1, 2)");
            fail("Illegal number of arguments");
        } catch (CalculatorException e) {
            assertEquals("Check message", "These function can have only one argument", e.getMessage());
        }
    }

}
