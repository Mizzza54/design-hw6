package token;

import visitor.TokenVisitor;

/**
 * @author Michael Gerasimov
 */
public enum OperationToken implements Token {
    PLUS(1, "PLUS"),
    MINUS(1, "MINUS"),
    MULT(2, "MULT"),
    DIV(2, "DIV");

    private final int priority;
    private final String externalName;

    OperationToken(int priority, String externalName) {
        this.priority = priority;
        this.externalName = externalName;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public NumberToken applyOperation(NumberToken left, NumberToken right) {
        return new NumberToken(
                switch (this) {
                    case PLUS -> left.getNumber() + right.getNumber();
                    case MINUS -> left.getNumber() - right.getNumber();
                    case MULT -> left.getNumber() * right.getNumber();
                    case DIV -> left.getNumber() / right.getNumber();
                }
        );
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return externalName;
    }
}
