package com.cats.playground.algebriac.`type`

import com.cats.playground.algebriac.`type`.MyList.unfold

sealed trait MyList[+A] {
  def head: A = this match {
    case Cons(head, _) => head
  }

  def tail: MyList[A] = this match {
    case Cons(_, _tail) => _tail
  }

  def isEmpty: Boolean = this match {
    case Nil => true
    case Cons(_, _) => false
  }

  def map[B](f: A => B): MyList[B] = unfold(this) (
    _.isEmpty,
    t => f(t.head),
    t => t.tail
  )
}

object MyList {
  def unfold[A, B](seed: A)(stop: A => Boolean, f: A => B, next: A => A): MyList[B] = stop(seed) match {
    case true => Nil
    case false => Cons(f(seed), unfold(next(seed))(stop, f, next))
  }

  def fill[A](n: Int)(elem: A): MyList[A] = unfold[Int, A](n)(n => n <= 0, _ => elem, n => n - 1)


  def iterate[A](start: A, length: Int)(next: A => A): MyList[A] = unfold((length, start))(
    { case (i, _) => i <= 0 },
    { case (_, a) => a },
    { case (i, a) => (i - 1, next(a)) }
  )


}

case object Nil extends MyList[Nothing]

case class Cons[+A](_head: A, _tail: MyList[A]) extends MyList[A]
