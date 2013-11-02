package com.calc.lexicalanalysis;

public class Lexeme {
    private String text;
    private int startPosition;

    public Lexeme(String text, int startPosition) {
        this.text = text;
        this.startPosition = startPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }


    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "lexeme(" + startPosition + "):" + text;
    }
}
