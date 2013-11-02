package com.github.stkurilin.calculator.core.token;

public abstract class ExecutableToken implements Token {
    protected Order order;

    protected ExecutableToken(Order order) {
        this.order = order;
    }

    // if there is unknown order in creation time. (hack for unary minus)
    protected ExecutableToken() {
        this(null);
    }

    public abstract Number calculate(Number... args);

    //if there is different order of evaluation on same level. it must override this method.
    public boolean mustCalculateBefore(ExecutableToken t) {
        int compare = order.compareTo(t.order);
        if (t.isLeftAssociative()) {
            return compare <= 0;
        } else {
            return compare < 0;
        }
    }

    public abstract int getNumberOfArguments();

    private boolean isLeftAssociative() {
        return order != Order.PowerLevel;
    }

    //Precedence and Order of Evaluation
    protected enum Order {    //Associativity       //   example
        Function,
        UnaryOperator,         //Right to left       //! ++ --
        PowerLevel,           //Right to left       // ^
        MultiplicativeLevel,   //Left to right       //* / %
        AdditiveOperator   //Right to left         //- + (binary)
    }
}
