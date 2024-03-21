package jzxy.cbq.ast;

import lombok.Getter;

import java.math.BigInteger;

/**
 * 表示一个整数表达式的类，继承自 Expression 类
 */
@Getter
public class IntegerExpression extends Expression {

    /**
     * -- GETTER --
     * 获取整数表达式的值
     */
    private final BigInteger value;

    /**
     * 构造函数，通过字符串初始化整数表达式
     *
     * @param value 表示整数的字符串
     */
    public IntegerExpression(String value) {
        this.value = new BigInteger(value);
    }

    /**
     * 构造函数，通过 BigInteger 初始化整数表达式
     *
     * @param val 表示整数的 BigInteger 对象
     */
    public IntegerExpression(BigInteger val) {
        this.value = val;
    }

}

