package tokenizer;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Gerasimov
 */
public class Tokenizer {
    private TokenizerState tokenizerState;
    private List<Token> tokens;
    private StringBuilder numberBuffer;

    private void nextChar(int codePoint) {
        switch (tokenizerState) {
            case EXPECTED_NUMBER:
                if (Character.isDigit(codePoint)) {
                    numberBuffer.append((char) codePoint);
                } else {
                    tokenizerState = TokenizerState.EXPECTED_NON_NUMBER;
                    tokens.add(new NumberToken(Integer.valueOf(numberBuffer.toString())));
                    numberBuffer = new StringBuilder();
                    nextChar(codePoint);
                }
                break;
            case EXPECTED_NON_NUMBER:
                if (Character.isDigit(codePoint)) {
                    tokenizerState = TokenizerState.EXPECTED_NUMBER;
                    numberBuffer.append((char) codePoint);
                } else if (!Character.isWhitespace(codePoint)) {
                    switch (codePoint) {
                        case '(' -> tokens.add(BraceToken.OPEN);
                        case ')' -> tokens.add(BraceToken.CLOSE);
                        case '+' -> tokens.add(OperationToken.PLUS);
                        case '-' -> tokens.add(OperationToken.MINUS);
                        case '*' -> tokens.add(OperationToken.MULT);
                        case '/' -> tokens.add(OperationToken.DIV);
                    }
                }
                break;
        }
    }

    public List<Token> tokenize(InputStream in) throws IOException {
        tokenizerState = TokenizerState.EXPECTED_NON_NUMBER;
        tokens = new ArrayList<>();
        numberBuffer = new StringBuilder();

        int codePoint;
        while ((codePoint = in.read()) != -1) {
            nextChar(codePoint);
        }

        if (numberBuffer.length() != 0) {
            tokens.add(new NumberToken(Integer.valueOf(numberBuffer.toString())));
        }

        return tokens;
    }

    private enum TokenizerState {
        EXPECTED_NUMBER, EXPECTED_NON_NUMBER
    }
}
