package com.github.stkurilin.calculator.core.token;

import com.github.stkurilin.calculator.core.token.function.FunctionToken;
import com.github.stkurilin.calculator.core.token.operator.OperatorToken;
import com.github.stkurilin.calculator.core.token.service.NumberToken;
import com.github.stkurilin.calculator.core.token.service.SeparatorToken;

public interface TokenVisitor {
    void visitNumber(NumberToken t);

    void visitOperator(OperatorToken t);

    void visitOpenParenthesis();

    void visitCloseParenthesis();

    void visitFunction(FunctionToken functionToken);

    void visitArgumentSeparator(SeparatorToken separatorToken);

    enum State {
        BEGIN, NUMBER, OPERATOR, OPEN_PARENTHESIS, CLOSE_PARENTHESIS, SEPARATOR, FUNCTION
    }
}
