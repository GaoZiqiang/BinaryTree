
public class BinaryTreeDemo {

  public static void level4NodesTest() 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> bt70 = new BinaryTree<Integer>();
    Integer[][] w = { {8},{7,6},{5,4,3,2}, {0,3,6,9,2,5,8,1} };
    bt70 = bt70.createPopulatedIntegerTree(w);
    Node<Integer> rt = bt70.getRoot();
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
    bt70.printDiagram(bt70, bt70.getRoot());
    System.out.println("MARKER 2");
    bt70.removeNode(lll); // remove 1st node in 4th level 
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(lrl); // remove 3rd node 
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(rrl); // remove 2nd to last node 
    bt70.printDiagram(bt70, bt70.getRoot());
  }// end level4NodesTest


  public static void level5NodesTest() 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> bt70 = new BinaryTree<Integer>();
    Integer[] u = {1,2,4,5,7,8,0,1,3,4,6,7,9,0,2,3};   
    Integer[][] w = { {8},{7,6},{5,4,3,2}, {0,3,6,9,2,5,8,1}, u };
    bt70 = bt70.createPopulatedIntegerTree(w);
    System.out.println("CPIT METHOD, bt70");
    bt70.printDiagram(bt70, bt70.getRoot());
    Node<Integer> rt = bt70.getRoot();
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
    Node<Integer> llll = lll.getLeft();
    Node<Integer> lllr = lll.getRight();
    Node<Integer> llrl = llr.getLeft();
    Node<Integer> llrr = llr.getRight();
    Node<Integer> lrll = lrl.getLeft();
    Node<Integer> lrlr = lrl.getRight();
    Node<Integer> lrrl = lrr.getLeft();
    Node<Integer> lrrr = lrr.getRight();
    Node<Integer> rlll = rll.getLeft();
    Node<Integer> rllr = rll.getRight();
    Node<Integer> rlrl = rlr.getLeft();
    Node<Integer> rlrr = rlr.getRight();
    Node<Integer> rrll = rrl.getLeft();
    Node<Integer> rrlr = rrl.getRight();
    Node<Integer> rrrl = rrr.getLeft();
    Node<Integer> rrrr = rrr.getRight();                                       
    System.out.println("removeLevel5 NodesTest");
    bt70.removeNode(llll); // remove 1st node in 5th level 
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(lllr); // remove 2nd node 
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(rlll); // remove 9th node 
    bt70.printDiagram(bt70, bt70.getRoot());
    System.out.println("REMOVE SEVERAL INTERNAL NODES:");
    bt70.removeNode(rll); // remove 5th node in 4th level 
    bt70.printDiagram(bt70, bt70.getRoot()); 
    bt70.removeNode(rllr); // remove the promoted node (with element 4) to allow...
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(rlCh); // remove 3rd node in 3rd level
    bt70.printDiagram(bt70, bt70.getRoot());    
    System.out.println("Using getTreeDiagramListFromNode method:");
    NodeList<Node<Integer>> noli = bt70.getTreeDiagramListFromNode(bt70.getRoot());
    bt70.printList(noli);
    BinaryTree<Integer> pop = new BinaryTree<Integer>();
    Integer[] c = {8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3};
    Integer[] d = {8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3};   
    Integer[][] e = { {1},{2,3},{4,5,6,7}, {8,9,0,1,2,3,4,5}, c, d };
    System.out.println();
    System.out.println("TREE WITH 6 LEVELS:");
    pop = pop.createPopulatedIntegerTree(e);
   
    NodeList<Node<Integer>> nodeListA; 
    nodeListA = pop.getTreeList();
    System.out.println("USE GETTREELIST FOR ELEMENTS");
    pop.printList(nodeListA); 
  
    NodeList<Integer> levelsList = new NodeList<Integer>();
    pop.getSubtreeLevelsList(pop.getRoot(), levelsList);
    System.out.println("USE GETSUBTREELEVELSLIST FOR LEVELS");
    pop.printLevelList(levelsList);

    pop.printDiagram(pop, pop.getRoot());
    System.out.println("DEMONSTRATE MORE EXTENSIVE NODE REMOVAL AND PROMOTION:");
    rt = pop.getRoot();
    lCh = rt.getLeft();    
    llCh = lCh.getLeft();    
    lll = llCh.getLeft();
    llr = llCh.getRight();       
    llll = lll.getLeft();
    lllr = lll.getRight();
    llrl = llr.getLeft();
    llrr = llr.getRight();    
    Node<Integer> lllll = llll.getLeft();
    Node<Integer> llllr = llll.getRight();
    Node<Integer> lllrl = lllr.getLeft();
    Node<Integer> lllrr = lllr.getRight();
    Node<Integer> llrll = llrl.getLeft();
    Node<Integer> llrlr = llrl.getRight();
    Node<Integer> llrrl = llrr.getLeft();
    Node<Integer> llrrr = llrr.getRight(); 
    pop.removeNode(lllll); // remove 1st node in 6th level 
    pop.printDiagram(pop, pop.getRoot()); 
    pop.removeNode(llll); // remove 1st node in 5th level 
    pop.printDiagram(pop, pop.getRoot());
    pop.removeNode(llllr); // remove 1st node in 5th level (promoted from 6th level)
    pop.printDiagram(pop, pop.getRoot()); 
    pop.removeNode(lllrl);
    pop.removeNode(lllrr); // remove 3rd and 4th nodes in 6th level
    pop.removeNode(lllr); // remove 2nd mode in 5th level
    pop.printDiagram(pop, pop.getRoot()); 
    pop.removeNode(lll); // remove 1st node in 4th level
    pop.printDiagram(pop, pop.getRoot());
    pop.removeNode(llCh); // remove 1st node in 3rd level
    System.out.println("NOTE NODES PROMOTED THROUGH 3 LAYERS");
    pop.printDiagram(pop, pop.getRoot());
    // remove all descendent nodes below node promoted to 1st node 3rd level 
    pop.removeDescendents(llr); 
    System.out.println("REMOVED ALL DESCENDENTS OF A NODE WITH REMOVEDESCENDENTS");
    pop.printDiagram(pop, pop.getRoot());
    pop.removeNode(llr); // now remove the node itself
    pop.printDiagram(pop, pop.getRoot());
    pop.removeNode(lCh); // remove 1st node in 2nd level
    System.out.println("NODES NOW PROMOTED THROUGH 4 LAYERS");
    pop.printDiagram(pop, pop.getRoot()); 
    pop.updateHeightAndSize();
    System.out.println("POP Size: " + pop.getBTSize());
    System.out.println("POP Height: " + pop.getBTHeight());
    System.out.println(); 
   
    rCh = rt.getRight();
    pop.removeSubtree(rCh); // remove subtree from 2nd node in 2nd level
    System.out.println("DELETE ENTIRE RIGHT OF TREE BY REMOVESUBTREE:");
    pop.printDiagram(pop, pop.getRoot());

    NodeList<Node<Integer>> popNodeList = pop.getTreeDiagramList();
    pop.printList(popNodeList); 
    
    pop.updateHeightAndSize();
    System.out.println("POP Size: " + pop.getBTSize());
    System.out.println("POP Height: " + pop.getBTHeight());

  }// end level5NodesTest


  // MAIN METHOD- TEST PURPOSES
  public static void main(String[] args) 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    level4NodesTest();
    level5NodesTest();
  }// End main
}
