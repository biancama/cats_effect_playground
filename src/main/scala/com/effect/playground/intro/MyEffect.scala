package com.effect.playground.intro

import com.effect.playground.intro.MyEffect.createEffect

case class MyEffect[A](unsafeRunSync: () => A) {
  def map[B](f: A => B): MyEffect[B] = createEffect(f(unsafeRunSync()))
  def flatMap[B](f: A => MyEffect[B]): MyEffect[B] = createEffect(f(unsafeRunSync()).unsafeRunSync())
}


object MyEffect {
  def createEffect[A](effect: => A) = new MyEffect[A](() => effect)
}