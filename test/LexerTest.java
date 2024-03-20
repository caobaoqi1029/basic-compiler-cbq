import jzxy.cbq.ast.Expression;
import jzxy.cbq.evaluator.Evaluator;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.parser.Parser;
import org.junit.jupiter.api.Test;

/**
 * LexerTest类用于测试词法分析器。
 */
public class LexerTest {

    /**
     * 测试方法，演示如何使用词法分析器、语法分析器和表达式求值器来解析并计算一个简单的表达式。
     * 该方法没有参数和返回值，因为它是在测试框架中运行的，主要目的是验证代码的正确性。
     */
    @Test
    public void test() {
        // 定义一个待解析的表达式字符串
        String code = "1-(-2*2^3)";
        // 创建一个词法分析器实例，用于分解字符串成一个个词法单元
        Lexer lexer = new Lexer(code);
        // 使用词法分析器创建一个语法分析器实例
        Parser p = new Parser(lexer);
        // 解析表达式，生成表达式树
        Expression expression = p.parseMain();
        // 输出表达式的计算结果
        System.out.println(Evaluator.eval(expression));
    }
}
