package BTree.tests


import BTree.BTree
import org.junit.Assert._
import org.junit.{Before, Test}
class BTreeTest
{
  private var tree:BTree[Int] = null
  private var balancedTree:BTree[Int] = null

  @Before def makeTree: Unit = {
    tree = new BTree[Int]((k1,k2) => k1.compareTo(k2))
    tree.add(5)
    tree.add(7)
    tree.add(2)
    tree.add(1)
    tree.add(8)
    tree.add(9)
    tree.add(10)
    tree.add(12)
    tree.add(11)
    balancedTree = new BTree[Int]((k1,k2) => k1.compareTo(k2))
    balancedTree.add(5)
    balancedTree.add(7)
    balancedTree.add(2)
    balancedTree.add(1)
    balancedTree.add(8)
    balancedTree.add(9)
    balancedTree.add(4)
  }

  /**
    * Добавление узла, не содержащегося в дереве
    */
  @Test def add() {
    tree.add(13)
    assertEquals(10,tree.getCount())
  }

  /**
    * Добавляем узел, который в дереве уже имеется
    * @throws IllegalArgumentException
    */
  @Test(expected = classOf[IllegalArgumentException])
  @throws[IllegalArgumentException]
  def addingExistedValue(): Unit =
  {
    tree.add(2)
  }

  /**
    * Удаление узла с левым сыном
    */
  @Test def removeLeft() {
    println("removeLeft()")
    tree.remove(12)
    assertEquals(8,tree.getCount())
    tree.show()
  }
  /**
    * Удаление узла с правым сыном
    */
  @Test def removeRight() {
    println("removeRight()")
    tree.remove(8)
    assertEquals(8,tree.getCount())
    tree.show()
  }

  /**
    * Удаление листа
    */
  @Test def removeList() {
    println("removeList()")
    tree.remove(11)
    assertEquals(8,tree.getCount())
    tree.show()
  }

  /**
    * Удаление узла с двумя потомками (в данном случае - корня)
    */
  @Test def removeRoot() {
    println("removeRoot()")
    tree.remove(5)
    assertEquals(8,tree.getCount())
    tree.show()
  }

  /**
    * Удаление отсутствующего узла
    * @throws IllegalArgumentException
    */
  @Test(expected = classOf[IllegalArgumentException])
  @throws[IllegalArgumentException]
  def removeNotExistedValue() {
  tree.remove(-5)
}

  /**
    * Проверка сбалансированности дерева
    */
  @Test def isBalanced() {
    assertFalse(tree.isBalanced())
    assertTrue(balancedTree.isBalanced())
  }


  /**
    * Балансировка дерева
    */
  @Test def balancing() {
    println("Before balancing:")
    tree.show()
    assertFalse(tree.isBalanced())
    val balanced = tree.balance()
    assertTrue(balanced.isBalanced())
    println("After balancing:")
    balanced.show()
  }
  /**
    * Поиск данных в дереве
    */
  @Test def search() {
    assertTrue(tree.search(10))
    assertFalse(tree.search(-50))
  }
}
