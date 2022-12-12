import token.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * @author Michael Gerasimov
 */
public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        InputStream in = new ByteArrayInputStream(line.getBytes());

        List<Token> tokens = new Tokenizer().tokenize(in);
        System.out.println("Tokens:");
        new PrintVisitor(System.out).print(tokens);
        System.out.println();

        List<Token> parsedTokens = new ParserVisitor().parse(tokens);
        System.out.println("PRN:");
        new PrintVisitor(System.out).print(parsedTokens);
        System.out.println();

        System.out.println("Calculation:");
        System.out.println(new CalcVisitor().calculate(parsedTokens));
    }
}
