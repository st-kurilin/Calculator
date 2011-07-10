package com.tests;

import com.calc.Calculator;
import org.junit.Before;
import org.junit.Test;

public class AliasesTests extends CalculationTests {
    @Before
    public void calculatorStateLess() {
        calculator = new Calculator();
    }

    @Test
    public void plusAlias() {
        calculator.addAlias("+", "plus");
        assertCalculation("Check calculation with alias", +2 + 2 + 2, "+ 2 plus 2 + 2 ");
    }

    @Test
    public void plusAliasWithOutWhitespaces() {
        calculator.addAlias("+", "plus");
        assertCalculation("Check parsing expression with out whitespaces", 2 + 2, "2plus2");
    }

    @Test
    public void plusAliasWithParentheses() {
        calculator.addAlias("+", "plus");
        assertCalculation("Check calculation with simple sum", 2 + 2 + 3, "2 plus(2plus 3 )");
    }

    @Test
    public void severalAliasesForOneLexeme() {
        calculator.addAlias("+", "plus");
        calculator.addAlias("+", "add");
        assertCalculation("Check calculation with several aliases", 1 + 2 + 3 + 4, "1 + 2 plus 3 add 4");
    }

    @Test
    public void severalAliasesWithLinking() {
        calculator.addAlias("+", "plus");
        calculator.addAlias("plus", "add");
        assertCalculation("Check calculation with several aliases", 1 + 2 + 3 + 4, "1 + 2 plus 3 add 4");
    }

    @Test
    public void recursionAliases() {
        calculator.addAlias("+", "plus");
        calculator.addAlias("plus", "addition");
        calculator.addAlias("addition", "+");
        assertCalculation("Check recursion in aliases", 1 + 2 + 3 + 4, "1 addition 2 plus 3 + 4");
    }

    @Test
    public void aliasPriority() {
        calculator.addAlias("+", "fooo");
        calculator.addAlias("-", "fo");
        calculator.addAlias("fooo", "f");
        calculator.addAlias("*", "foo");
        assertCalculation("Check preority in aliases", +2 + 3 * 2, "f2fooo3foo2");
    }
}
