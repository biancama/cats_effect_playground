package com.cats.playground.algebriac.`type`

sealed trait MyBool {
 def `if`[A](t: A)(f: A): A
}

case object True extends MyBool {
  override def `if`[A](t: A)(f: A): A = t
}

case object False extends MyBool {
  override def `if`[A](t: A)(f: A): A = f
}

object MyBool {
  def and(one: MyBool, two: MyBool): MyBool = one match {
    case True => two
    case False => False
  }
  def or(one: MyBool, two: MyBool): MyBool = one match {
    case True => True
    case False => two
  }
  def not(b: MyBool): MyBool = b match {
    case True => False
    case False => True
  }
}