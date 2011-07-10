package com.calc;

import com.calc.exception.CalculatorRuntimeException;
import com.calc.token.ExecutableToken;
import com.calc.token.Token;
import com.calc.token.TokenVisitor;
import com.calc.token.function.FunctionToken;
import com.calc.token.operator.OperatorToken;
import com.calc.token.service.NumberToken;
import com.calc.token.service.SeparatorToken;

import java.util.Stack;

class CalcPolishAnnotationVisitor implements TokenVisitor {
    private Stack<Token> stack;

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
        Token res = stack.pop();
        if (!stack.isEmpty()) {
            throw new RuntimeException("Illegal lexeme in stack: " + stack.pop());
        }
        return ((NumberToken) res).getValue();
    }
}
