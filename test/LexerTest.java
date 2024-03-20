import jzxy.cbq.ast.Expression;
import jzxy.cbq.evaluator.Evaluator;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.object.MObj;
import jzxy.cbq.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestLoggerExtension.class)
@Slf4j
public class LexerTest {
    /**
     * 测试加法运算
     */
    @Test
    public void testAddition() {
        testExpression("1 + 2", "MInt(3)");
    }

    /**
     * 测试减法运算
     */
    @Test
    public void testSubtraction() {
        testExpression("3 - 2", "MInt(1)");
    }

    /**
     * 测试乘法运算
     */
    @Test
    public void testMultiplication() {
        testExpression("2 * 3", "MInt(6)");
    }

    /**
     * 测试除法运算
     */
    @Test
    public void testDivision() {
        testExpression("6 / 2", "MInt(3)");
    }

    /**
     * 测试指数运算
     */
    @Test
    public void testExponentiation() {
        testExpression("2 ^ 3", "MInt(8)");
    }

    /**
     * 测试括号
     */
    @Test
    public void testParentheses() {
        testExpression("(2 + 3) * 4", "MInt(20)");
    }

    /**
     * 测试负数
     */
    @Test
    public void testNegativeNumber() {
        testExpression("-5", "MInt(-5)");
    }

    /**
     * 测试复杂表达式
     */
    @Test
    public void testComplexExpression() {
        testExpression("2 * (3 + 4) - 5 / 2 ^ 2", "MInt(13)");
    }

    /**
     * 初始化词法分析器
     *
     * @param exp 表达式
     * @return Expression
     */
    private Expression init(String exp) {
        Parser p = new Parser(new Lexer(exp));
        return p.parseMain();
    }

    /**
     * 测试表达式
     *
     * @param exp    表达式
     * @param result 预期结果
     */
    private void testExpression(String exp, String result) {
        MObj eval = Evaluator.eval(init(exp));

        log.info("exp: " + exp);
        log.info("eval: " + eval);
        log.info("result: " + result);

        assert eval != null;
        Assertions.assertEquals(result, eval.toString(), "结果应为： " + result);
    }

}
