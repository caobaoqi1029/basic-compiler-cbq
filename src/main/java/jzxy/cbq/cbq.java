package jzxy.cbq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cbq {
    private static final Map<String, Integer> keyWords = new HashMap<>();
    static {
        keyWords.put("if", 3);
        keyWords.put("then", 3);
        // 添加其他关键字和它们的编码
    }

    private static final Map<String, Integer> operators = new HashMap<>();
    static {
        operators.put("=", 4);
        operators.put("+", 4);
        operators.put("-", 4);
        operators.put("*", 4);
        operators.put("/", 4);
        // 添加其他运算符和它们的编码
    }

    private static final Map<String, Integer> delimiters = new HashMap<>();
    static {
        delimiters.put(";", 5);
        delimiters.put("(", 5);
        delimiters.put(")", 5);
        // 添加其他界符和它们的编码
    }

    private static final String regexForWord = "\\p{L}+";
    private static final String regexForNumber = "\\d+";
    private static final Pattern wordPattern = Pattern.compile(regexForWord);
    private static final Pattern numberPattern = Pattern.compile(regexForNumber);
    private static final List<Token> tokens = new ArrayList<>();

    public static void main(String[] args) {
        String code = "if i=5 then x=y;";

        latex(code);
    }

    private static void latex(String code) {
        code = getString(code, keyWords);

        code = getString(code, operators);

        code = getString(code, delimiters);

        Matcher matcher = wordPattern.matcher(code);
        while (matcher.find()) {
            tokens.add(new Token(1, matcher.group())); // 假设标识符编码为 1
        }

        matcher = numberPattern.matcher(code);
        while (matcher.find()) {
            tokens.add(new Token(2, matcher.group())); // 假设常数编码为 2
        }

        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }

    private static String getString(String code, Map<String, Integer> keyWords) {
        for (Map.Entry<String, Integer> entry : keyWords.entrySet()) {
            String keyword = entry.getKey();
            while (code.contains(keyword)) {
                int start = code.indexOf(keyword);
                int end = start + keyword.length();
                tokens.add(new Token(entry.getValue(), keyword));
                code = code.substring(0, start) + code.substring(end);
            }
        }
        return code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Token {
        Integer categoryCode;
        String value;

        @Override
        public String toString() {
            return String.format("(%d, '%s')", categoryCode, value);
        }
    }
}
