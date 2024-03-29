package jzxy.cbq.lexer;

import jzxy.cbq.token.Token;
import jzxy.cbq.token.TokenType;

import java.util.LinkedList;
import java.util.List;

/**
 * 词法分析器类，用于分析输入字符串中的一个个 Token
 */
public class Lexer {
    /**
     * 输入的字符串
     */
    final String input;
    /**
     * 当前字符
     */
    char ch;
    /**
     * 当前位置和下一个位置
     */
    int curPos, peekPos;

    /**
     * Lexer 构造函数。
     *
     * @param input 输入的字符串。
     */
    public Lexer(String input) {
        this.input = input;
        curPos = 0;
        peekPos = 0;
        readChar(); // 读取第一个字符
    }

    /**
     * 获取下一个字符，但不改变当前字符
     *
     * @return 下一个字符
     */
    public char peekChar() {
        return input.charAt(peekPos);
    }

    /**
     * 获取下一个 Token
     *
     * @return 分析出的下一个 Token
     */
    public Token nextToken() {
        Token token;
        skipWhitespace(); // 跳过空白字符
        switch (ch) {
            case '+' -> token = new Token(TokenType.PLUS, "+");
            case '-' -> token = new Token(TokenType.MINUS, "-");
            case '*' -> token = new Token(TokenType.ASTERISK, "*");
            case '/' -> token = new Token(TokenType.SLASH, "/");
            case '(' -> token = new Token(TokenType.LPAREN, "(");
            case ')' -> token = new Token(TokenType.RPAREN, ")");
            case '^' -> token = new Token(TokenType.HAT, "^");
            case 0 -> token = new Token(TokenType.EOF, "");
            default -> {
                if (isDigit(ch)) {
                    String num = readNum(); // 读取数字
                    token = new Token(TokenType.NUM, num);
                    return token;
                } else {
                    throw new RuntimeException("Lexer error"); // 词法错误
                }
            }
        }
        readChar(); // 读取下一个字符
        return token;
    }

    /**
     * 读取数字字符串，直到非数字字符
     *
     * @return 读取到的数字字符串
     */
    String readNum() {
        StringBuilder num = new StringBuilder();
        while (isDigit(ch)) {
            num.append(ch);
            readChar();
        }
        return num.toString();
    }

    /**
     * 判断字符是否为数字
     *
     * @param c 待判断的字符
     * @return 如果是数字返回 true，否则返回 false
     */
    boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 跳过所有的空白字符
     */
    void skipWhitespace() {
        while (hasNext()) {
            if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
                readChar();
            } else break;
        }
    }

    /**
     * 检查是否还有下一个字符
     *
     * @return 如果还有字符未处理，返回 true，否则返回 false
     */
    public boolean hasNext() {
        return peekPos <= input.length();
    }

    /**
     * 读取下一个字符，并更新当前位置和下一个位置
     */
    void readChar() {
        if (peekPos >= input.length()) {
            ch = 0;
        } else {
            ch = input.charAt(peekPos);
        }
        curPos = peekPos;
        peekPos += 1;
    }
 /**
     * 生成并返回一个包含所有 Token 的列表。
     *
     * @return 包含从输入字符串分析出的所有 Token 的 List
     */
    public List<Token> getTokenList() {
        List<Token> tokens = new LinkedList<>();
        while (hasNext()) {
            Token token = nextToken();
            if (token.type != TokenType.EOF) { // 不将 EOF Token 加入列表
                tokens.add(token);
            } else {
                break; // 遇到 EOF 结束循环
            }
        }
        return tokens;
    }

}

