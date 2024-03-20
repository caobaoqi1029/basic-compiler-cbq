package jzxy.cbq.parser;

import jzxy.cbq.ast.*;
import jzxy.cbq.lexer.Lexer;
import jzxy.cbq.token.Token;
import jzxy.cbq.token.TokenType;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * 解析器类，用于解析词法分析器生成的令牌流，构建表达式树。
 */
public class Parser {

    final Lexer lexer; // 词法分析器

    Token cur, peek; // 当前令牌和下一个令牌

    // 中缀解析函数映射
    HashMap<TokenType, InfixParseFn> infixParseFnHashMap = new HashMap<>();
    // 前缀解析函数映射
    HashMap<TokenType, PrefixParseFn> prefixParseFnHashMap = new HashMap<>();

    /**
     * 构造函数，初始化解析器。
     *
     * @param lexer 词法分析器实例
     */
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        // 初始化前缀解析函数映射
        prefixParseFnHashMap.put(TokenType.NUM, this::parseInteger);
        prefixParseFnHashMap.put(TokenType.MINUS, this::parsePrefixExpression);
        prefixParseFnHashMap.put(TokenType.LPAREN, this::parseGroupExpression);

        // 初始化中缀解析函数映射
        infixParseFnHashMap.put(TokenType.PLUS, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.MINUS, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.ASTERISK, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.SLASH, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.HAT, this::parseInfixExpression);
        // 预读两个令牌
        nextToken();
        nextToken();
    }

    /**
     * 获取下一个令牌。
     */
    void nextToken() {
        cur = peek;
        peek = lexer.nextToken();
    }

    /**
     * 检查当前令牌是否为指定类型。
     *
     * @param type 令牌类型
     * @return 如果当前令牌类型与指定类型匹配，则返回true，否则返回false
     */
    private boolean curTokenIs(TokenType type) {
        return cur.type == type;
    }

    /**
     * 检查下一个令牌是否为指定类型。
     *
     * @param type 令牌类型
     * @return 如果下一个令牌类型与指定类型匹配，则返回true，否则返回false
     */
    private boolean peekTokenIs(TokenType type) {
        return peek.type == type;
    }

    /**
     * 解析主表达式。
     *
     * @return 解析后的表达式树根节点
     */
    public Expression parseMain() {
        return parseExpression(Precedence.LOWEST);
    }

    /**
     * 获取下一个令牌的优先级。
     *
     * @return 令牌的优先级
     */
    private int peekPrecedence() {
        return Precedence.getPrecedence(peek.type);
    }

    /**
     * 根据当前优先级解析表达式。
     *
     * @param precedence 当前解析表达式的优先级
     * @return 解析后的表达式
     */
    public Expression parseExpression(int precedence) {
        PrefixParseFn prefixParseFn = prefixParseFnHashMap.get(cur.type);
        if (prefixParseFn == null) {
            throw new RuntimeException("No prefix parse function for " + cur.type);
        }
        Expression left = prefixParseFn.parse();

        while (!peekTokenIs(TokenType.EOF) && precedence < peekPrecedence()) {
            InfixParseFn infixParseFn = infixParseFnHashMap.get(peek.type);
            if (infixParseFn == null) {
                return left;
            }
            nextToken();
            left = infixParseFn.parse(left);
        }

        return left;
    }

    /**
     * 解析中缀表达式。
     *
     * @param left 左侧表达式
     * @return 中缀表达式节点
     */
    Expression parseInfixExpression(Expression left) {
        InfixExpression infixExpression = new InfixExpression();
        infixExpression.left = left;
        infixExpression.operator = cur.value;
        int precedence = Precedence.getPrecedence(cur.type);
        nextToken();
        infixExpression.right = parseExpression(precedence);
        return infixExpression;
    }

    /**
     * 解析前缀表达式。
     *
     * @return 前缀表达式节点
     */
    Expression parsePrefixExpression() {
        PrefixExpression prefixExpression = new PrefixExpression();
        prefixExpression.operator = cur.value;
        nextToken();
        prefixExpression.right = parseExpression(Precedence.PREFIX);

        return prefixExpression;
    }

    /**
     * 解析整数表达式。
     *
     * @return 整数表达式节点
     */
    public Expression parseInteger() {
        BigInteger val = new BigInteger(cur.value);

        IntegerExpression integerExpression = new IntegerExpression(val);

        return integerExpression;
    }

    /**
     * 解析括号表达式。
     *
     * @return 括号内的表达式节点
     */
    Expression parseGroupExpression() {
        nextToken();
        Expression exp = parseExpression(Precedence.LOWEST);
        nextToken();
        return exp;
    }

}

