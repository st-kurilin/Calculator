package com.github.stkurilin.calculator.core;

import com.github.stkurilin.calculator.core.token.ExecutableToken;
import com.github.stkurilin.calculator.core.token.Token;
import com.github.stkurilin.calculator.core.token.TokenVisitor;
import com.github.stkurilin.calculator.core.token.function.FunctionToken;
import com.github.stkurilin.calculator.core.token.operator.OperatorToken;
import com.github.stkurilin.calculator.core.token.service.NumberToken;
import com.github.stkurilin.calculator.core.token.service.SeparatorToken;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Transforms expression from split lexemes to polish notation.
 */
class ToPolishAnnotationVisitor implements TokenVisitor {
    private final LinkedList<Token> res;
    private final StackHolder stackHolder;
    private State previous;

    public ToPolishAnnotationVisitor() {
        stackHolder = new StackHolder();
        res = new LinkedList<Token>();
        previous = State.BEGIN;
    }

    public void visitNumber(NumberToken t) {
        res.add(t);
        previous = State.NUMBER;
    }

    public void visitOperator(OperatorToken t) {
        t.thereIsLeadingArguments(!thereIsNoArgumentsPresentNow());
        while (stackHolder.executableOnTop() && stackHolder.peekExecutable().mustCalculateBefore(t)) {
            res.addLast(stackHolder.popExecutable());
        }
        stackHolder.pushExecutable(t);
        previous = State.OPERATOR;
    }

    public void visitFunction(FunctionToken functionToken) {
        stackHolder.pushExecutable(functionToken);
        previous = State.FUNCTION;
    }

    public void visitArgumentSeparator(SeparatorToken separatorToken) {
        while (!stackHolder.leftParenthesisOnTop()) {
            res.addLast(stackHolder.popOperator());
        }
        stackHolder.peekFunction().incrementNumberOfArguments();
    }

    public void visitOpenParenthesis() {
        stackHolder.pushLeftParenthesis();
        previous = State.OPEN_PARENTHESIS;
    }

    public void visitCloseParenthesis() {
        while (!stackHolder.leftParenthesisOnTop()) {
            res.addLast(stackHolder.popOperator());
        }
        stackHolder.popLeftParenthesis();
        if (stackHolder.functionOnTop()) {
            res.addLast(stackHolder.popFunction());
        }
        previous = State.CLOSE_PARENTHESIS;
    }

    public List<Token> inPolishAnnotation() {
        while (stackHolder.executableOnTop()) {
            res.addLast(stackHolder.popOperator());
        }
        if (!stackHolder.isEmpty()) {
            throw new RuntimeException("Illegal state of stackHolder");
        }
        return res;
    }

    public boolean thereIsNoArgumentsPresentNow() {
        return previous == State.OPEN_PARENTHESIS || previous == State.BEGIN;
    }

    private static class StackHolder {
        //List of executable, just like in stack, but with indexed positions
        private final LinkedList<ExecutableToken> executable;
        //Map of opening parentheses in format <after operator's position, number of parentheses>
        private final Map<Integer, Integer> parenthesis;

        StackHolder() {
            executable = new LinkedList<ExecutableToken>();
            parenthesis = new HashMap<Integer, Integer>();
        }

        public void pushLeftParenthesis() {
            int pos = executable.size();
            Integer number = getCurrentParenthesis() + 1;
            parenthesis.put(pos, number);
        }

        public void popLeftParenthesis() {
            int index = executable.size();
            int val = parenthesis.get(index) - 1;
            if (val != 0) {
                parenthesis.put(index, val);
            } else {
                parenthesis.remove(index);
            }
        }

        public boolean executableOnTop() {
            return !leftParenthesisOnTop() && !executable.isEmpty();
        }

        public void pushExecutable(ExecutableToken functionToken) {
            executable.addLast(functionToken);
        }

        public ExecutableToken popExecutable() {
            return executable.removeLast();
        }

        public ExecutableToken peekExecutable() {
            return executable.getLast();
        }

        public boolean isEmpty() {
            return executable.isEmpty() && parenthesis.isEmpty();
        }

        public boolean leftParenthesisOnTop() {
            return getCurrentParenthesis() != 0;
        }

        public ExecutableToken popOperator() {
            if (peekExecutable().getClass().isAssignableFrom(OperatorToken.class)) {
                throw new IllegalStateException("There isn't operator at top");
            }
            return popExecutable();
        }

        public ExecutableToken popFunction() {
            if (!functionOnTop()) {
                throw new IllegalStateException("There isn't function at top");
            }
            return popExecutable();
        }

        public boolean functionOnTop() {
            return !executable.isEmpty() && peekExecutable().getClass().isAssignableFrom(FunctionToken.class);
        }

        public FunctionToken peekFunction() {
            if (getCurrentParenthesis() != 1) {
                throw new IllegalStateException("Left parenthesis expected");
            }
            return (FunctionToken) peekExecutable();
        }

        protected int getCurrentParenthesis() {
            Integer number = parenthesis.get(executable.size());
            if (number == null) {
                return 0;
            }
            return number;
        }
    }
}