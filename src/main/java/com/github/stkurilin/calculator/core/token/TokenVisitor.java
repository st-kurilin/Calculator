package com.github.stkurilin.calculator.core.token;

import com.github.stkurilin.calculator.core.token.function.FunctionToken;
import com.github.stkurilin.calculator.core.token.operator.OperatorToken;
import com.github.stkurilin.calculator.core.token.service.NumberToken;
import com.github.stkurilin.calculator.core.token.service.SeparatorToken;

/**
 * Interface for token sequence handling.
 */
public interface TokenVisitor<T> {
    void visitNumber(NumberToken t);

    void visitOperator(OperatorToken t);

    void visitOpenParenthesis();

    void visitCloseParenthesis();

    void visitFunction(FunctionToken functionToken);

    void visitArgumentSeparator(SeparatorToken separatorToken);

    //should be called on ending sequence handling. visitor can return accumulated state.
    T complete();

    enum State {
        BEGIN, NUMBER, OPERATOR, OPEN_PARENTHESIS, CLOSE_PARENTHESIS, SEPARATOR, FUNCTION
    }

    class Utils {
        public static <T> T process(TokenVisitor<T> visitor, Iterable<Token> tokens) {
            for (Token each : tokens) {
                each.accept(visitor);
            }
            return visitor.complete();
        }
    }
}
