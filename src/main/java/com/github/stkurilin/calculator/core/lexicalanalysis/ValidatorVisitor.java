package com.github.stkurilin.calculator.core.lexicalanalysis;

import com.github.stkurilin.calculator.core.exception.CalculatorRuntimeException;
import com.github.stkurilin.calculator.core.token.TokenVisitor;
import com.github.stkurilin.calculator.core.token.function.FunctionToken;
import com.github.stkurilin.calculator.core.token.operator.OperatorToken;
import com.github.stkurilin.calculator.core.token.service.NumberToken;
import com.github.stkurilin.calculator.core.token.service.SeparatorToken;

import java.util.LinkedList;
import java.util.Stack;

class ValidatorVisitor implements TokenVisitor {
    private State previous;
    private Stack<Integer> positionsOfNotClosedParentheses;
    private int cursor;
    private LinkedList<FunctionLexeme> functions;

    public ValidatorVisitor() {
        previous = State.BEGIN;
        positionsOfNotClosedParentheses = new Stack<Integer>();
        functions = new LinkedList<FunctionLexeme>();
    }

    public void visitNumber(NumberToken t) {
        validateNotIn("Number doesn't expected here", State.NUMBER, State.CLOSE_PARENTHESIS);
        previous = State.NUMBER;
    }

    public void visitOperator(OperatorToken t) {
        validateNotIn("Operator doesn't expected here", State.OPERATOR);
        previous = State.OPERATOR;
    }

    public void visitOpenParenthesis() {
        positionsOfNotClosedParentheses.push(cursor);
        previous = State.OPEN_PARENTHESIS;
    }

    public void visitCloseParenthesis() {
        validateNotIn("Empty structure", State.OPEN_PARENTHESIS);
        if (positionsOfNotClosedParentheses.isEmpty()) {
            throw new CalculatorRuntimeException("Illegal closing parenthesis", cursor);
        }
        positionsOfNotClosedParentheses.pop();
        previous = State.CLOSE_PARENTHESIS;
    }

    public void visitFunction(FunctionToken functionToken) {
        validateNotIn("Function  doesn't expected here", State.CLOSE_PARENTHESIS);
        functions.addLast(new FunctionLexeme(cursor, functionToken));
    }

    public void visitArgumentSeparator(SeparatorToken separatorToken) {
        validateNotIn("Function  doesn't expected here", State.SEPARATOR, State.OPERATOR, State.OPEN_PARENTHESIS);
        previous = State.SEPARATOR;
    }

    public void setPositionToValidate(int cursor) {
        this.cursor = cursor;
    }

    public void unrecognizedLexeme() {
        throw new CalculatorRuntimeException("Unrecognized lexeme", cursor);
    }

    public void throwErrorsIfTheyArePresented() {
        if (!positionsOfNotClosedParentheses.isEmpty()) {
            throw new CalculatorRuntimeException("Illegal opening parenthesis", positionsOfNotClosedParentheses.pop());
        }
        for (FunctionLexeme p : functions) {
            if (!p.getFunction().hasLegalNumberOfArguments()) {
                throw new CalculatorRuntimeException("Illegal number of arguments : " + p.getFunction().getNumberOfArguments(),
                        p.getPosition());
            }
        }
    }

    /*
   * throw CalculatorRuntimeException if previous not in permissible states
   * */
    private void validateIn(String msg, State... permissible) {
        for (State s : permissible) {
            if (previous == s) {
                return;
            }
        }
        throw new CalculatorRuntimeException(msg, cursor);
    }

    /*
   * throw CalculatorRuntimeException if previous in not permissible states
   * */
    private void validateNotIn(String msg, State... notPermissible) {
        for (State s : notPermissible) {
            if (previous == s) {
                throw new CalculatorRuntimeException(msg, cursor);
            }
        }
    }

    private static class FunctionLexeme {
        private Integer position;
        private FunctionToken function;

        private FunctionLexeme(Integer position, FunctionToken function) {
            this.position = position;
            this.function = function;
        }

        public Integer getPosition() {
            return position;
        }

        public FunctionToken getFunction() {
            return function;
        }
    }
}




