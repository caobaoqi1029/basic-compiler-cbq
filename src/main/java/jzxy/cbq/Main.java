package jzxy.cbq;

import jzxy.cbq.ast.Expression;
import jzxy.cbq.evaluator.Evaluator;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.parser.Parser;
import jzxy.cbq.token.Token;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = "3 + 5 * (10 - 4)";
        // 将 code 拆分为 token 并解析 tokens 并构建 AST
        Expression expression = new Parser(new Lexer(code)).parseMain();
        // 计算 AST
        System.out.println("code:  " + code + "result" + Evaluator.eval(expression));

        System.out.println("===========TokenList=============");
        // 输出 tokens
        List<Token> tokens = new Lexer(code).getTokenList();
        tokens.forEach(System.out::println);
        System.out.println("===========TokenList=============");

    }
}
