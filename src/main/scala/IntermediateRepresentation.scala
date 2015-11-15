package com.github.binarystar

sealed abstract class Expression
case class Detector(tag: String) extends Expression
case class Weapon(tag: String) extends Expression
case class Comparison(expression: Expression,
                      comparionOperator: ComparisonOperator,
                      value: String) extends Expression

sealed abstract class Statement
case class If(condition: Expression, code: Block) extends Statement
case class Block(code: List[Statement]) extends Statement
case class Assignment(identifier: String, value: String) extends Statement

sealed abstract class ComparisonOperator
case object GreaterThan extends ComparisonOperator
case object GreaterThanOrEqual extends ComparisonOperator
case object LessThan extends ComparisonOperator
case object LessThanOrEqual extends ComparisonOperator
case object EqualTo extends ComparisonOperator
