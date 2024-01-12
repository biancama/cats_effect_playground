package com.effect.playground.ioruntime

import cats.effect.IO
import cats.effect.unsafe.{IORuntime, IORuntimeConfig}
import cats.implicits.catsSyntaxParallelSequence1
import com.effect.playground.ioruntime.MyExecutionContext.basicIORuntime

import java.util.concurrent.{CompletableFuture, Executors}
import scala.annotation.tailrec
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, DurationInt}
import scala.util.{Failure, Success}
object MyExecutionContext {
  val basicIORuntime: IORuntime = IORuntime(
    compute = IORuntime.createDefaultBlockingExecutionContext("compute")._1,
    blocking = IORuntime.createDefaultBlockingExecutionContext("blocking")._1,
    scheduler = IORuntime.createDefaultScheduler("scheduler")._1,
    shutdown = () => (),
    config = IORuntimeConfig()
  )

  def boundedIORuntime(numberOfThread: Int): IORuntime = {
    lazy val bounded: IORuntime = IORuntime(
      compute = IORuntime.createDefaultComputeThreadPool(bounded, numberOfThread, "compute")._1,
      blocking = IORuntime.createDefaultBlockingExecutionContext("blocking")._1,
      scheduler = IORuntime.createDefaultScheduler("scheduler")._1,
      shutdown = () => (),
      config = IORuntimeConfig()
    )
    bounded
  }

  def time(work: IO[Unit]): IO[String] = work.timed.map {
    case (duration, _) => s"The function took ${duration.toSeconds} secs."
  }
}


object Main extends App {
  private def printConsole(f: Future[String]) = f.onComplete {
    case Failure(_) => println("Something bad occur")
    case Success(value) => print(value)
  }(basicIORuntime.compute)


  import MyExecutionContext._
  private val sleeper = IO.sleep(2.seconds)
  // Single Future
  val fut01 = time(sleeper).unsafeToFuture()(basicIORuntime)
//  printConsole(fut01)
//  Await.ready(fut01, Duration.Inf)

  private val sleepers = List(sleeper, sleeper)

  val parSleepers = sleepers.parSequence.void
  val fut02 = time(parSleepers).unsafeToFuture()(basicIORuntime)
//  printConsole(fut02)
//  Await.ready(fut02, Duration.Inf)

  private val lotsOfParSleepers = (0 until 1000).map(_ => sleeper ).toList.parSequence.void
//  val fut03 = time(lotsOfParSleepers).unsafeToFuture()(basicIORuntime)
//  printConsole(fut03)
//  Await.ready(fut03, Duration.Inf)

  private val factorial = (n: Long) =>  {
    @tailrec
    def factorial(n: Long, app: Long): Long =
      if (n > 1) factorial(n - 1, app * n) else app
    val tot = factorial(n, 1)
    IO.pure(tot)
  }

    private val fut04 = time(factorial(2000000L).map(total => println(s"Factorial of 1000 ${total}"))).unsafeToFuture()(basicIORuntime)
    printConsole(fut04)
    Await.ready(fut04, Duration.Inf)
}