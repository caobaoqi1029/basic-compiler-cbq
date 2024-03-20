package jzxy.cbq.object;

import java.math.BigInteger;

/**
 * MInt类，继承自MObj类，用于表示一个大整数。
 */
public class MInt extends MObj {

    /**
     * 大整数值。
     */
    public BigInteger value;

    /**
     * 构造函数，从字符串创建MInt对象。
     *
     * @param value 字符串表示的大整数。
     */
    public MInt(String value) {
        this.value = new BigInteger(value);
    }

    /**
     * 构造函数，从BigInteger创建MInt对象。
     *
     * @param value BigInteger表示的大整数。
     */
    public MInt(BigInteger value) {
        this.value = value;
    }

    /**
     * 重写toString方法，返回MInt对象的字符串表示。
     *
     * @return 表示MInt对象的字符串。
     */
    @Override
    public String toString() {
        return "MInt(" + value + ")";
    }
}
