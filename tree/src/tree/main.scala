package tree
import javax.swing.JFrame

import scala.io.StdIn._

object main {
  def main(args: Array[String]): Unit = {
    var tree = new BTree[String]((k1,k2) => k1.compareTo(k2))
    println("1. Show the tree")
    println("2. Add a node")
    println("3. Remove a node")
    println("4. Balance the tree")
    println("5. Check if the tree is balanced")
    println("6. Search data")
    println("7. Print tree using foreach")
    println("0. Exit")
    while (true) {
      readLine() match {
        case "1" => println(tree.show())
        case "2" => {
          print("With data: ")
          try {
            tree.add(readLine())
          }
          catch {
            case iae: IllegalArgumentException => println(iae.getLocalizedMessage())
          }
        }
        case "3" => {
          print("With data: ")
          try {
            tree.remove(readLine())
          }
          catch {
            case iae: IllegalArgumentException => println(iae.getLocalizedMessage())
          }
        }
        case "4" => tree = tree.balance()
        case "5" => println(tree.isBalanced())
        case "6" => {println("With data: "); println(tree.search(readLine()))}
        case "7" => {tree.foreach(println)}
        case "0" => return
        case _ => println("Invalid command")
      }
    }
  }
}
