package com.cats.playground.algebriac.`type`

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MyListSpec extends AnyFlatSpec with should.Matchers {
  "unfold" should "create a MyList" in {
    val expectedList = Cons(2, Cons(4, Cons(6, Cons(8, Cons(10, Cons(12, Cons(14, Cons(16, Cons(18, Nil)))))))))
    val actualList = MyList.unfold[Int, Int](1)(a => a > 9, _ * 2, _ + 1)

    actualList should be (expectedList)
  }

  "fill" should "create a MyList with the same values" in {
    val expectedList = Cons(1, Cons(1, Cons(1, Cons(1, Cons(1, Cons(1, Cons(1, Cons(1, Cons(1, Nil)))))))))
    val actualList = MyList.fill(9)(1)

    actualList should be (expectedList)
  }

  "iterate" should "create a MyList with different values" in {
    val expectedList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Cons(7, Cons(8, Cons(9, Nil)))))))))
    val actualList = MyList.iterate(1, 9)(_ + 1)

    actualList should be (expectedList)
  }

  "map" should "crete a new list accordingly with function passed" in {
    val expectedList = Cons(2, Cons(4, Cons(6, Cons(8, Cons(10, Cons(12, Cons(14, Cons(16, Cons(18, Nil)))))))))
    val inputList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Cons(7, Cons(8, Cons(9, Nil)))))))))
    val actualList = inputList.map(_ * 2)

    actualList should be (expectedList)
  }
}
