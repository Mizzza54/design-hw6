import org.junit.jupiter.api.Test;
import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;
import visitor.CalcVisitor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Michael Gerasimov
 */
public class CalcVisitorTest {

    @Test
    public void testPlus() {
        List<Token> tokens = List.of(new NumberToken(2), new NumberToken(1), OperationToken.PLUS);
        Integer result = new CalcVisitor().calculate(tokens);
        assertEquals(3, result);
    }

    @Test
    public void testMinus() {
        List<Token> tokens = List.of(new NumberToken(2), new NumberToken(1), OperationToken.MINUS);
        Integer result = new CalcVisitor().calculate(tokens);
        assertEquals(1, result);
    }

    @Test
    public void testMult() {
        List<Token> tokens = List.of(new NumberToken(2), new NumberToken(5), OperationToken.MULT);
        Integer result = new CalcVisitor().calculate(tokens);
        assertEquals(10, result);
    }

    @Test
    public void testDiv() {
        List<Token> tokens = List.of(new NumberToken(4), new NumberToken(2), OperationToken.DIV);
        Integer result = new CalcVisitor().calculate(tokens);
        assertEquals(2, result);
    }

    @Test
    public void testInvalidParsedTokens() {
        List<Token> tokens = List.of(new NumberToken(4), OperationToken.DIV, new NumberToken(2));
        assertThrows(RuntimeException.class, () -> new CalcVisitor().calculate(tokens));
    }

    @Test
    public void testParsedTokensHaveBrace() {
        List<Token> tokens = List.of(new NumberToken(4), new NumberToken(2), OperationToken.DIV, BraceToken.OPEN);
        assertThrows(RuntimeException.class, () -> new CalcVisitor().calculate(tokens));
    }
}
