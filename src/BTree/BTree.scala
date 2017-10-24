package BTree
import scala.collection.mutable.ArrayBuffer

class BTree[T](compare:(T,T) => Int) {
  class Node(var value:T, var left:Node, var right:Node)
  private var root:Node = null
  private  var count = 0

  /**
    * Добавляет узел в дерево
    * @param data данные узла
    */
  def add(data:T): Unit ={
    def walk(n:Node): Node = {
      if (n==null) new Node(data,null,null)
      else {
        val c = compare(data, n.value)
        if(c==0) {
          throw new IllegalArgumentException("Node "+n.value+" is already exists")
        } else if(c<0) {
          n.left = walk(n.left)
        }
        else {
          n.right = walk(n.right)
        }
        n
      }
    }
    root = walk(root)
    count+=1
  }

  /**
    *
    * @return число узлов в дереве
    */
  def getCount() = count
  /**
    * Добавляет узел в дерево
    * @param data данные узла
    */
  def remove(data:T): Unit ={
    def walk(n:Node): Node = {
      if (n==null) throw new IllegalArgumentException("Node "+data+" is not exists")
      else {
        val c = compare(data, n.value)
        if(c==0) { //Нашли удаляемый
          count-=1
          if (n.left==null) n.right
          else if (n.right==null) n.left
          else { //Оба потомка есть
            val (v,node) = removeMin(n.right) //Следующий по значению узел
            n.right = node
            n.value = v
            n
          }
        } else if(c<0) { //Удаляемый в левом поддереве
          n.left = walk(n.left)
          n
        }
        else {  //Удаляемый в правом поддереве
          n.right = walk(n.right)
          n
        }
      }
    }
    def removeMin(n:Node) : (T,Node) = {
      if (n.left==null) (n.value,n.right)
      else {
        val (v,node) = removeMin(n.right)
        n.right = node
        (v,n)
      }
    }
    root = walk(root)
  }

  /**
    * Печать дерева на экран
    */
  def show(): Unit = {
    def walk(n:Node, level: Int): Unit ={
      if (n==null) return
      walk(n.right,level+1)
      var i = 0
      while (i < 3*level) {
        print(" ")
        i+=1
      }
      println(n.value)
      walk(n.left,level+1)
    }
    if (root==null) println("The tree is empty")
    else walk(root, 0)
  }

  /**
    * Проверяет, что дерево сбалансированно
    * @return true - дерево сбалансированно, false в противном случае
    */
  def isBalanced(): Boolean = {
    def walk(n:Node): Int = {
      if (n==null) return 0
      var l= 0
      var r= 0
      if (n.left!=null)
        l = walk(n.left)
      if (n.right!=null)
        r = walk(n.right)
      if (l>r) l+1 else r+1
    }
    var l = 0
    var r = 0
    if (root == null) return true
    if (root.left != null) l = walk(root.left)
    if (root.right != null) r = walk(root.right)
    Math.abs(r-l)<=1
  }

  /**
    * Выполняет балансировку дерева
    * @return сбалансированное дерево
    */
  def balance():BTree[T] = {
    if (isBalanced()) return this
    val arr = new ArrayBuffer[T]()
    def fillArray(n:Node):Unit = {
      if (n==null) return
      fillArray(n.left)
      arr += n.value
      fillArray(n.right)
    }
    fillArray(root)
    var balnced:BTree[T] = new BTree[T](compare)
    def balance(a:Int, b:Int) : Unit = {
      if (a>b) return
      val middle = (a + b) / 2
      balnced.add(arr(middle))
      balance(a,middle-1)
      balance(middle+1,b)
    }
    balance(0,count-1)
    balnced
  }

  /**
    * Поиск элемента
    * @param data искомые данные
    * @return true|false в зависимости от успеха поиска
    */
  def search(data:T):Boolean = {
    def walk(num: T, root: Node):Boolean = {
      if (root == null) return false
      if (root.value.equals(num)) return true
      val c = compare(num, root.value)
      if (c<0) return walk(num,root.left)
      return walk(num,root.right)

    }
    walk(data,root)
  }

  def foreach[U](f: T => U) = {
    def walk(n:Node): Unit ={
      if (n==null) return
      walk(n.left)
      f(n.value)
      walk(n.right)
    }
    if (root==null) println("The tree is empty")
    else walk(root)
  }
}

