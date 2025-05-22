package com.cats.playground.algebriac.`type`

import scala.annotation.tailrec

trait MyStream [A] {
  def head: A
  def tail: MyStream[A]
  def take1(count: Int): List[A] =
    count match {
    case 0 => List.empty[A]
    case n => head :: this.tail.take(n - 1)
  }

  def take(count: Int): List[A] = {
    @tailrec
    def loop(t: MyStream[A], count: Int, app: List[A]): List[A] = count match {
      case 0 => app
      case n => loop(t.tail, n - 1, t.head::app)
    }
    loop(this, count, List()).reverse
  }

  def filter(pred: A => Boolean): MyStream[A] = {
    val s = this
    new MyStream[A] {
      override def head: A = {
        @tailrec
        def loop(app: MyStream[A]): A = if (pred(app.head)) app.head else loop(app.tail)
        loop(s)
      }
      override def tail: MyStream[A] = {
        @tailrec
        def loop(app: MyStream[A]): MyStream[A] = if (pred(app.head)) app.tail.filter(pred) else loop(app.tail)
        loop(s)
      }
    }
  }

  def zip[B](that: MyStream[B]): MyStream[(A, B)] = {
    val s =  this
    new MyStream[(A, B)] {
      override def head: (A, B) = (s.head, that.head)
      override def tail: MyStream[(A, B)] = s.tail.zip(that.tail)
    }
  }
  def scanLeft[B](zero: B)(f: (B, A) => B): MyStream[B] = {
    val s = this
    new MyStream[B] {
      override def head: B = zero
      override def tail: MyStream[B] = s.tail.scanLeft(f(zero, s.head))(f)
    }
  }
}

object MyStream {
  def unfold[A, B](seed: A, f: A => B, next: A => A): MyStream[B] = new MyStream[B] {
    override def head: B = f(seed)

    override def tail: MyStream[B] = unfold(next(seed), f, next)
  }

}
