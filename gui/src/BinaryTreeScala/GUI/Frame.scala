package BinaryTreeScala.GUI

import tree.BTree

import scala.swing._

class Frame extends MainFrame {
  private var tree:BTree[Int] = new BTree[Int]((k1,k2) => k1.compareTo(k2))
  // Поле ввода
  val input = new TextField(columns = 1)
  // Поле вывода
  val output = new TextArea()
  val controlsPanel = new GridPanel(2,1)
  val buttonsPanel = new GridPanel(2,3)
  preferredSize = new Dimension(500,400)

  // Добавление узла в дерево
  buttonsPanel.contents += Button("Add node") {
    try {
      tree.add(input.text.toInt)
      input.text = ""
    }
    catch {
      case iae: IllegalArgumentException => output.text = iae.getLocalizedMessage
    }
  }

  // Удаление узла
  buttonsPanel.contents += Button("Remove node") {
    try {
      tree.remove(output.text.toInt)
      input.text = ""
    }
    catch {
      case _:NumberFormatException => output.text = "Incorrect input"
      case iae:IllegalArgumentException => output.text = iae.getLocalizedMessage
    }
  }

  // Поиск
  buttonsPanel.contents += Button("Search node") {
    output.text = tree.search(input.text.toInt).toString
    input.text = ""
  }

  // Балансировка
  buttonsPanel.contents += Button("Balancing") {
    tree = tree.balance()
  }

  // Проверка на сбалансированность
  buttonsPanel.contents += Button("isBalanced()") {
    output.text = tree.isBalanced().toString
  }

  buttonsPanel.contents += Button("Foreach print") {
    val str = new StringBuilder
    tree.foreach(println)
    output.text = str.toString()
    output.text.addString(str)
  }

  // Вывод на экран
  val actionsPanel:BorderPanel = new BorderPanel{
    add(buttonsPanel,BorderPanel.Position.West)
    add(input,BorderPanel.Position.Center)
  }
  controlsPanel.contents += actionsPanel
  controlsPanel.contents += Button("Show tree") {
    output.text = tree.show()
  }
  // Добавляем подготовленные компоненты на форму
  contents = new BorderPanel{
    add(controlsPanel,BorderPanel.Position.South)
    add(output,BorderPanel.Position.Center)
  }

}