package jzxy.cbq.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代表一个语言的标记（Token）类
 * 一个标记通常包含标记类型（TokenType）和标记的值（value）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    /**
     * 标记的类型，例如关键字、标识符、运算符等
     */
    public TokenType type;
    /**
     * 标记的值，具体取决于标记的类型
     */
    public String value;

    /**
     * 重写 toString() 方法，用于在控制台上输出 Token 的信息
     *
     * @return Token 的信息
     */
    public String toString() {
        int typeLen = type.getName().length();
        int valueLen = value.length();
        int maxLen = Math.max(typeLen, valueLen);
        return "Token --> type: " + type.getName() +
                " ".repeat(Math.max(0, maxLen - typeLen)) +
                "\t| value: " +
                value +
                " ".repeat(maxLen - valueLen);
    }

}

