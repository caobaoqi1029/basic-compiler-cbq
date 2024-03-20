package jzxy.cbq.parser;

import jzxy.cbq.token.TokenType;

import java.util.HashMap;

/**
 * 该类定义了运算符的优先级。
 */
public class Precedence {

    // 定义了从低到高的优先级常量
    public static final int LOWEST = 0; // 最低优先级
    public static final int EQUALS = 1; // 相等运算符的优先级
    public static final int LESS_GREATER = 2; // 比较运算符的优先级
    public static final int SUM = 3; // 加减运算的优先级
    public static final int PRODUCT = 4; // 乘除运算的优先级
    public static final int POWER = 5; // 幂运算的优先级
    public static final int PREFIX = 6; // 前缀运算符（如负号）的优先级
    public static final int CALL = 7; // 函数调用的优先级

    // 存储每个TokenType对应的优先级
    static HashMap<TokenType, Integer> precedences = new HashMap<>();

    // 初始化precedences映射，设置各个运算符的优先级
    static {
        precedences.put(TokenType.PLUS, SUM); // 加号
        precedences.put(TokenType.MINUS, SUM); // 减号
        precedences.put(TokenType.SLASH, PRODUCT); // 除号
        precedences.put(TokenType.ASTERISK, PRODUCT); // 乘号
        precedences.put(TokenType.HAT, POWER); // 幂运算符
        precedences.put(TokenType.LPAREN, CALL); // 左括号
    }

    /**
     * 获取给定TokenType的优先级。
     *
     * @param t TokenType枚举，代表具体的运算符类型。
     * @return 对应于TokenType的优先级整数。如果没有对应项，则返回最低优先级。
     */
    static int getPrecedence(TokenType t) {
        return precedences.getOrDefault(t, LOWEST);
    }

}

