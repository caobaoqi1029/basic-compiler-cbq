package jzxy.cbq.ast;

/**
 * 表示一个中缀表达式的类，继承自 Expression 类
 * 这个类用于表示带有操作符和两个操作数的中缀表达式
 */
public class InfixExpression extends Expression {
    /**
     * 表达式中的操作符
     */
    public String operator;
    /**
     * 表达式中的左操作数
     */
    public Expression left;
    /**
     * 表达式中的右操作数
     */
    public Expression right;

    /**
     * 重写 toString 方法，以中缀表达式的格式返回表达式的字符串表示
     *
     * @return 表达式的字符串表示，格式为"(左操作数 操作符 右操作数)"，并在行尾添加换行符
     */
    @Override
    public String toString() {

        return "(" +
                left +
                " " + operator + " " +
                right +
                ")" +
                "\n";
    }
}

