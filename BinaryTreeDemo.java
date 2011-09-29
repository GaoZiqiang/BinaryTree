
public class BinaryTreeDemo {

  /*
  public static void testRoutine1() // A TEST ROUTINE
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> bt4 = new BinaryTree<Integer>();
    bt4.setRoot(40);
    Node<Integer> root4 = bt4.getRoot();
    Node<Integer> leftCh4l1 = bt4.setLeft(root4, 41);
    Node<Integer> leftCh4l2 = bt4.setLeft(leftCh4l1, 42);
    Node<Integer> leftCh4l3 = bt4.setLeft(leftCh4l2, 43);
    Integer left4intL2 = leftCh4l2.getElement();
    Integer left4intL3 = leftCh4l3.getElement();
    System.out.println("BT4 left2: " + left4intL2);
    System.out.println("BT4 left3: " + left4intL3);
    System.out.println("BT4 Size: " + bt4.getBTSize());   
    BinaryTree<Integer> bt5 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt6 = new BinaryTree<Integer>();
    Node<Integer> root5 = bt5.setRoot(10);
    bt5.setLeft(root5, 11);
    bt5.setRight(root5, 12);
    Node<Integer> root6 = bt6.setRoot(20);
    bt6.setLeft(root6, 21);
    bt6.setRight(root6, 22);

    bt4.attachTrees(leftCh4l3, bt5, bt6); // Attach trees
    System.out.println("BT4 Size: " + bt4.getBTSize());
    NodeList<Node<Integer>> nList4 = bt4.getTreeList();
    System.out.print("BT4 list: ");
    printList(nList4); // Print list    
    Node<Integer> leftLev4 = leftCh4l3.getLeft();
    Integer leftLev4int = leftLev4.getElement();
    System.out.println("BT4 left4: " + leftLev4int);
    Node<Integer> leftLev5 = leftLev4.getLeft();
    Integer leftLev5int = leftLev5.getElement();
    System.out.println("BT5 left5: " + leftLev5int);    
    NodeList<Node<Integer>> aSubList = new NodeList<Node<Integer>>();
    bt4.getSubtreeList(leftLev4, aSubList );    
    printList(aSubList);
    NodeList<Node<Integer>> aSingleSubList = new NodeList<Node<Integer>>();
    bt4.getSubtreeList(leftLev5, aSingleSubList );
    printList(aSingleSubList);

    Integer replace = 1612;
    bt4.replaceElement(leftLev4, replace);
    NodeList<Node<Integer>> nListReplaced = bt4.getTreeList(); // Get list
    printList(nListReplaced);
    replace = 92000023;
    bt4.replaceElement(leftLev5, replace);
    nListReplaced = bt4.getTreeList(); // Get list
    printList(nListReplaced);   
    //CANT DELETE AN INTERNAL NODE!!!!!
    bt4.removeNode(leftLev5); // works when do both!!
    bt4.removeNode(leftLev4); // doesn't, when dont do 5 first!
    nListReplaced = bt4.getTreeList(); // Get list
    printList(nListReplaced);
    NodeList<Integer> levelsList = new NodeList<Integer>();
    bt4.getSubtreeLevelsList(bt4.getRoot(), levelsList);
    System.out.println("BT4 Size: " + bt4.getBTSize());
    printList2(levelsList);
  }// end testRoutine1
  */
  
  public static void removeLevel4NodesTest() // A TEST ROUTINE
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> bt2 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt3 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt4 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt5 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt6 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt7 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt8 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt9 = new BinaryTree<Integer>();
    Node<Integer> root2 = bt2.setRoot(0);    
    Node<Integer> root3 = bt3.setRoot(3);    
    Node<Integer> root4 = bt4.setRoot(6);    
    Node<Integer> root5 = bt5.setRoot(9);    
    Node<Integer> root6 = bt6.setRoot(2);    
    Node<Integer> root7 = bt7.setRoot(5);    
    Node<Integer> root8 = bt8.setRoot(8);    
    Node<Integer> root9 = bt9.setRoot(1);
      
