
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
    Integer[] d = {8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,8,9,0,1,2,3,4,5,6,7,8,9,0,1,null,3};   
    Integer[][] e = { {1},{2,3},{4,5,6,7}, {8,9,0,1,2,3,4,5}, c, d };
    System.out.println();
    System.out.println("TREE WITH 6 LEVELS:");
    pop = pop.createPopulatedIntegerTree(e);
    pop.printDiagram(pop, pop.getRoot()); 
  }// end level5NodesTest


  // MAIN METHOD- TEST PURPOSES
  public static void main(String[] args) 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    level4NodesTest();
    level5NodesTest();
  }// End main
}
