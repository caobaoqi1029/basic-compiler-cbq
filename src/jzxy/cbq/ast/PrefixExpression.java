package jzxy.cbq.ast;

/**
 * 前缀表达式类，继承自表达式类。
 * 这个类用于表示一个前缀运算符和一个右操作数构成的表达式。
 */
public class PrefixExpression extends Expression {
    public String operator; // 运算符
    public Expression right; // 右操作数

    /**
     * 重写toString方法，以前缀形式返回表达式的字符串表示。
     *
     * @return 表达式的字符串表示，格式为"(运算符 右操作数)"，后面跟一个换行符。
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("("); // 开始构建表达式的字符串表示
        sb.append(operator); // 添加运算符
        sb.append(right); // 添加右操作数
        sb.append(")"); // 结束构建

        sb.append("\n"); // 添加换行符，为了格式化输出

        return sb.toString();
    }
}

