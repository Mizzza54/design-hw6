package token;

import visitor.TokenVisitor;

/**
 * @author Michael Gerasimov
 */
public enum BraceToken implements Token {
    OPEN("LEFT"),
    CLOSE("RIGHT");

    private final String externalName;

    BraceToken(String externalName) {
        this.externalName = externalName;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    @Override
    public String toString() {
        return externalName;
    }
}
