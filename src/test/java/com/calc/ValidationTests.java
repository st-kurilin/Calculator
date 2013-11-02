package com.calc;

import com.calc.exception.CalculatorException;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ValidationTests extends CalculationTests {

    @Test
    public void operatorWithOutNumber() {
        try {
            calculator.calc("+");
            fail("Operator with out number");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Illegal number of arguments", e.getMessage());
        }
    }

    @Test
    public void illegalCharacterInExpression() {
        try {
            calculator.calc("2+a2");
            fail("Illegal character in expression");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Unrecognized lexeme", e.getMessage());
            assertEquals("Check position", 2, e.getPosition());
        }
    }

    @Test
    public void twainUnaryOperators() {
        try {
            calculator.calc("2+-2");
            fail("Check message and position for  Twain unary operators exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Operator doesn't expected here", e.getMessage());
            assertEquals("Check position", 2, e.getPosition());
        }
    }

    @Test
    public void nonClosedParenthesis() {
        try {
            calculator.calc("2+(32-(2-2)");
            fail("Check message and position for Illegal opening parenthesis exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Illegal opening parenthesis", e.getMessage());
            assertEquals("Check position", 2, e.getPosition());
        }
    }

    @Test
    public void illegalClosedParenthesis() {
        try {
            calculator.calc("2+32-(2)-2)+3");
            fail("Check message and position for Illegal closing parenthesis exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Illegal closing parenthesis", e.getMessage());
            assertEquals("Check position", 10, e.getPosition());
        }
    }

    @Test
    public void unbalancedParentheses() {
        try {
            calculator.calc("2+32)-(2)-(2");
            fail("Check message and position for Illegal closing parenthesis exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Illegal closing parenthesis", e.getMessage());
            assertEquals("Check position", 4, e.getPosition());
        }
    }

    @Test
    public void emptyParentheses() {
        try {
            calculator.calc("2+()-2");
            fail("Check message and position for Empty structure exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Empty structure", e.getMessage());
            assertEquals("Check position", 3, e.getPosition());
        }
    }

    @Test
    public void twainMultiplicationOperators() {
        try {
            calculator.calc("2**2");
            fail("Check message and position for  Twain non unary operators exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Operator doesn't expected here", e.getMessage());
            assertEquals("Check position", 2, e.getPosition());
        }
    }

    @Test
    public void multiplicationAndDivisionOperators() {
        try {
            calculator.calc("2*/2");
            fail("Check message and position for  Twain non unary operators exception");
        } catch (CalculatorException e) {
            assertEquals("Check message", "Operator doesn't expected here", e.getMessage());
            assertEquals("Check position", 2, e.getPosition());
        }
    }

    @Test
    public void binaryOperatorAsUnary() {
        try {
            calculator.calc("*2+1");
            fail("Check possibility of using multiplication as unary operation");
        } catch (Exception e) {
            assertEquals("Check message", "Operator cannot be unary", e.getMessage());
        }
    }

    @Test
    public void checkFactorialFromNegativeNumber() {
        try {
            calculator.calc("(-1)!");
            fail("Factorial isn't defined for negative numbers");
        } catch (Exception e) {
            assertEquals("Factorial isn't defined for negative numbers",
                    "Factorial defined only for nonnegative integers", e.getMessage());
        }
    }

    @Test
    public void checkFactorialInPrefixPosition() {
        try {
            calculator.calc("!3");
            fail("Factorial cannot placed in prefix");
        } catch (Exception e) {
            assertEquals("Check operand's illegal (prefix) position ",
                    "Illegal operator position", e.getMessage());
        }
    }
}
