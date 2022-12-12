package visitor;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Michael Gerasimov
 */
public class PrintVisitor implements TokenVisitor {

    private final OutputStream os;

    public PrintVisitor(OutputStream os) {
        this.os = os;
    }

    public void print(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
    }

    @Override
    public void visit(NumberToken token) {
        write(token + " ");
    }

    @Override
    public void visit(BraceToken token) {
        write(token + " ");
    }

    @Override
    public void visit(OperationToken token) {
        write(token + " ");
    }


    private void write(String s) {
        try {
            os.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
