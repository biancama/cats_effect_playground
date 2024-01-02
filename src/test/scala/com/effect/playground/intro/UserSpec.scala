package com.effect.playground.intro

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class UserSpec extends AnyFlatSpec with should.Matchers {
  "createUsers function with def" should "create users with different Ids" in {
    def uuid = java.util.UUID.randomUUID.toString
    val users = List("Massimo", "John", "Martin")
    val expectedUsersSize = users.size
    val actualIds = Users.createUsers(users, () => uuid).map(_.id).toSet

    actualIds.size should be (expectedUsersSize)
  }

  "createUsers function with val" should "create users with different Ids" in {
    val uuid = java.util.UUID.randomUUID.toString
    val users = List("Massimo", "John", "Martin")
    val expectedUsersSize = users.size
    val actualIds = Users.createUsers(users, () => uuid).map(_.id).toSet

    actualIds.size should be (expectedUsersSize)
  }

}
