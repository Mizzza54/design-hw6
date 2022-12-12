package token;

import visitor.TokenVisitor;

import java.util.Objects;

/**
 * @author Michael Gerasimov
 */
public class NumberToken implements Token {
    private final Integer number;

    public NumberToken(Integer number) {
        this.number = number;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("NUMBER(%d)", number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberToken that = (NumberToken) o;

        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
