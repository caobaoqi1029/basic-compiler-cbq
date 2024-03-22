import jzxy.cbq.ast.Expression;
import jzxy.cbq.evaluator.Evaluator;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.object.MObj;
import jzxy.cbq.parser.Parser;
import jzxy.cbq.token.Token;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

/**
 * 流程测试
 * 编译器的基本流程通常包括几个主要阶段：
 * 1. 词法分析（Lexical Analysis）
 * 2. 语法分析（Syntax Analysis）
 * 3. 语义分析（Semantic Analysis）
 * 4. 中间代码生成（Intermediate Code Generation）
 * 5. 优化（Optimization）
 * 6. 目标代码生成（Code Generation）
 * P.S. 因为本项目是一个简易计算器，因此不包含 中间代码生成、优化 阶段
 */
@ExtendWith(TestLoggerExtension.class)
@Slf4j
public class ProcessTest {
    private static String code = "(1 + 2) * 3";

    /**
     * 1. 词法分析（Lexing)
     * - 输入：用户输入的数学表达式字符串
     * - 处理：Lexer 类将字符串转换为 token 序列。每个 token 是表达式的一个原子部分，如数字、运算符或括号
     * - 输出：一系列 token
     */
    @Test
    void testLexicalAnalysis() {
        Lexer lexer = new Lexer(code);
        List<Token> tokenList = lexer.getTokenList();
        tokenList.forEach(System.out::println);
    }

    /**
     * 语法分析（Parsing）
     * - 输入：词法分析阶段输出的 token 序列
     * - 处理：Parser 类读取 tokens 并根据语法规则构建抽象语法树（AST）Pratt parsing 技术在这里发挥作用，使用不同的解析函数来处理不同的 token 类型（如前缀和中缀）
     * - 输出：一个 AST，它表示了输入表达式的结构
     */
    @Test
    void testSyntaxAnalysis() {
        Lexer lexer = new Lexer(code);
        Expression exp1 = new Parser(lexer).parseMain();

        System.out.println(exp1);
    }

    /**
     * 语义分析（Semantic Analysis）
     * - 输入：语法分析阶段构建的 AST
     * - 处理：这个阶段通常涉及检查 AST 是否符合语义规则，如变量是否已定义，类型是否匹配等
     * - 输出：经过验证的 AST
     */
    @Test
    void testSemanticAnalysis() {
        code = "1 / 0";
        Lexer lexer = new Lexer(code);
        Expression ast = new Parser(lexer).parseMain();
        ArithmeticException aThrows = Assertions.assertThrows(ArithmeticException.class, () -> Evaluator.eval(ast));

        System.out.println("测试通过，异常信息: " + aThrows.getMessage());
    }

    /**
     * 求值（Evaluation)
     * - 输入：经过语义分析的 AST
     * - 处理：Evaluator 类遍历 AST，并执行计算。递归地计算每个节点的值，根据操作符节点的类型执行相应的数学运算
     * - 输出：最终的计算结果
     */
    @Test
    void testCodeGeneration() {
        Lexer lexer = new Lexer(code);
        Expression ast = new Parser(lexer).parseMain();
        MObj result = Evaluator.eval(ast);
        System.out.println("result = " + result);
    }

}
