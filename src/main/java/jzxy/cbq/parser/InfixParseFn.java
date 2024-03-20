package jzxy.cbq.parser;


import jzxy.cbq.ast.Expression;

/**
 * 中缀解析函数接口。
 * 该接口定义了一个方法，用于解析表达式。
 */
public interface InfixParseFn {

    /**
     * 解析给定的表达式。
     * @param p 待解析的表达式对象。
     * @return 解析后的表达式对象。
     */
    Expression parse(Expression p);
}

