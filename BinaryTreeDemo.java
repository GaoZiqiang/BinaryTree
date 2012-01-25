// Author: Steve Savia ('stevesavia' @ github.com)

public class BinaryTreeDemo {

  // Brief test method with hard coded nodes, in contrast to second test method
  public static void nodeTest1() 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> tree = new BinaryTree<Integer>();
    Integer[][] w = { {8},{7,6},{5,4,3,2}, {0,3,6,9,2,5,8,1} };
    tree = tree.createPopulatedIntegerTree(w);
    Node<Integer> rt = tree.getRoot();
    Node<Integer> lCh = rt.getLeft();
    Node<Integer> rCh = rt.getRight();
    Node<Integer> llCh = lCh.getLeft();
    Node<Integer> lrCh = lCh.getRight();
    Node<Integer> rlCh = rCh.getLeft();
    Node<Integer> rrCh = rCh.getRight();   
    Node<Integer> lll = llCh.getLeft();
    Node<Integer> llr = llCh.getRight();
    Node<Integer> lrl = lrCh.getLeft();
    Node<Integer> lrr = lrCh.getRight();
    Node<Integer> rll = rlCh.getLeft();
    Node<Integer> rlr = rlCh.getRight();
    Node<Integer> rrl = rrCh.getLeft();
    Node<Integer> rrr = rrCh.getRight();
    System.out.println("removeLevel4 NodesTest");
    System.out.println();    
    tree.printDiagram(tree, tree.getRoot());
    System.out.println("MARKER 2");
    tree.removeNode(lll); // remove 1st node in 4th level 
    tree.printDiagram(tree, tree.getRoot());
    tree.removeNode(lrl); // remove 3rd node 
    tree.printDiagram(tree, tree.getRoot());
    tree.removeNode(rrl); // remove 2nd to last node 
    tree.printDiagram(tree, tree.getRoot());
  }// end nodeTest1


  // Test method without hard coded nodes, instead using direct tree path access
  // Also demonstrates a variety of methods
  public static void nodeTest2() 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> tree = new BinaryTree<Integer>();
    Integer[] u = {1,2,4,5,7,8,0,1,3,4,6,7,9,0,2,3};   
    Integer[][] w = { {8},{7,6},{5,4,3,2}, {0,3,6,9,2,5,8,1}, u };
    tree = tree.createPopulatedIntegerTree(w);
    System.out.println();
    System.out.println("CPIT METHOD, tree");
    tree.printDiagram(tree, tree.getRoot());
                                  
    System.out.println("removeLevel5 NodesTest");
    int[] pathTo5thLevel = {0,0,0,0};
    Node<Integer> accessedNode = tree.accessNode(tree, pathTo5thLevel);
    tree.removeNode(accessedNode); // remove 1st node in 5th level 
    tree.printDiagram(tree, tree.getRoot());
    pathTo5thLevel[3] = 1; // 0,0,0,1  
    tree.removeNode(tree.accessNode(tree, pathTo5thLevel)); // remove 2nd node
    tree.printDiagram(tree, tree.getRoot());
    pathTo5thLevel[0] = 1; // 1,0,0,1
    pathTo5thLevel[3] = 0; // 1,0,0,0
    tree.removeNode(tree.accessNode(tree, pathTo5thLevel)); // remove 9th node 
    tree.printDiagram(tree, tree.getRoot());
    System.out.println("REMOVE SEVERAL INTERNAL NODES:");
    int[] pathTo4thLevel = {1,0,0};
    tree.removeNode(tree.accessNode(tree, pathTo4thLevel)); // remove 5th node in 4th level 
    tree.printDiagram(tree, tree.getRoot()); 
    tree.removeNode(tree.accessNode(tree, pathTo4thLevel)); // remove the promoted node 
    tree.printDiagram(tree, tree.getRoot());
    int[] pathTo3rdLevel = {1,0};
    tree.removeNode(tree.accessNode(tree, pathTo3rdLevel)); // remove 3rd node in 3rd level
    tree.printDiagram(tree, tree.getRoot());    
    System.out.println("Using getTreeDiagramListFromNode method:");
    NodeList<Node<Integer>> noli = tree.getTreeDiagramListFromNode(tree.getRoot());
    tree.printList(noli); 
 
    BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
    Integer[] c = {8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3};
    Integer[] d = {8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3};   
    Integer[][] e = { {1},{2,3},{4,5,6,7}, {8,9,0,1,2,3,4,5}, c, d };
    System.out.println();
    System.out.println("TREE WITH 6 LEVELS:");
    tree2 = tree2.createPopulatedIntegerTree(e);
   
    NodeList<Node<Integer>> nodeListA; 
    nodeListA = tree2.getTreeList();
    System.out.println("USE GETTREELIST FOR ELEMENTS");
    tree2.printList(nodeListA); 
  
    NodeList<Integer> levelsList = new NodeList<Integer>();
    tree2.getSubtreeLevelsList(tree2.getRoot(), levelsList);
    System.out.println("USE GETSUBTREELEVELSLIST FOR LEVELS");
    tree2.printLevelList(levelsList);

    tree2.printDiagram(tree2, tree2.getRoot());
    System.out.println("DEMONSTRATE MORE EXTENSIVE NODE REMOVAL AND PROMOTION:");
    int[] pathTo6thLevel = {0,0,0,0,0};
    tree2.removeNode(tree2.accessNode(tree2, pathTo6thLevel)); // remove 1st node in 6th level 
    tree2.printDiagram(tree2, tree2.getRoot()); 
    pathTo5thLevel[0] = 0; // 0,0,0,0
    tree2.removeNode(tree2.accessNode(tree2, pathTo5thLevel)); // remove 1st node in 5th level 
    tree2.printDiagram(tree2, tree2.getRoot());
    tree2.removeNode(tree2.accessNode(tree2, pathTo5thLevel)); // remove the promoted node
    tree2.printDiagram(tree2, tree2.getRoot()); 
    pathTo6thLevel[3] = 1; // 0,0,0,1,0
    tree2.removeNode(tree2.accessNode(tree2, pathTo6thLevel));
    pathTo6thLevel[4] = 1; // 0,0,0,1,1
    tree2.removeNode(tree2.accessNode(tree2, pathTo6thLevel)); // 3rd & 4th nodes 6th level gone
    pathTo5thLevel[3] = 1; // 0,0,0,1
    tree2.removeNode(tree2.accessNode(tree2, pathTo5thLevel)); // remove 2nd mode in 5th level
    tree2.printDiagram(tree2, tree2.getRoot()); 
    pathTo4thLevel[0] = 0; // 0,0,0
    tree2.removeNode(tree2.accessNode(tree2, pathTo4thLevel)); // remove 1st node in 4th level
    tree2.printDiagram(tree2, tree2.getRoot());
    pathTo3rdLevel[0] = 0; // 0,0
    tree2.removeNode(tree2.accessNode(tree2, pathTo3rdLevel)); // remove 1st node in 3rd level
    System.out.println("NOTE NODES PROMOTED THROUGH 3 LAYERS");
    tree2.printDiagram(tree2, tree2.getRoot());
    // remove all descendent nodes below node promoted to 1st node 3rd level 
    tree2.removeDescendents(tree2.accessNode(tree2, pathTo3rdLevel)); 
    System.out.println("REMOVED ALL DESCENDENTS OF A NODE WITH REMOVEDESCENDENTS");
    tree2.printDiagram(tree2, tree2.getRoot());
    tree2.removeNode(tree2.accessNode(tree2, pathTo3rdLevel)); // now remove the node itself
    tree2.printDiagram(tree2, tree2.getRoot()); 
    int[] pathTo2ndLevel = {0};
    tree2.removeNode(tree2.accessNode(tree2, pathTo2ndLevel)); // remove 1st node in 2nd level
    System.out.println("NODES NOW PROMOTED THROUGH 4 LAYERS");
    tree2.printDiagram(tree2, tree2.getRoot()); 

    tree2.updateHeightAndSize();
    System.out.println("TREE2 Size: " + tree2.getBTSize());
    System.out.println("TREE2 Height: " + tree2.getBTHeight());
    System.out.println(); 
   
    pathTo2ndLevel[0] = 1; // remove subtree 2nd node 2nd level
    tree2.removeSubtree(tree2.accessNode(tree2, pathTo2ndLevel));
    System.out.println("DELETE ENTIRE RIGHT OF TREE BY REMOVESUBTREE:");
    tree2.printDiagram(tree2, tree2.getRoot());
    NodeList<Node<Integer>> tree2NodeList = tree2.getTreeDiagramList();
    tree2.printList(tree2NodeList);     
    System.out.println("TREE2 Size: " + tree2.getBTSize());
    System.out.println("TREE2 Height: " + tree2.getBTHeight());

    pathTo2ndLevel[0] = 0;
    accessedNode = tree2.accessNode(tree2, pathTo2ndLevel);
    tree2.removeDescendents(accessedNode); 
    System.out.println("REMOVED ALL DESCENDENTS OF LEFT NODE 2ND LEVEL:");
    tree2.printDiagram(tree2, tree2.getRoot());

    BinaryTree<Integer> leftAttach = new BinaryTree<Integer>();
    BinaryTree<Integer> rightAttach = new BinaryTree<Integer>();
    Node<Integer> lft = leftAttach.setRoot(8);
    leftAttach.setLeft(lft, 7);
    leftAttach.setRight(lft, 6);
    Node<Integer> rght = rightAttach.setRoot(9); 
    rightAttach.setLeft(rght, 5);
    rightAttach.setRight(rght, 4);   
    tree2.attachTrees(accessedNode, leftAttach, rightAttach);
    System.out.println("USE ATTACHTREES:");
    tree2.printDiagram(tree2, tree2.getRoot());
    System.out.println("TREE2 Size: " + tree2.getBTSize());
    System.out.println("TREE2 Height: " + tree2.getBTHeight());

    pathTo3rdLevel[1] = 1; // 0,1
    accessedNode = tree2.accessNode(tree2, pathTo3rdLevel);
    int accessedElement = accessedNode.getElement();
    System.out.println("Accessed node holding: " + accessedElement);
  }// end nodeTest2


  // MAIN METHOD- TEST PURPOSES
  public static void main(String[] args) 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    nodeTest1();
    nodeTest2();
  }// End main
}
