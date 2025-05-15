package com.cats.playground.algebriac.`type`

sealed abstract class Tree[A] extends Product with Serializable {
  def size: Int = this match {
    case Leaf(_) => 1
    case Node(left, right) => left.size + right.size
  }
  def contains(value: A): Boolean = this match {
    case Leaf(v) => v == value
    case Node(left, right) => left.contains(value) || right.contains(value)
  }
  def map[B](f: A => B): Tree[B] = this match {
    case Leaf(value) => Leaf(f(value))
    case Node(left, right) => Node(left.map(f), right.map(f))
  }
  def fold[B](f: A => B)(op: (B, B) => B): B = this match {
    case Leaf(value) => f(value)
    case Node(left, right) => op(left.fold(f)(op), right.fold(f)(op))
  }

  def sizeFold: Int = fold(_ => 1){(i1, i2) => i1 + i2}
  def containsFold(value: A): Boolean = fold(v => v == value){(check1, check2) => check1 || check2}
  def mapFold[B](f: A => B): Tree[B] = fold[Tree[B]](v => Leaf(f(v))){(l, r) => Node(l, r)}
}
case class Leaf[A](value: A) extends Tree[A]
case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
