package com.github.stkurilin.calculator.core.token.operator.additive;

import com.github.stkurilin.calculator.core.token.MapBasedTokenFactory;

public class AdditiveTokenFactory extends MapBasedTokenFactory.MutableTokens {
    public AdditiveTokenFactory() {
        addTokenInfo("+", new PlusOperator());
        addTokenInfo("-", new MinusOperator());
    }
}
