package com.calc.lexicalanalysis;

import com.calc.exception.CalculatorRuntimeException;
import com.calc.token.Token;
import com.calc.tokenfactory.TokenFactory;

import java.text.ParsePosition;
import java.util.Iterator;

public class Analyzer {
    private TokenFactory factory;

    public Analyzer(TokenFactory factory) {
        this.factory = factory;
    }

    public Iterable<Token> getIterable(CharSequence input) {
        return new Handler(input);
    }

    private class Handler implements Iterable<Token> {
        private ValidatorVisitor validator;
        private String source;
        private Token nextToken;
        private ParsePosition position;

        Handler(CharSequence input) {
            validator = new ValidatorVisitor();
            source = input.toString();
            position = new ParsePosition(0);
            evalNextToken();
        }

        void evalNextToken() {
            skipWhiteSpaces();
            if (thereIsUnparsedText()) {
                Token token = factory.createToken(source, position);
                validateToken(token);
                nextToken = token;
            } else {
                nextToken = null;
            }
        }

        private void validateToken(Token token) {
            if (token == null) {
                validator.unrecognizedLexeme();
            } else {
                token.accept(validator);
                validator.setPositionToValidate(position.getIndex());
            }
        }

        private void skipWhiteSpaces() {
            if (thereIsUnparsedText()) {
                int i = position.getIndex();
                while (i < source.length() && Character.isWhitespace(source.charAt(i))) {
                    i++;
                }
                position.setIndex(i);
            }
        }

        private boolean thereIsUnparsedText() {
            return position.getIndex() != source.length();
        }


        public Iterator<Token> iterator() {
            return new Iterator<Token>() {
                public boolean hasNext() {
                    boolean done = (nextToken == null);
                    if (done) {
                        checkState();
                    }
                    return !done;
                }

                public Token next() {
                    Token current = nextToken;
                    evalNextToken();
                    return current;
                }

                private void checkState() {
                    if (thereIsUnparsedText()) {
                        validator.unrecognizedLexeme();
                    }
                    validator.throwErrorsIfTheyArePresented();
                }

                public void remove() {
                    throw new CalculatorRuntimeException("unsupported operation");
                }
            };
        }
    }
}
