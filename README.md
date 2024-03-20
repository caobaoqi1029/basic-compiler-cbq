# basic-compiler-cbq

`basic-compiler-cbq` 是一个基于 Java 实现的简单计算器，采用 Pratt parsing 技术，支持加减乘除、乘方^、以及复杂运算等操作。该项目主要用于编译原理课程设计。

- 参考自 [BiliBili up 熊熊熊熊爷 的 -->如何快速入门编译原理，并快速实现一个编译器](https://www.bilibili.com/video/BV16h4y1F7FM/?spm_id_from=..search-card.all.click&vd_source=9071a50b607525e6db8ba7b49bc960f5)

![2024-03-20 18-02-23.png](assets%2F2024-03-20%2018-02-23.png)

## 项目结构

该项目的目录结构如下：

```sh
.
├── src
│   └── main
│       ├── java.jzxy.cbq
│       │     ├── ast
│       │        ├── Expression.java 
│       │        ├── InfixExpression.java
│       │        │   ├── IntegerExpression.java
│       │        │   ├── Node.java
│       │        │   └── PrefixExpression.java
│       │     ├── evaluator
│       │        │   └── Evaluator.java
│       │     ├── lexer
│       │        │   └── Lexer.java
│       │        ├── Main.java
│       │     ├── object
│       │        │   ├── MInt.java
│       │        │   └── MObj.java
│       │     ├── parser
│       │     │      ├── InfixParseFn.java
│       │     │      ├── Parser.java
│       │     │      ├── Precedence.java
│       │     │      └── PrefixParseFn.java
│       │     └── token
│       │            ├── Token.java
│       │            └── TokenType.java 
│       └── resources
└── test
├── LexerTest.java 测试文件
└── TestLoggerExtension.java 测试拓展
```


## 功能特点

- 支持基本的算术运算，包括加减乘除。
- 支持乘方运算 `^`。
- 支持复杂表达式的解析和计算。
- 使用 Pratt parsing 技术进行表达式解析。

## 如何使用

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

