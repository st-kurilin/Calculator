package com.github.stkurilin.calculator.core;

import com.github.stkurilin.calculator.core.exception.CalculatorRuntimeException;
import com.github.stkurilin.calculator.core.token.ExecutableToken;
import com.github.stkurilin.calculator.core.token.Token;
import com.github.stkurilin.calculator.core.token.TokenVisitor;
import com.github.stkurilin.calculator.core.token.function.FunctionToken;
import com.github.stkurilin.calculator.core.token.operator.OperatorToken;
import com.github.stkurilin.calculator.core.token.service.NumberToken;
import com.github.stkurilin.calculator.core.token.service.SeparatorToken;

import java.util.Stack;

/**
 * Evaluates expression in polish notation.
 */
class CalcPolishAnnotationVisitor implements TokenVisitor {
    private final Stack<Token> stack;

    public CalcPolishAnnotationVisitor() {
        stack = new Stack<Token>();
    }

    public void visitNumber(NumberToken t) {
        stack.push(t);
    }

    public void visitOperator(OperatorToken t) {
        visitExecutable(t);
    }

    private void visitExecutable(ExecutableToken token) {
        int n = token.getNumberOfArguments();
        Number[] args = new Number[n];
        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                throw new CalculatorRuntimeException("Illegal number of arguments");
            }
            args[i] = convertToNumber(stack.pop());
        }
        Double res = token.calculate(args).doubleValue();
        stack.push(new NumberToken(res));
    }

    private Number convertToNumber(Token token) {
        return ((NumberToken) token).getValue();
    }

    public void visitOpenParenthesis() {
        throw new UnsupportedOperationException("Unexpected parenthesis");
    }

    public void visitCloseParenthesis() {
        throw new UnsupportedOperationException("Unexpected parenthesis");
    }

    public void visitArgumentSeparator(SeparatorToken separatorToken) {
        throw new UnsupportedOperationException("Unexpected parenthesis");
    }

    public void visitFunction(FunctionToken functionToken) {
        visitExecutable(functionToken);
    }

    public Number getResult() {
        if (stack.isEmpty()) {
            return 0;
        }
        final Token res = stack.pop();
        if (!stack.isEmpty()) {
            throw new RuntimeException("Illegal lexeme in stack: " + stack.pop());
        }
        return ((NumberToken) res).getValue();
    }
}
