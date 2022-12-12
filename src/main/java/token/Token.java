package token;

import visitor.TokenVisitor;

/**
 * @author Michael Gerasimov
 */
public interface Token {
    void accept(TokenVisitor tokenVisitor);
}
