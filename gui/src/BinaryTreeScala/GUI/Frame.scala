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
  val actionsPanel = new GridPanel(1,4)
  preferredSize = new Dimension(500,400)
  // Добавление узла в дерево
  actionsPanel.contents += Button("Add node") {
    try {
      tree.add(input.text.toInt)
      input.text = ""
    }
    catch {
      case iae: IllegalArgumentException => output.text = iae.getLocalizedMessage()
    }
  }
  // Удаление узла
  actionsPanel.contents += Button("Remove node") {
    try {
      tree.remove(input.text.toInt)
      input.text = ""
    }
    catch {
      case iae: IllegalArgumentException => output.text = iae.getLocalizedMessage()
    }
  }
  // Поиск
  actionsPanel.contents += Button("Search node") {
    output.text = tree.search(input.text.toInt).toString
    input.text = ""
  }
  // Вывод на экран
  actionsPanel.contents += input
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