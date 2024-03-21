package jzxy.cbq.object;

import java.math.BigInteger;

/**
 * MInt 类，继承自 MObj 类，用于表示一个大整数
 */
public class MInt extends MObj {

    /**
     * 大整数值
     */
    public BigInteger value;

    /**
     * 构造函数，从字符串创建 MInt 对象
     *
     * @param value 字符串表示的大整数
     */
    public MInt(String value) {
        this.value = new BigInteger(value);
    }

    /**
     * 构造函数，从 BigInteger 创建 MInt 对象
     *
     * @param value BigInteger表示的大整数
     */
    public MInt(BigInteger value) {
        this.value = value;
    }

    /**
     * 重写 toString 方法，返回 MInt 对象的字符串表示
     *
     * @return 表示 MInt 对象的字符串
     */
    @Override
    public String toString() {
        return "MInt(" + value + ")";
    }
}
