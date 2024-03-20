package jzxy.cbq.ast;

import java.math.BigInteger;

/**
 * 表示一个整数表达式的类，继承自Expression类。
 */
public class IntegerExpression extends Expression {

    private BigInteger value; // 存储整数值

    /**
     * 构造函数，通过字符串初始化整数表达式。
     *
     * @param value 表示整数的字符串。
     */
    public IntegerExpression(String value) {
        this.value = new BigInteger(value);
    }

    /**
     * 构造函数，通过BigInteger初始化整数表达式。
     *
     * @param val 表示整数的BigInteger对象。
     */
    public IntegerExpression(BigInteger val) {
        this.value = val;
    }

    /**
     * 获取整数表达式的值。
     *
     * @return 返回表达式的BigInteger值。
     */
    public BigInteger getValue() {
        return value;
    }
}

