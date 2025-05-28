package com.cats.playground.algebriac.`type`

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MyBoolSpec extends AnyFlatSpec with should.Matchers {
  "and" should "create a MyBool with boolean law 1" in {
    val expectedBool = True
    val actualBool = MyBool.and(True, True)

    actualBool should be (expectedBool)
  }

  "and" should "create a MyBool with boolean law 2" in {
    val expectedBool = False
    val actualBool = MyBool.and(True, False)

    actualBool should be (expectedBool)
  }

  "and" should "create a MyBool with boolean law 3" in {
    val expectedBool = False
    val actualBool = MyBool.and(False, False)

    actualBool should be (expectedBool)
  }

  "or" should "create a MyBool with boolean law 1" in {
    val expectedBool = True
    val actualBool = MyBool.or(True, True)

    actualBool should be (expectedBool)
  }

  "or" should "create a MyBool with boolean law 2" in {
    val expectedBool = True
    val actualBool = MyBool.or(True, False)

    actualBool should be (expectedBool)
  }

  "or" should "create a MyBool with boolean law 3" in {
    val expectedBool = False
    val actualBool = MyBool.or(False, False)

    actualBool should be (expectedBool)
  }

  "not" should "create a MyBool with boolean law 1" in {
    val expectedBool = True
    val actualBool = MyBool.not(False)

    actualBool should be (expectedBool)
  }

  "not" should "create a MyBool with boolean law 2" in {
    val expectedBool = False
    val actualBool = MyBool.not(True)

    actualBool should be (expectedBool)
  }

}
