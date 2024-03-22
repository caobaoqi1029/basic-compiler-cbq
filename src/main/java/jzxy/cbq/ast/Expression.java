package jzxy.cbq.ast;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表达式类，继承自节点类
 * 这个类代表一个表达式节点，是解析树中的一个组成部分。
 *
 * @extends Node
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Expression extends Node {
}

