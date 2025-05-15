package com.cats.playground.algebriac.`type`

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class TreeSpec extends AnyFlatSpec with should.Matchers {
  "size" should "returns the number of leaves stored in a tree 01" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Leaf(3))
    tree.size should be (3)
  }

  "size" should "returns the number of leaves stored in a tree 02" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.size should be (5)
  }

  "contains" should "returns true if the value is contained in the tree" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.contains(0) should be (true)
  }
  "contains" should "returns false if the value is contained in the tree" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.contains(8) should be (false)
  }

  "map" should "returns a tree as specified in the function" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    val expectedTree = Node(Node(Leaf("even"), Leaf("odd")), Node(Leaf("even"), Node(Leaf("odd"), Leaf("even"))))
    tree.map(i => if (i % 2 == 0) "even" else "odd" ) should be (expectedTree)
  }

  "fold" should "returns a value as specified in the function 01" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.fold(identity) {  (i1, i2) => i1 + i2} should be (12)
  }

  "fold" should "returns a value as specified in the function 02" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.fold((identity)) {  (i1, i2) => i1 * i2} should be (0)
  }


  "sizeFold" should "returns the number of leaves stored in a tree 01" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Leaf(3))
    tree.sizeFold should be (3)
  }

  "sizeFold" should "returns the number of leaves stored in a tree 02" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.sizeFold should be (5)
  }

  "containsFold" should "returns true if the value is contained in the tree" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.containsFold(0) should be (true)
  }
  "containsFold" should "returns false if the value is contained in the tree" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    tree.containsFold(8) should be (false)
  }

  "mapFold" should "returns a tree as specified in the function" in {
    val tree = Node(Node(Leaf(4), Leaf(5)), Node(Leaf(2), Node(Leaf(1), Leaf(0))))
    val expectedTree = Node(Node(Leaf("even"), Leaf("odd")), Node(Leaf("even"), Node(Leaf("odd"), Leaf("even"))))
    tree.mapFold(i => if (i % 2 == 0) "even" else "odd" ) should be (expectedTree)
  }

}
