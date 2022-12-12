package visitor;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Michael Gerasimov
 */
public class ParserVisitor implements TokenVisitor {
    private List<Token> result;
    private Deque<Token> stack;

    public List<Token> parse(List<Token> tokens) {
        result = new ArrayList<>();
        stack = new ArrayDeque<>();

        for (Token token : tokens) {
            token.accept(this);
        }

        while (!stack.isEmpty()) {
            Token curToken = stack.removeLast();
            if (!(curToken instanceof OperationToken)) {
                throw new IllegalStateException(
                        "Parse error: wrong balanced braces"
                );
            }
            result.add(curToken);
        }

        return result;
    }


    @Override
    public void visit(NumberToken token) {
        result.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        switch (token) {
            case OPEN -> stack.addLast(token);
            case CLOSE -> {
                Token curToken;
                while (!stack.isEmpty() && (curToken = stack.removeLast()) != BraceToken.OPEN) {
                    result.add(curToken);
                    if (stack.isEmpty()) {
                        throw new IllegalStateException(
                                "Parser visitor exception: wrong balance"
                        );
                    }
                }
            }
        }
    }

    @Override
    public void visit(OperationToken token) {
        while (!stack.isEmpty()
                && stack.getLast() instanceof OperationToken
                && ((OperationToken) stack.getLast()).getPriority() >= token.getPriority()) {
            result.add(stack.removeLast());
        }
        stack.addLast(token);
    }
}
