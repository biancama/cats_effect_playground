package com.effect.playground.intro

object ConsoleOutputWithEffect {
  def twiceOutput_v1(): Unit = {
    for (_ <- 1 to 2){
      println("Some logs")
    }
  }

  def twiceOutput_v2(): Unit = {
    val consoleMessage = println("Some logs")
    for (_ <- 1 to 2){
      consoleMessage
    }
  }

  def twiceOutput_v3(): MyEffect[Unit] = {
    for {
      _ <- MyEffect.createEffect(println("Some logs"))
      _ <- MyEffect.createEffect(println("Some logs"))
    } yield ()
  }

  def twiceOutput_v4(): MyEffect[Unit] = {
    val consoleMessage = MyEffect.createEffect(println("Some logs"))
    for {
      _ <- consoleMessage
      _ <- consoleMessage
    } yield ()
  }
}
