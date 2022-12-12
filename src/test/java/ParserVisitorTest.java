import org.junit.jupiter.api.Test;
import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;
import tokenizer.Tokenizer;
import visitor.ParserVisitor;
import visitor.TokenVisitor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static token.OperationToken.DIV;
import static token.OperationToken.MULT;
import static token.OperationToken.PLUS;

/**
 * @author Michael Gerasimov
 */
public class ParserVisitorTest {

    @Test
    public void testPlus() {
        assertEquals(List.of(PLUS), new ParserVisitor().parse(List.of(PLUS)));
    }

    @Test
    public void testMinus() {
        assertEquals(List.of(OperationToken.MINUS), new ParserVisitor().parse(List.of(OperationToken.MINUS)));
    }

    @Test
    public void testMult() {
        assertEquals(List.of(MULT), new ParserVisitor().parse(List.of(MULT)));
    }

    @Test
    public void testDiv() {
        assertEquals(List.of(DIV), new ParserVisitor().parse(List.of(DIV)));
    }

    @Test
    public void testNumber() {
        assertEquals(List.of(new NumberToken(2)), new ParserVisitor().parse(List.of(new NumberToken(2))));
    }

    @Test
    public void testOpen() {
        assertThrows(RuntimeException.class, () -> new ParserVisitor().parse(List.of(BraceToken.OPEN)));
    }

    @Test
    public void testClose() {
        assertEquals(List.of(), new ParserVisitor().parse(List.of(BraceToken.CLOSE)));
    }

    @Test
    public void testExpression() throws IOException {
        List<Token> tokens = new Tokenizer().tokenize(
                new ByteArrayInputStream("(23 + 10) * 5 – 3 * (32 + 5) * (10 – 4 * 5) + 8 / 2".getBytes())
        );

        List<Token> expectedTokens = List.of(
                new NumberToken(23),
                new NumberToken(10),
                PLUS,
                new NumberToken(5),
                new NumberToken(3),
                MULT,
                new NumberToken(32),
                new NumberToken(5),
                PLUS,
                MULT,
                new NumberToken(10),
                new NumberToken(4),
                new NumberToken(5),
                MULT,
                MULT,
                new NumberToken(8),
                new NumberToken(2),
                DIV,
                PLUS
        );

        assertArrayEquals(expectedTokens.toArray(), new ParserVisitor().parse(tokens).toArray());
    }
}
