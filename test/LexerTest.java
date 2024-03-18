import jzxy.cbq.ast.Expression;
import jzxy.cbq.evaluator.Evaluator;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.parser.Parser;
import org.junit.jupiter.api.Test;

public class LexerTest {


    @Test
    public void test() {
        String code = "-1-(-2*2^3)";
        Lexer lexer = new Lexer(code);
        Parser p = new Parser(lexer);
        Expression expression = p.parseMain();
        System.out.println(Evaluator.eval(expression));
    }
}
