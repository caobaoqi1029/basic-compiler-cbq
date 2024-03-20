package jzxy.cbq.evaluator;

import jzxy.cbq.ast.InfixExpression;
import jzxy.cbq.ast.IntegerExpression;
import jzxy.cbq.ast.Node;
import jzxy.cbq.ast.PrefixExpression;
import jzxy.cbq.object.MInt;
import jzxy.cbq.object.MObj;

import java.util.Objects;

/**
 * Evaluator类用于计算表达式节点的值。
 */
public class Evaluator {

    /**
     * 对给定的节点进行求值。
     *
     * @param node 表达式节点，是抽象语法树中的一个节点。
     * @return 求值结果，返回MObj类型，可能是整数（MInt）或其他计算结果。
     */
    public static MObj eval(Node node) {

        // 如果节点是整数表达式，直接返回其值
        if (node instanceof IntegerExpression e) {
            return new MInt(e.getValue());
        } else if (node instanceof PrefixExpression pre) {
            // 处理前缀表达式，如"-x"形式
            if (Objects.equals(pre.operator, "-")) {
                return minus(node);
            } else if (Objects.equals(pre.operator, "+")) {
                return eval(pre.right);
            }
        } else if (node instanceof InfixExpression infix) {
            // 处理中缀表达式，如"x + y"形式
            var left = eval(infix.left);
            var right = eval(infix.right);

            return op(left, right, infix.operator);
        }

        return null;
    }

    /**
     * 根据操作符和操作数执行计算。
     *
     * @param left  左操作数。
     * @param right 右操作数。
     * @param operator 操作符。
     * @return 计算结果，返回MObj类型。
     */
    public static MObj op(MObj left, MObj right, String operator) {
        // 保证操作数为整数类型，然后根据操作符执行相应的计算
        if (left instanceof MInt leftInt && right instanceof MInt rightInt) {
            return switch (operator) {
                case "+" -> new MInt(leftInt.value.add(rightInt.value));
                case "-" -> new MInt(leftInt.value.subtract(rightInt.value));
                case "*" -> new MInt(leftInt.value.multiply(rightInt.value));
                case "/" -> new MInt(leftInt.value.divide(rightInt.value));
                case "^" -> new MInt(leftInt.value.pow(rightInt.value.intValue()));
                default -> null;
            };
        }
        return null;
    }

    /**
     * 处理前缀负号操作符。
     *
     * @param node 要计算的节点，预期为前缀表达式节点。
     * @return 计算结果，返回MObj类型。
     */
    public static MObj minus(Node node) {
        // 从节点中提取操作数并对其进行负号运算
        if (node instanceof PrefixExpression ) {
            MObj m = eval(((PrefixExpression) node).right);
            if (m instanceof MInt mint) {
                return new MInt(mint.value.negate());
            }
        }
        return null;
    }
}
