package com.github.stkurilin.calculator.core.token.service;

import com.github.stkurilin.calculator.core.token.MapBasedTokenFactory;
import com.github.stkurilin.calculator.core.token.Token;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class ServiceTokenFactory extends MapBasedTokenFactory.ImmutableTokens {
    public ServiceTokenFactory() {
        addTokenInfo("(", new OpeningParenthesis());
        addTokenInfo(")", new ClosingParenthesis());
        addTokenInfo(",", new SeparatorToken());
    }

    public Token createToken(String source, ParsePosition position) {
        Token token = super.createToken(source, position);
        if (token != null) {
            return token;
        }
        return parseNumber(source, position);
    }

    private Token parseNumber(String source, ParsePosition position) {
        if (signPresented(source, position)) {
            return null;
        }
        NumberFormat parser = DecimalFormat.getInstance(Locale.US);
        Number number = parser.parse(source, position);
        if (number != null) {
            return new NumberToken(number);
        }
        return null;

    }

    private boolean signPresented(String source, ParsePosition position) {
        return source.charAt(position.getIndex()) == '-' || source.charAt(position.getIndex()) == '+';
    }
}
