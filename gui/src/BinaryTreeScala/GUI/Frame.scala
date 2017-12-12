package BinaryTreeScala.GUI

import Model.BTree

import scala.swing._

class Frame extends MainFrame {
  private var tree:BTree[Int] = new BTree[Int]((k1,k2) => k1.compareTo(k2))
  val nodeField = new TextField(columns = 1)
  val output = new TextArea()
  val buttonsPanel = new GridPanel(1,4)
  buttonsPanel.contents += Button("Add node") {
    try {
      tree.add(nodeField.text.toInt)
      nodeField.text = ""
    }
    catch {
      case iae: IllegalArgumentException => output.text = iae.getLocalizedMessage()
    }
  }
  buttonsPanel.contents += Button("Remove node") {
    try {
      tree.remove(nodeField.text.toInt)
      nodeField.text = ""
    }
    catch {
      case iae: IllegalArgumentException => output.text = iae.getLocalizedMessage()
    }
  }
  buttonsPanel.contents += Button("Search node") {
    output.text = tree.search(nodeField.text.toInt).toString
    nodeField.text = ""
  }
  buttonsPanel.contents += nodeField
  contents = new BorderPanel{
    add(Button("Show tree") {
      output.text = tree.show()
    },BorderPanel.Position.South)
    add(buttonsPanel,BorderPanel.Position.Center)
    add(output,BorderPanel.Position.apply(0))
  }

}