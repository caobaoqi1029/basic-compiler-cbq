
package jzxy.cbq;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cbq {
    private static final List<String> keyWords = Arrays.asList("if", "then");
    private static final String regexForIdentifier = "[a-zA-Z_][a-zA-Z_0-9]*";
    private static final String regexForNumber = "\\d+";
    private static final Pattern identifierPattern = Pattern.compile(regexForIdentifier);
    private static final Pattern numberPattern = Pattern.compile(regexForNumber);
    private static final Map<String, Integer> keywordMap = createKeywordMap();
    private static final Map<Character, Integer> operatorMap = createOperatorMap();
    private static final Map<Character, Integer> delimiterMap = createDelimiterMap();
    private static final List<Token> tokens = new ArrayList<>();

    static {
        operatorMap.put('=', 4);
        delimiterMap.put(';', 5);
    }

    public static void main(String[] args) {
        String code = "if i=5 then x=y;";

        latex(code);
    }

    /**
     * 词法分析
     *
     * @param code code
     */
    private static void latex(String code) {
        // 先处理关键字
        for (String keyword : keyWords) {
            int index = code.indexOf(keyword);
            while (index != -1) {
                tokens.add(new Token(Token.TokenType.KEYWORD, keyword, keywordMap.get(keyword)));
                // 移除已识别的关键字，防止后续将其识别为标识符
                code = code.substring(0, index) + code.substring(index + keyword.length());
                index = code.indexOf(keyword);
            }
        }

        // 继续处理标识符、常量、运算符和界符
        Matcher matcher = identifierPattern.matcher(code);
        while (matcher.find()) {
            tokens.add(new Token(Token.TokenType.IDENTIFIER, matcher.group()));
        }

        matcher = numberPattern.matcher(code);
        while (matcher.find()) {
            tokens.add(new Token(Token.TokenType.CONSTANT, matcher.group()));
        }

        for (char c : code.toCharArray()) {
            if (operatorMap.containsKey(c)) {
                tokens.add(new Token(Token.TokenType.OPERATOR, Character.toString(c), operatorMap.get(c)));
            } else if (delimiterMap.containsKey(c)) {
                tokens.add(new Token(Token.TokenType.DELIMITER, Character.toString(c), delimiterMap.get(c)));
            }
        }

        for (Token token : tokens) {
            System.out.println("\t" + token.getType().toString().toLowerCase() + ": " + token.getValue() + " (" + token.getCode() + ")");
        }
        System.out.println(); // 添加一个空行

        boolean isFirstInGroup = true;
        for (Token.TokenType type : Token.TokenType.values()) {
            List<Token> groupTokens = tokens.stream()
                    .filter(token -> token.getType() == type)
                    .toList();

            if (!groupTokens.isEmpty()) {
                if (!isFirstInGroup) {
                    System.out.println(); // 在不同类型的词法单元间添加空行
                }
                isFirstInGroup = false;
                System.out.println(type.toString().toLowerCase() + "s:");
                for (Token token : groupTokens) {
                    System.out.println("\t- " + token.getValue() + " (" + token.getCode() + ")");
                }
            }
        }
    }

    @Data
    @NoArgsConstructor
    static class Token {
        enum TokenType {
            KEYWORD(3),
            IDENTIFIER(1),
            CONSTANT(2),
            OPERATOR(4),
            DELIMITER(5);

            private final int code;

            TokenType(int code) {
                this.code = code;
            }
        }

        /**
         * 类型
         */
        TokenType type;
        /**
         * 值
         */
        String value;
        /**
         * 编码
         */
        int code;

        // 新增构造函数，接受类型和编码
        Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
            this.code = type.code;
        }

        Token(TokenType type, String value, int code) {
            this.type = type;
            this.value = value;
            this.code = code;
        }
    }

    // 创建关键字编码映射的方法
    private static Map<String, Integer> createKeywordMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("if", 3);
        map.put("then", 3);
        return map;
    }

    // 创建运算符编码映射的方法
    private static Map<Character, Integer> createOperatorMap() {
        return new HashMap<>();
    }

    // 创建界符编码映射的方法
    private static Map<Character, Integer> createDelimiterMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put(';', 5);
        return map;
    }
}
