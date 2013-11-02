package com.github.stkurilin.calculator.core.exception;

public class CalculatorRuntimeException extends RuntimeException {
    private int position;

    public CalculatorRuntimeException(String msg, int position) {
        super(msg);
        this.position = position;
    }

    public CalculatorRuntimeException(String msg) {
        this(msg, 0);
    }

    public int getPosition() {
        return position;
    }
}
