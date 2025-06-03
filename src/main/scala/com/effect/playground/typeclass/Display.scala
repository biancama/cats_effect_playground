package com.effect.playground.typeclass


trait Display[A] {
  def display(value: A): String
}

final case class Cat(name: String, age: Int, color: String)

object DisplayInstances {

  implicit val stringDisplay:Display[String] = (value: String) => value
  implicit val intDisplay: Display[Int] = (value: Int) => String.valueOf(value)
  implicit val catDisplay: Display[Cat] = value => s"${value.name} is a ${value.age} year-old ${value.color} cat."
}

object DisplaySyntax {
  implicit class DisplaySyntaxImplicit[A:Display] (value: A) {
    def display: String = Display.display(value)
    def print: Unit = Display.print(value)
  }
}


object Display {
  def display[A](a: A)(implicit display: Display[A]): String = display.display(a)
  def print[A:Display](value: A): Unit = println(display(value))
  def main(args: Array[String]): Unit = {
    import DisplayInstances._
    import DisplaySyntax._
    println(display("pippo"))
    println(display(1))
    print("Cesare")
    print(Cat("Romeo", 11, "red"))
    println(2.display)

    print(Cat("Micia", 3, "white"))
  }
}
