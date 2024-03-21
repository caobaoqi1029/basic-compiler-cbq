package jzxy.cbq.ast;

/**
 * 前缀表达式类，继承自表达式类
 * 这个类用于表示一个前缀运算符和一个右操作数构成的表达式
 */
public class PrefixExpression extends Expression {
    /**
     * 运算符
     */
    public String operator;
    /**
     * 右操作数
     */
    public Expression right;

    /**
     * 重写 toString 方法，以前缀形式返回表达式的字符串表示
     *
     * @return 表达式的字符串表示，格式为"(运算符 右操作数)"，后面跟一个换行符
     */
    @Override
    public String toString() {

        return "(" +
                operator +
                right +
                ")" +
                "\n";
    }
}