    BinaryTree<Integer> bt70 = new BinaryTree<Integer>();
    bt70.setRoot(8);
    Node<Integer> rt = bt70.getRoot();
    Node<Integer> lCh = bt70.setLeft(rt, 7);
    Node<Integer> rCh = bt70.setRight(rt, 6);
    Node<Integer> llCh = bt70.setLeft(lCh, 5);
    Node<Integer> lrCh = bt70.setRight(lCh, 4);
    Node<Integer> rlCh = bt70.setLeft(rCh, 3);
    Node<Integer> rrCh = bt70.setRight(rCh, 2);
    System.out.println();    
   // NB ATTACHING SAME TREES MORE THAN ONCE CAN CAUSE AN ANOMALY, NAMELY 
   // INVALIDNODEeXCEPTION, TRYING TO ATTACH TREES TO "INTERNAL NODES", IF
   // SUBSEQUENT TREES LATER ATTEMPT ATTACHMENT BELOW   
    bt70.attachTrees(llCh, bt2, bt3); // Attach trees VALID POINTERS
    bt70.attachTrees(lrCh, bt4, bt5); 
    bt70.attachTrees(rlCh, bt6, bt7); 
    bt70.attachTrees(rrCh, bt8, bt9); 
 
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

  }// end removeLevel4NodesTest




  public static void removeLevel5NodesTest() // A TEST ROUTINE
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> bt2 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt3 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt4 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt5 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt6 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt7 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt8 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt9 = new BinaryTree<Integer>();
    Node<Integer> root2 = bt2.setRoot(0);
    bt2.setLeft(root2, 1);
    bt2.setRight(root2, 2);
    Node<Integer> root3 = bt3.setRoot(3);
    bt3.setLeft(root3, 4);
    bt3.setRight(root3, 5);
    Node<Integer> root4 = bt4.setRoot(6);
    bt4.setLeft(root4, 7);
    bt4.setRight(root4, 8);
    Node<Integer> root5 = bt5.setRoot(9);
    bt5.setLeft(root5, 0);
    bt5.setRight(root5, 1);
    Node<Integer> root6 = bt6.setRoot(2);
    bt6.setLeft(root6, 3);
    bt6.setRight(root6, 4);
    Node<Integer> root7 = bt7.setRoot(5);
    bt7.setLeft(root7, 6);
    bt7.setRight(root7, 7);
    Node<Integer> root8 = bt8.setRoot(8);
    bt8.setLeft(root8, 9);
    bt8.setRight(root8, 0);
    Node<Integer> root9 = bt9.setRoot(1);
    bt9.setLeft(root9, 2);
    bt9.setRight(root9, 3);
  
    BinaryTree<Integer> bt70 = new BinaryTree<Integer>();
    bt70.setRoot(8);
    Node<Integer> rt = bt70.getRoot();
    Node<Integer> lCh = bt70.setLeft(rt, 7);
    Node<Integer> rCh = bt70.setRight(rt, 6);
    Node<Integer> llCh = bt70.setLeft(lCh, 5);
    Node<Integer> lrCh = bt70.setRight(lCh, 4);
    Node<Integer> rlCh = bt70.setLeft(rCh, 3);
    Node<Integer> rrCh = bt70.setRight(rCh, 2);
    System.out.println();    
   // NB ATTACHING SAME TREES MORE THAN ONCE CAN CAUSE AN ANOMALY, NAMELY 
   // INVALIDNODEeXCEPTION, TRYING TO ATTACH TREES TO "INTERNAL NODES", IF
   // SUBSEQUENT TREES LATER ATTEMPT ATTACHMENT BELOW   
    bt70.attachTrees(llCh, bt2, bt3); // Attach trees VALID POINTERS
    bt70.attachTrees(lrCh, bt4, bt5); 
    bt70.attachTrees(rlCh, bt6, bt7); 
    bt70.attachTrees(rrCh, bt8, bt9); 
 
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
    System.out.println();      
    bt70.printDiagram(bt70, bt70.getRoot());
    System.out.println("MARKER 1");
    bt70.removeNode(llll); // remove 1st node in 5th level 
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(lllr); // remove 2nd node 
    bt70.printDiagram(bt70, bt70.getRoot());
    bt70.removeNode(rrrl); // remove 2nd to last node 
    bt70.printDiagram(bt70, bt70.getRoot());
    
  }// end removeLevel5NodesTest



  public static void testRoutine2() // A TEST ROUTINE
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    BinaryTree<Integer> bt2 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt3 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt4 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt5 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt6 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt7 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt8 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt9 = new BinaryTree<Integer>();
    Node<Integer> root2 = bt2.setRoot(0);
    bt2.setLeft(root2, 1);
    bt2.setRight(root2, 2);
    Node<Integer> root3 = bt3.setRoot(3);
    bt3.setLeft(root3, 4);
    bt3.setRight(root3, 5);
    Node<Integer> root4 = bt4.setRoot(6);
    bt4.setLeft(root4, 7);
    bt4.setRight(root4, 8);
    Node<Integer> root5 = bt5.setRoot(9);
    bt5.setLeft(root5, 0);
    bt5.setRight(root5, 1);
    Node<Integer> root6 = bt6.setRoot(2);
    bt6.setLeft(root6, 3);
    bt6.setRight(root6, 4);
    Node<Integer> root7 = bt7.setRoot(5);
    bt7.setLeft(root7, 6);
    bt7.setRight(root7, 7);
    Node<Integer> root8 = bt8.setRoot(8);
    bt8.setLeft(root8, 9);
    bt8.setRight(root8, 0);
    Node<Integer> root9 = bt9.setRoot(1);
    bt9.setLeft(root9, 2);
    bt9.setRight(root9, 3);
  
    BinaryTree<Integer> bt70 = new BinaryTree<Integer>();
    bt70.setRoot(8);
    Node<Integer> rt = bt70.getRoot();
    Node<Integer> lCh = bt70.setLeft(rt, 7);
    Node<Integer> rCh = bt70.setRight(rt, 6);
    Node<Integer> llCh = bt70.setLeft(lCh, 5);
    Node<Integer> lrCh = bt70.setRight(lCh, 4);
    Node<Integer> rlCh = bt70.setLeft(rCh, 3);
    Node<Integer> rrCh = bt70.setRight(rCh, 2);
    System.out.println();    
   // NB ATTACHING SAME TREES MORE THAN ONCE CAN CAUSE AN ANOMALY, NAMELY 
   // INVALIDNODEeXCEPTION, TRYING TO ATTACH TREES TO "INTERNAL NODES", IF
   // SUBSEQUENT TREES LATER ATTEMPT ATTACHMENT BELOW   
    bt70.attachTrees(llCh, bt2, bt3); // Attach trees VALID POINTERS
    bt70.attachTrees(lrCh, bt4, bt5); 
    bt70.attachTrees(rlCh, bt6, bt7); 
    bt70.attachTrees(rrCh, bt8, bt9); 
 
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
    Integer i = llll.getElement();
    System.out.println("Element of llll: " + i);   
    Integer l2lev = llCh.getLevel();
    System.out.println("Level of ll: " + l2lev); 
    Integer l3lev = lll.getLevel();
    System.out.println("Level of lll: " + l3lev);
    Integer l4lev = llll.getLevel();
    System.out.println("Level of llll: " + l4lev); 
    BinaryTree<Integer> bt80 = new BinaryTree<Integer>();
    bt80.setRoot(8);
    BinaryTree<Integer> bt81 = new BinaryTree<Integer>();
    bt81.setRoot(8);
    BinaryTree<Integer> bt82 = new BinaryTree<Integer>();
    bt82.setRoot(8);
    BinaryTree<Integer> bt83 = new BinaryTree<Integer>();
    bt83.setRoot(8);
    BinaryTree<Integer> bt84 = new BinaryTree<Integer>();
    bt84.setRoot(8);
    BinaryTree<Integer> bt85 = new BinaryTree<Integer>();
    bt85.setRoot(8);
    BinaryTree<Integer> bt86 = new BinaryTree<Integer>();
    bt86.setRoot(8);
    BinaryTree<Integer> bt87 = new BinaryTree<Integer>();
    bt87.setRoot(8);
    BinaryTree<Integer> bt88 = new BinaryTree<Integer>();
    bt88.setRoot(8);
    BinaryTree<Integer> bt89 = new BinaryTree<Integer>();
    bt89.setRoot(8);
    BinaryTree<Integer> bt90 = new BinaryTree<Integer>();
    bt90.setRoot(8);
    BinaryTree<Integer> bt91 = new BinaryTree<Integer>();
    bt91.setRoot(8);
    BinaryTree<Integer> bt92 = new BinaryTree<Integer>();
    bt92.setRoot(8);
    BinaryTree<Integer> bt93 = new BinaryTree<Integer>();
    bt93.setRoot(8);
    BinaryTree<Integer> bt94 = new BinaryTree<Integer>();
    bt94.setRoot(8);
    BinaryTree<Integer> bt95 = new BinaryTree<Integer>();
    bt95.setRoot(8);
    BinaryTree<Integer> bt96 = new BinaryTree<Integer>();
    bt96.setRoot(8);
    BinaryTree<Integer> bt97 = new BinaryTree<Integer>();
    bt97.setRoot(8);
    BinaryTree<Integer> bt98 = new BinaryTree<Integer>();
    bt98.setRoot(8);
    BinaryTree<Integer> bt99 = new BinaryTree<Integer>();
    bt99.setRoot(8);
    BinaryTree<Integer> bt100 = new BinaryTree<Integer>();
    bt100.setRoot(8);
    BinaryTree<Integer> bt101 = new BinaryTree<Integer>();
    bt101.setRoot(8);
    BinaryTree<Integer> bt102 = new BinaryTree<Integer>();
    bt102.setRoot(8);
    BinaryTree<Integer> bt103 = new BinaryTree<Integer>();
    bt103.setRoot(8);
    BinaryTree<Integer> bt104 = new BinaryTree<Integer>();
    bt104.setRoot(8);
    BinaryTree<Integer> bt105 = new BinaryTree<Integer>();
    bt105.setRoot(8);
    BinaryTree<Integer> bt106 = new BinaryTree<Integer>();
    bt106.setRoot(8);
    BinaryTree<Integer> bt107 = new BinaryTree<Integer>();
    bt107.setRoot(8);
    BinaryTree<Integer> bt108 = new BinaryTree<Integer>();
    bt108.setRoot(8);
    BinaryTree<Integer> bt109 = new BinaryTree<Integer>();
    bt109.setRoot(8);
    BinaryTree<Integer> bt110 = new BinaryTree<Integer>();
    bt110.setRoot(8);
    BinaryTree<Integer> bt111 = new BinaryTree<Integer>();
    bt111.setRoot(8);    
    bt70.attachTrees(llll, bt80, bt81); // attach trees
    bt70.attachTrees(lllr, bt82, bt83); 
    bt70.attachTrees(llrl, bt84, bt85); 
    bt70.attachTrees(llrr, bt86, bt87); 
    bt70.attachTrees(lrll, bt88, bt89); 
    bt70.attachTrees(lrlr, bt90, bt91); 
    bt70.attachTrees(lrrl, bt92, bt93); 
    bt70.attachTrees(lrrr, bt94, bt95); 
    bt70.attachTrees(rlll, bt96, bt97); 
    bt70.attachTrees(rllr, bt98, bt99); 
    bt70.attachTrees(rlrl, bt100, bt101); 
    bt70.attachTrees(rlrr, bt102, bt103); 
    bt70.attachTrees(rrll, bt104, bt105); 
    bt70.attachTrees(rrlr, bt106, bt107); 
    bt70.attachTrees(rrrl, bt108, bt109); 
    bt70.attachTrees(rrrr, bt110, bt111);  
    System.out.println("MARKER 1");
      
    bt70.printDiagram(bt70, bt70.getRoot());
   
    Node<Integer> lllll = llll.getRight();
    Integer l5El = lllll.getLevel();
    System.out.println("lllll level: " + l5El); 
    int h70 =  bt70.getBTHeight();
    System.out.println("BT70 Height: " + h70);      
    NodeList<Integer> levelsList70 = new NodeList<Integer>();
    bt70.getSubtreeLevelsList(bt70.getRoot(), levelsList70);
    System.out.print("BT70 Levels Augmented: "); 
    bt70.printLevelList(levelsList70);  
    NodeList<Node<Integer>> nListA = bt70.getTreeList();
    System.out.println("BT70 list from getTreeList using printList: ");
    bt70.printList(nListA);
    /*NodeList<Node<Integer>> dn = bt70.getDiagramList();
    int sz = dn.getNLSize();
    System.out.println("DN Size: " + sz); //Should be 63 for full 6 levels
    printDiagramNodeList(dn);
    */
    NodeList<Node<Integer>> nListB = bt70.getTreeDiagramList(); 
    System.out.println("BT70 list from getTreeDiagramList using printList: ");
    bt70.printList(nListB); 
    System.out.println("BT70 list from gTDL using printDiagramNodeList method: ");
    bt70.printDiagramNodeListE(nListB);  
   /*
    bt70.removeNode(rCh);
    nListB = bt70.getTreeDiagramList();
    level5 = bt70.sortDiagramList(nListB);
    printDiagramNodeList(level5);
  */
    BinaryTree<Integer> bt700 = new BinaryTree<Integer>();
    bt700.setRoot(8);
    Node<Integer> rt7 = bt700.getRoot();
    Node<Integer> lCh7 = bt700.setLeft(rt7, 7);
    Node<Integer> rCh7 = bt700.setRight(rt7, 6);
    Node<Integer> llCh7 = bt700.setLeft(lCh7, 5);
    Node<Integer> lrCh7 = bt700.setRight(lCh7, 4);
    Node<Integer> rlCh7 = bt700.setLeft(rCh7, 3);
    Node<Integer> rrCh7 = bt700.setRight(rCh7, 2);
    System.out.println("MARKER 2");
    bt700.printDiagram(bt700, bt700.getRoot());  
    int h700 =  bt700.getBTHeight();
    System.out.println("BT700 Height: " + h700);
    System.out.println("MARKER X");
    bt700.printDiagram(bt700, bt700.getRoot());
    bt700.printDiagram(bt700, bt700.getRoot().getLeft());
    bt700.printDiagram(bt700, bt700.getRoot().getLeft().getRight());  
    //nListB = bt700.getTreeDiagramList();    
    System.out.println("MARKER 3");
    bt700.printDiagram(bt700, bt700.getRoot()); 
    bt700.removeNode(lrCh7); // remove node 4
    bt700.printDiagram(bt700, bt700.getRoot());
    bt700.removeNode(llCh7); // remove node 5
    bt700.printDiagram(bt700, bt700.getRoot());
  }// end testRoutine2



  // MAIN METHOD- TEST PURPOSES
  public static void main(String[] args) 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    testRoutine2();
    removeLevel4NodesTest();
    removeLevel5NodesTest();
  }// End main
}
