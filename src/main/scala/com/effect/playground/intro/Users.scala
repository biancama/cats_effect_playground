package com.effect.playground.intro

case class User(id: String, name: String)
object Users {
  def createUsers(names: List[String], genId: () => String): List[User] = names.map(name => User(genId(), name))

  def createUsersWithEffect(names: List[String], genId: () => String): MyEffect[List[User]] = MyEffect.createEffect(names.map(name => User(genId(), name)))
}
