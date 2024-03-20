package jzxy.cbq.token;

/**
 * 代表一个语言的标记（Token）类。
 * 一个标记通常包含标记类型（TokenType）和标记的值（value）。
 */
public class Token {

    // 标记的类型，例如关键字、标识符、运算符等。
    public TokenType type;
    // 标记的值，具体取决于标记的类型。
    public String value;

    /**
     * 构造函数，用于创建一个具有指定类型和值的Token。
     *
     * @param type 标记的类型。
     * @param value 标记的值。
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * 无参数构造函数，用于创建一个类型和值尚未指定的Token。
     */
    public Token() {

    }

    /**
     * 重写toString方法，以便于以"Token(类型, 值)"的格式输出标记。
     *
     * @return 表示Token的字符串。
     */
    @Override
    public String toString() {
        return "Token(" + type + ", " + value + ")";
    }
}

