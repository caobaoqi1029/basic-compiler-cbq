package jzxy.cbq.token;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌类型枚举，用于标识解析过程中的不同令牌。
 */
public enum TokenType {

    NUM("NUM"), // 数字类型
    LPAREN("("), // 左括号
    RPAREN(")"), // 右括号

    MINUS("-"), // 减号
    PLUS("+"), // 加号

    ASTERISK("*"), // 乘号
    HAT("^"), // 幂运算符

    SLASH("/"), // 除号

    EOF("EOF"); // 文件结束符

    /**
     * 构造函数，初始化令牌类型。
     *
     * @param name 令牌的名称。
     */
    TokenType(String name) {
        this.name = name;
    }

    private String name; // 令牌的内部名称。

    // 创建一个静态映射，用于快速查找枚举值。
    static Map<String, TokenType> map = new HashMap<>();

    static {
        // 初始化映射，将字符与相应的枚举值关联起来。
        map.put("(", LPAREN);
        map.put(")", RPAREN);
        map.put("-", MINUS);
        map.put("+", PLUS);
        map.put("*", ASTERISK);
        map.put("/", SLASH);
    }

    /**
     * 重写toString方法，提供更友好的字符串表示形式。
     *
     * @return 令牌类型的字符串表示。
     */
    @Override
    public String toString() {
        return "TokenType(" + this.name() + ")";
    }
}

