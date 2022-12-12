package visitor;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author Michael Gerasimov
 */
public class CalcVisitor implements TokenVisitor {

    private Deque<Token> stack;

    public Integer calculate(List<Token> expr) {
        stack = new ArrayDeque<>();

        for (Token token : expr) {
            token.accept(this);
        }

        if (stack.size() != 1) {
            throw new IllegalStateException("After calculate stack must be size 1");
        }
        return popNumber().getNumber();
    }


    @Override
    public void visit(NumberToken token) {
        stack.addLast(token);
    }

    @Override
    public void visit(BraceToken token) {
        throw new IllegalStateException("Parsed tokens should not have braces");
    }

    @Override
    public void visit(OperationToken token) {
        NumberToken right = popNumber();
        NumberToken left = popNumber();
        stack.addLast(token.applyOperation(left, right));
    }

    private NumberToken popNumber() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Empty stack");
        }
        Token num = stack.removeLast();
        return (NumberToken) num;
    }

}