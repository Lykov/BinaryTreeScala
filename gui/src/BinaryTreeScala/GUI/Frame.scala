package BinaryTreeScala.GUI

import Model.BTree

import scala.swing._

class Frame extends MainFrame {
  private var tree:BTree[Int] = new BTree[Int]((k1,k2) => k1.compareTo(k2))
  val nodeField = new TextField(columns = 1)
  val output = new TextArea()
  val controlsPanel = new GridPanel(2,1)
  val buttonsPanel = new GridPanel(1,4)
  preferredSize = new Dimension(500,400)
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
  controlsPanel.contents += buttonsPanel
  controlsPanel.contents += Button("Show tree") {
    output.text = tree.show()
  }
  contents = new BorderPanel{
    add(controlsPanel,BorderPanel.Position.South)
    add(output,BorderPanel.Position.Center)
  }

}