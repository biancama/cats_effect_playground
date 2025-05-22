package com.cats.playground.algebriac.`type`

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MyStreamSpec extends AnyFlatSpec with should.Matchers {
  "take" should "extract a list" in {
    val initialStream = MyStream.unfold[Int, Int](1, identity, i => i + 1)
    val expectedList = List(1, 2, 3, 4, 5)

    val actualList = initialStream.take(5)

    actualList should be (expectedList)
  }


  "filter" should "create a new MyStream" in {
    val initialStream = MyStream.unfold[Int, Int](1, identity, i => i + 1)
    val expectedStream = MyStream.unfold[Int, Int](2, identity, i => i + 2)
    val actualStream = initialStream.filter(_ % 2 == 0)

    actualStream.head should be (expectedStream.head)
    actualStream.tail.head should be (expectedStream.tail.head)
    actualStream.tail.tail.head should be (expectedStream.tail.tail.head)
  }

  "zio" should "create a new MyStream with pair" in {
    val initialStream1 = MyStream.unfold[Int, Int](1, identity, i => i + 1)
    val initialStream2 = MyStream.unfold[Int, String](1, i => (0 to i).mkString, i => i + 1)
    val expectedStream = MyStream.unfold[Int, (Int, String)](1, i => (i, (0 to i).mkString), i => i + 1)
    val actualStream = initialStream1.zip(initialStream2)

    actualStream.head should be (expectedStream.head)
    actualStream.tail.head should be (expectedStream.tail.head)
    actualStream.tail.tail.head should be (expectedStream.tail.tail.head)
    actualStream.tail.tail.tail.head should be (expectedStream.tail.tail.tail.head)
    actualStream.tail.tail.tail.tail.head should be (expectedStream.tail.tail.tail.tail.head)
  }

  "scanLeft" should "create the natural numbers" in {
    val expectedStream = MyStream.unfold[Int, Int](0, identity, i => i + 1)
    val initialStream = MyStream.unfold[Int, Int](1, identity, identity)

    val actualStream = initialStream.scanLeft(0){ case (prev, current) => prev + current }

    actualStream.head should be (expectedStream.head)
    actualStream.tail.head should be (expectedStream.tail.head)
    actualStream.tail.tail.head should be (expectedStream.tail.tail.head)
  }

}
