package com.effect.playground.intro

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class ConsoleOutputWithEffectSpec extends AnyFlatSpec with should.Matchers {
  "twiceOutput_v1" should "print 2 messages to the console" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      ConsoleOutputWithEffect.twiceOutput_v1()
    }
    stream.toString should be ("Some logs\nSome logs\n")
  }
  "twiceOutput_v2" should "print 2 messages to the console" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      ConsoleOutputWithEffect.twiceOutput_v2()
    }
    stream.toString should be ("Some logs\nSome logs\n")
  }


  "twiceOutput_v3" should "print 2 messages to the console" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      ConsoleOutputWithEffect.twiceOutput_v3().unsafeRunSync()
    }
    stream.toString should be ("Some logs\nSome logs\n")
  }

  "twiceOutput_v4" should "print 2 messages to the console" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      ConsoleOutputWithEffect.twiceOutput_v4().unsafeRunSync()
    }
    stream.toString should be ("Some logs\nSome logs\n")
  }

  "twiceOutput_v3" should "print 0 messages to the console if I don't call unsafeRunSync" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      ConsoleOutputWithEffect.twiceOutput_v3()
    }
    stream.toString should be ("")
  }

  "twiceOutput_v4" should "print 0 messages to the console if I don't call unsafeRunSync" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      ConsoleOutputWithEffect.twiceOutput_v4()
    }
    stream.toString should be ("")
  }
}
