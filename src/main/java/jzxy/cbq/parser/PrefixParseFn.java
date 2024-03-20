package jzxy.cbq.parser;


import jzxy.cbq.ast.Expression;

/**
 * 前缀解析函数接口。
 * 该接口定义了一个方法，用于解析表达式。
 */
public interface PrefixParseFn {
    /**
     * 解析表达式。
     * 该方法不接受参数，返回一个表达式对象。
     *
     * @return 表达式对象，具体类型依赖于实现。
     */
    Expression parse();
}

