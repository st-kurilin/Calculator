package com.github.stkurilin.calculator.core.token;

import java.text.ParsePosition;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CompositeTokenFactory implements TokenFactory {
    private List<TokenFactory> factories;

    public CompositeTokenFactory(TokenFactory... factories) {
        this.factories = new LinkedList<TokenFactory>(Arrays.asList(factories));
    }

    /*if there are several variants of lexemes token will be create from the longest*/
    public Token createToken(String source, ParsePosition parsePosition) {
        int original = parsePosition.getIndex();
        int maxLength = original;
        Token best = null;

        for (TokenFactory factory : factories) {
            Token current = factory.createToken(source, parsePosition);
            if (parsePosition.getIndex() > maxLength) {
                maxLength = parsePosition.getIndex();
                best = current;
            }
            parsePosition.setIndex(original);
        }
        parsePosition.setIndex(maxLength);
        return best;
    }

    public boolean addAlias(String old, String... aliases) {
        for (TokenFactory factory : factories) {
            boolean done = factory.addAlias(old, aliases);
            if (done) {
                return true;
            }
        }
        return false;
    }

    public void addTokenFactory(TokenFactory factory) {
        factories.add(factory);
    }
}
