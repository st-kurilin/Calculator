package com.calc.token.function;

import com.calc.exception.CalculatorRuntimeException;
import com.calc.token.ExecutableToken;
import com.calc.token.MutableToken;
import com.calc.token.TokenVisitor;

public abstract class FunctionToken extends ExecutableToken {
    protected FunctionToken() {
        super(Order.Function);
    }

    public void accept(TokenVisitor t) {
        t.visitFunction(this);
    }

    public abstract void incrementNumberOfArguments();

    //after thereIsLeadingArguments ExecutableToken can be in state where it has illegal number of arguments
    //return true if all right, false - other way.
    public abstract boolean hasLegalNumberOfArguments();

    public static abstract class SingleArgumentFunction extends FunctionToken {
        public final int getNumberOfArguments() {
            return 1;
        }

        @Override
        public final boolean hasLegalNumberOfArguments() {
            return true;    //as it const
        }

        public final void incrementNumberOfArguments() {
            throw new CalculatorRuntimeException("These function can have only one argument");
        }

    }

    public static abstract class MultiArgumentsFunction extends FunctionToken implements MutableToken {
        private int numberOfArguments = 1;

        @Override
        public FunctionToken clone() throws CloneNotSupportedException {
            return (FunctionToken) super.clone();
        }

        public int getNumberOfArguments() {
            return numberOfArguments;
        }

        public void incrementNumberOfArguments() {
            numberOfArguments++;
        }
    }
}
