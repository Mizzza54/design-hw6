package visitor;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;

/**
 * @author Michael Gerasimov
 */
public interface TokenVisitor {

    void visit(NumberToken token);

    void visit(BraceToken token);

    void visit(OperationToken token);

}
