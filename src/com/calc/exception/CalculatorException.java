package com.calc.exception;

public class CalculatorException extends Exception {
    private int position;

    public CalculatorException(CalculatorRuntimeException e) {
        super(e.getMessage());
        position = e.getPosition();
    }

    public CalculatorException(Throwable e) {
        super(e);
    }

    public int getPosition() {
        return position;
    }
}
