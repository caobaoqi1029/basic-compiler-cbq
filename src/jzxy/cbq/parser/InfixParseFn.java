package jzxy.cbq.parser;


import jzxy.cbq.ast.Expression;

public interface InfixParseFn {

    Expression parse(Expression p);
}
