package jzxy.cbq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cbq {
    private static final List<String> keyWords = Arrays.asList("if", "then", "print", "'", "\"", "(", ")", "+", "-", "*", "/");
    private static final String regexForWord = "[a-zA-Z]+";
    private static final String regexForNumber = "\\d+";
    private static final Pattern wordPattern = Pattern.compile(regexForWord);
    private static final Pattern numberPattern = Pattern.compile(regexForNumber);
    private static final List<Token> tokens = new ArrayList<>();


    public static void main(String[] args) {
        String code = "if 1 + 2 then print('ji太美')";

        latex(code);
    }

    /**
     * 词法分析
     *
     * @param code code
     */
    private static void latex(String code) {

        Matcher matcher = wordPattern.matcher(code);
        while (matcher.find()) {
            tokens.add(new Token("单词", matcher.group()));
        }

        matcher = numberPattern.matcher(code);
        while (matcher.find()) {
            tokens.add(new Token("数字", matcher.group()));
        }

        for (String keyword : keyWords) {
            if (code.contains(keyword)) {
                int start = code.indexOf(keyword);
                int end = start + keyword.length();
                tokens.add(new Token("关键字", code.substring(start, end)));
            }
        }

        for (Token token : tokens) {
            System.out.println(token.category + ": " + token.value);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Token {
        /**
         * 类别
         */
        String category;
        /**
         * 值
         */
        String value;
    }
}
