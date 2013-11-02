package com.calc.token.operator.additive;

import com.calc.tokenfactory.MapBasedTokenFactory;

public class AdditiveTokenFactory extends MapBasedTokenFactory.MutableTokens {
    public AdditiveTokenFactory() {
        addTokenInfo("+", new PlusOperator());
        addTokenInfo("-", new MinusOperator());
    }
}
