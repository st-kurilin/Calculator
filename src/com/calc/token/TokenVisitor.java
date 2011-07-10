package com.calc.token;

import com.calc.token.function.FunctionToken;
import com.calc.token.operator.OperatorToken;
import com.calc.token.service.NumberToken;
import com.calc.token.service.SeparatorToken;

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
