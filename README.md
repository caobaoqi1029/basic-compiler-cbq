# basic-compiler-cbq

`basic-compiler-cbq` 是一个基于 Java 实现的简单计算器，采用 Pratt parsing 技术，支持加减乘除、乘方^、以及复杂运算等操作。该项目主要用于编译原理课程设计。

- 参考自 [BiliBili up 熊熊熊熊爷 的 -->如何快速入门编译原理，并快速实现一个编译器](https://www.bilibili.com/video/BV16h4y1F7FM/?spm_id_from=..search-card.all.click&vd_source=9071a50b607525e6db8ba7b49bc960f5)

![2024-03-20 18-02-23.png](assets/01.png)

## 项目结构

该项目的目录结构如下：

```tex
.
├── src
│   └── main
│       ├── java.jzxy.cbq
│       │     ├── ast	   			  # (Abstract Syntax Tree) 抽象语法树
│       │        ├── Expression.java 
│       │        ├── InfixExpression.java
│       │        │   ├── IntegerExpression.java
│       │        │   ├── Node.java
│       │        │   └── PrefixExpression.java
│       │     ├── evaluator							 	  # 计算 AST
│       │        │   └── Evaluator.java
│       │     ├── lexer 								  # 词法分析
│       │        │   └── Lexer.java
│       │        ├── Main.java
│       │     ├── object								  # Obj
│       │        │   ├── MInt.java
│       │        │   └── MObj.java
│       │     ├── parser								  # 解析器	
│       │     │      ├── InfixParseFn.java
│       │     │      ├── Parser.java
│       │     │      ├── Precedence.java
│       │     │      └── PrefixParseFn.java
│       │     └── token									  # token
│       │            ├── Token.java
│       │            └── TokenType.java 
│       └── resources
└── test												  # 测试
├── LexerTest.java 
└── TestLoggerExtension.java 
```

## 功能特点

- 支持基本的算术运算，包括加减乘除。
- 支持乘方运算 `^`。
- 支持复杂表达式的解析和计算。
- 使用 Pratt parsing 技术进行表达式解析。

> Pratt parsing 是一种自顶向下的解析技术，通过将表达式分解成嵌套的操作符和操作数来工作。每个操作符都有相应的优先级，而 Pratt 解析器则利用这些优先级来决定表达式的结构。例如，乘法操作符的优先级高于加法，因此 Pratt 解析器会先解析乘法表达式，然后再处理加法。

## 如何使用

在这个计算器项目中，词法分析器（`Lexer`）首先将输入的数学表达式字符串分解为 token 序列。然后，解析器（`Parser`）使用这些 token，根据定义的优先级和解析函数，构建一个 AST。最后，计算器使用求值器（`Evaluator`）对这个 AST 进行计算，得出最终结果。

每个操作符都有对应的解析函数，分别用于处理中缀（`InfixParseFn`）和前缀（`PrefixParseFn`）表达式。这些解析函数负责识别和构造 AST 中的相应节点。

整个计算器的工作流程大致如下：

1. 输入数学表达式。
2. 词法分析器将输入转换为 token 序列。
3. 解析器读取 token 序列，并根据操作符的优先级构建 AST。
4. 求值器遍历 AST，并计算出结果。

```java
public class Main {
    public static void main(String[] args) {
        String code = "3 + 5 * (10 - 4)";
        // 将 code 拆分为 token 并解析 tokens 并构建 AST
        Expression expression = new Parser(new Lexer(code)).parseMain();
        // 计算 AST
        System.out.println("code:  " + code + "\nresult: " + Evaluator.eval(expression));

        System.out.println("===========TokenList=============");
        // 输出 tokens
        List<Token> tokens = new Lexer(code).getTokenList();
        tokens.forEach(System.out::println);
        System.out.println("===========TokenList=============");

    }
}
```

<img src="https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/image-20240321082338534.png" alt="image-20240321082338534" style="zoom:67%;" />

<img src="https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/image-20240321082402569.png" alt="image-20240321082402569" style="zoom:67%;" />

### 环境要求

- Java 8 或以上版本。

### 编译和运行

1. 克隆项目到本地：

```shell
git clone https://github.com/caobaoqi1029/basic-compiler-cbq.git
```


## 贡献

如果您有任何改进意见或功能请求，请通过 issue 提交。

## 许可证

本项目采用 [MIT 许可证](LICENSE)。

