package com.github.stkurilin.calculator;

import org.junit.Test;

public class ArithmeticTest extends CalculationTests {
    @Test
    public void simpleSum() {
        assertCalculation("Check calculation with simple sum", 2 + 2, "2+2");
    }

    @Test
    public void simpleAdditionWithSpaces() {
        assertCalculation("Check parser for spaces \"preprocessor\"", 2 + 2, "2 + 2");
    }

    @Test
    public void unaryPlus() {
        assertCalculation("Check calculation with unary plus", +71, "+71");
    }

    @Test
    public void complicatedAddition() {
        assertCalculation("Check calculation with addition of several numbers", +2 + 13 + 3, "+2+13+3");
    }

    @Test
    public void simpleSubtracting() {
        assertCalculation("Check calculation with simple subtracting", 2 - 12, "2-12");
    }

    @Test
    public void unaryMinus() {
        assertCalculation("Check calculation with unary minus", -102, "-102");
    }

    @Test
    public void negativeSubtracting() {
        assertCalculation("Check calculation with subtracting from negative number", -6 - 3, "-6 - 3");
    }

    @Test
    public void complicatedSubtracting() {
        assertCalculation("Check calculation with subtracting of several numbers", -10 - 3 - 17, "-10-3-17");
    }

    @Test
    public void simpleExpression() {
        assertCalculation("Addition and subtracting together", -1 + 2 + 3, "-1+2+3");
    }

    @Test
    public void simpleExpressionWithSpaces() {
        assertCalculation("Check parser for spaces \"preprocessor\"", -1 + 2 + 3, "- 1  + 2 +3");
    }

    @Test
    public void simpleParenthesis() {
        assertCalculation("Operations with parentheses", -1 + (2 - 3), "-1 + (2 - 3)");
    }

    @Test
    public void complicatedParenthesis() {
        assertCalculation("Operations with inner parentheses", -1 + (-2 + (-3) - (+2 + 3 + (-(2) + 1))),
                "-1 + (-2 + (-3) - (+2 + 3 + (-(2) + 1)))");
    }

    @Test
    public void sumDouble() {
        assertCalculation("Check sum operation with double", 2.5 + 3.7, "2.5 + 3.7");
    }

    @Test
    public void multiplication() {
        assertCalculation("Check multiplication operation", 2.5 * (-2) * (-4) * (-2), "2.5 * (-2) * (-4) * (-2)");
    }

    @Test
    public void division() {
        assertCalculation("Check division operation", 16. / 4 / 8, "16/4/8");
    }

    @Test
    public void multiplicationAndDivisionCombine() {
        assertCalculation("Check multiplication and division combine", 16. / (3 * 2), "16/(3*2)");
    }

    @Test
    public void checkOperationsPrecedence() {
        assertCalculation("Multiplication vs Addition", 2.3 + 2 * 2, "2.3 + 2 * 2");
    }

    @Test
    public void checkSimpleOperationsPrecedenceWithParentheses() {
        assertCalculation("Multiplication vs Addition with parentheses", (2.3 + 2) * 2, "(2.3 + 2) * 2");
    }

    @Test
    public void checkOperationsPrecedenceWithParentheses() {
        assertCalculation("Check calculation of expressions with inner parentheses", -2.2 + 2 * (2.2 + 3 * 4 + (9 + 3) * 3),
                "-2.2 + 2 * (2.2 + 3 * 4 + (9 + 3) * 3)");
    }


    @Test
    public void checkRightOrientedPrecedence() {
        assertCalculation("Check calculation of expressions with right oriented precedence",
                Math.pow(2, Math.pow(3, 2)), "2 ^ 3 ^ 2");
    }

    @Test
    public void checkDifferentOrientedPrecedence() {
        //1 + 2.5 ^ 2 - (-5 - 4 ^ 2) * 2 ^ 3 ^ 2
        //    ---a---         --b--    ----c----
        double a = Math.pow(2.5, 2);
        double b = Math.pow(4, 2);
        double c = Math.pow(3, 2);
        c = Math.pow(2, c);

        assertCalculation("Check calculation of expressions with  different oriented precedence", 1 + a - (-5 - b) * c,
                "1 + 2.5 ^ 2 - (-5 - 4 ^ 2) * 2 ^ 3 ^ 2");
    }

    @Test
    //http://en.wikipedia.org/wiki/Shunting-yard_algorithm
    public void checkExpressionFromWikipedia() {
        double a = Math.pow(2, 3);
        a = Math.pow(1 - 5, a);
        assertCalculation("Check calculation of expressions with  different oriented precedence", 3d + 4 * 2 / a,
                "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3");
    }

    @Test
    public void checkIncrement() {
        assertCalculation("Check calculation with increment", 1 + 1, "++1");
    }

    @Test
    public void checkFactorial() {
        assertCalculation("Check calculation with factorial", 120, "5!");
    }

    @Test
    public void checkFactorialFromZero() {
        assertCalculation("Check calculation with factorial from zero", 1, "0!");
    }

    @Test
    public void checkDecrement() {
        assertCalculation("Check calculation with increment", 2, "++1+(--1)");
    }

    @Test
    public void checkDecrementAndIncrement() {
        assertCalculation("Check calculation with increment and decrement", 2, "++(+(--2))");
    }

    @Test
    public void checkDecrementAndIncrementInSimpleExpression() {
        assertCalculation("Check calculation with increment and decrement", 4, "2 + (++5 / 3)");
    }

    @Test
    public void checkDecrementAndIncrementInExpression() {
        assertCalculation("Check calculation with increment and decrement", 5, "++6 / 2 + 3 + (--3)  / 4 - (--2) * 2 ");
    }
}
