package jzxy.cbq;

import jzxy.cbq.ast.Expression;
import jzxy.cbq.evaluator.Evaluator;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.object.MObj;
import jzxy.cbq.parser.Parser;

public class Main {
    public static void main(String[] args) {
        String code = "(3 + 5) * 2 / 0";
        // 将 code 拆分为 tokens （词法分析阶段）
        Lexer lexer = new Lexer(code);
        // 解析 tokens 并构建 AST (语法分析阶段)
        Expression exp = new Parser(lexer).parseMain();
        // 计算 AST （语义分析、代码生成阶段）
        MObj result = Evaluator.eval(exp);

        System.out.println("code:  " + code + "\nresult：" + result);

/*        System.out.println("===========TokenList=============");
        // 输出 lexer
        List<Token> lexer = new Lexer(code).getTokenList();
        lexer.forEach(System.out::println);
        System.out.println("===========TokenList=============");*/
    }
}
