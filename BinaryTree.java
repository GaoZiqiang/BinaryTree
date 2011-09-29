import java.util.*;

public class BinaryTree<E> {
  
  protected int btSize;
  protected int btHeight;
  protected Node<E> root; 
  
  public BinaryTree() {
    btSize = 0;
    btHeight = 0;
    root = null;   
  }
  
  public int getBTSize() {return btSize;}

  public int getBTHeight() {return btHeight;}
  
  public Node<E> getRoot() throws EmptyTreeException {
    if (root == null) throw new EmptyTreeException("Empty tree");
    return root;
  }
  
  public Node<E> getParent(Node<E> n) throws BoundaryException {
    Node<E> p = n.getParent();
    if (p == null) throw new BoundaryException("No parent");
    return p;
  } 
 
  public Node<E> getLeft(Node<E> n) {
    Node<E> l = n.getLeft();
    return l;
  }

  public Node<E> getRight(Node<E> n) {
    Node<E> r = n.getRight();
    return r;
  }

  public Node<E> getSibling(Node<E> n) throws BoundaryException {
    Node<E> p = getParent(n);
    if (p != null) {
      Node<E> s;
      Node<E> l = getLeft(p);
      if (l == n) s = getRight(p);
      else s = l;
      if (s != null) return s;
    }
    throw new BoundaryException("Node has no sibling");
  }
  
  public boolean hasLeft(Node<E> n) throws InvalidNodeException {
    if (n == null) throw new InvalidNodeException("Node is null");
    return (n.getLeft() != null);
  }
  
  public boolean hasRight(Node<E> n) throws InvalidNodeException {
    if (n == null) throw new InvalidNodeException("Node is null");
    return (n.getRight() != null);
  }
   
  public boolean isEmpty() {return (btSize == 0);}
  
  public boolean isRoot(Node<E> n) 
    throws InvalidNodeException, EmptyTreeException {
    if (n == null) throw new InvalidNodeException("Node is null");
    return (n == getRoot());
  }
 
  public boolean isInternal(Node<E> n) throws InvalidNodeException {
    if (n == null) throw new InvalidNodeException("Node is null");
    return (hasLeft(n) || hasRight(n));
  }
  
  public NodeList<E> getChildList(Node<E> n) throws InvalidNodeException {
    NodeList<E> childList = new NodeList<E>(); 
    if (hasLeft(n)) childList.addLast(n.getLeft().getElement());  // Recursive
    if (hasRight(n)) childList.addLast(n.getRight().getElement()); // Recursive
    return childList;
  }  
  
  // Below areauxiliary methods for listing tree node properties, including 
  // for non-complete trees and subtrees. All lists may be printed out using
  // other methods, and some are used by the printDiagram method. 
  public NodeList<Node<E>> getTreeList() 
  throws InvalidNodeException, EmptyTreeException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeList(getRoot(), treeList);
    return treeList;
  }// Above method employs the one below  
  public void getSubtreeList(Node<E> n, NodeList<Node<E>> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n);
    if (hasLeft(n)) getSubtreeList(n.getLeft(), l); 
    if (hasRight(n)) getSubtreeList(n.getRight(), l);
  }
  // Lists the level at each node
  public void getSubtreeLevelsList(Node<E> n, NodeList<Integer> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n.getLevel());
    if (hasLeft(n)) getSubtreeLevelsList(n.getLeft(), l); 
    if (hasRight(n)) getSubtreeLevelsList(n.getRight(), l);
  } 
  // For whole tree, from root
  public NodeList<Node<E>> getTreeDiagramList() throws InvalidNodeException,
  EmptyTreeException, EmptyListException, BoundaryException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeDiagramList(getRoot(), treeList, 1);
    return treeList;
  }
  // For subtree, defined from node
  public NodeList<Node<E>> getTreeDiagramListFromNode(Node<E> n) 
  throws InvalidNodeException, EmptyTreeException, EmptyListException,
  BoundaryException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeDiagramList(n, treeList, 1);
    return treeList;
  }// Above two methods employs the one below  
  public void getSubtreeDiagramList(Node<E> n, NodeList<Node<E>> dl, int pos) 
  throws InvalidNodeException, EmptyTreeException {
    n.setPosition(pos);
    dl.addLast(n);
    int posn;
    if (hasLeft(n)) { 
      posn = pos*2 -1;
      getSubtreeDiagramList(n.getLeft(), dl, posn); // Recursive
    } 
    if (hasRight(n)) { 
      posn = pos*2;
      getSubtreeDiagramList(n.getRight(), dl, posn); // Recursive   
    }    
  }

  public void setBTHeight(int i) {
    this.btHeight = i;
  }

  public Node<E> setRoot(E e) throws TreeNotEmptyException {
    if (!isEmpty()) throw new TreeNotEmptyException("Tree is not empty");
    btSize = 1;
    btHeight = 1;
    root = new Node<E>(e, null, null, null);
    root.setLevel(1);
    return root;
  }

  public Node<E> setLeft(Node<E> n, E e) throws InvalidNodeException {
    Node<E> test = getLeft(n);
    if (test != null) throw new InvalidNodeException("Node already has left child");
    Node<E> l = new Node<E>(e, n, null, null);
    n.setLeft(l);
    btSize++;
    if (n.getLevel() == btHeight) {
      btHeight++;
      l.setLevel(btHeight);
    } 
    else {l.setLevel(n.getLevel() + 1);}
    return l;
  }

  public Node<E> setRight(Node<E> n, E e) throws InvalidNodeException {
    Node<E> test = getRight(n);
    if (test != null) throw new InvalidNodeException("Node already has left child");
    Node<E> r = new Node<E>(e, n, null, null);
    n.setRight(r);
    btSize++;
    if (n.getLevel() == btHeight) {
      btHeight++;
      r.setLevel(btHeight);
    } 
    else {r.setLevel(n.getLevel() + 1);}
    return r;
  }

  //Remove a node, replace it with its one child, two children causes error
  // and return element stored at the removed node
  //NEED TO SUBTRACT 1 FROM LEVEL FOR EACH SUBNODE OF n AND POSSIBLY
  //DECREMENT BTHEIGHT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public E removeNode(Node<E> n) throws InvalidNodeException,
  BoundaryException {
    Node<E> l = getLeft(n);
    Node<E> r = getRight(n);
    if (l != null && r != null) 
      throw new InvalidNodeException("Node has two children");
    Node<E> child;
    if (l != null) child = l;
    else if (r != null) child = r;
    else child = null;  // Node to be removed is external
    if (n == root) {  // Node to be removed is the root
      if (child != null) child.setParent(null);
      root = child; 
    } 
    else {  // Node to be removed is not the root
      Node<E> p = n.getParent();
      if (n == p.getLeft()) p.setLeft(child); // N.B set to null if node is external
      else p.setRight(child);  // N.B set to null if node to be removed is external
      if (child != null) child.setParent(p); // Node removed was internal
    }
    btSize--;
    return n.getElement();
  }

  public E replaceElement(Node<E> n, E e) throws InvalidNodeException {
    E oldElement = n.getElement();
    n.setElement(e);
    return oldElement;
  }
 
  public void setDescendentLevels(Node<E> rootSub) throws InvalidNodeException {
    int parentLevel = rootSub.getParent().getLevel();
    rootSub.setLevel(parentLevel + 1);
    if (hasLeft(rootSub)) this.setDescendentLevels(rootSub.getLeft()); // Recursive
    if (hasRight(rootSub)) this.setDescendentLevels(rootSub.getRight());// Recursive
  } 
  // Attach two trees as subtrees of an external node
  // i.e. the roots of these trees become children of the node
  public void attachTrees(Node<E> n, BinaryTree<E> TLeft, BinaryTree<E> TRight) 
  throws InvalidNodeException, EmptyTreeException {
    if (isInternal(n)) throw new InvalidNodeException("Node is not external");
    btSize = btSize + TLeft.getBTSize() + TRight.getBTSize();
    int TLeftHeight = TLeft.getBTHeight();
    int TRightHeight = TRight.getBTHeight();
    int maxSubHeight = (TLeftHeight > TRightHeight) ? TLeftHeight : TRightHeight;
    int oldHeight = this.getBTHeight();
    int attachmentNodeLevel = n.getLevel(); 
    int hOffset = oldHeight - attachmentNodeLevel;
    this.setBTHeight(oldHeight + maxSubHeight - hOffset);
    if (!TLeft.isEmpty()) {
      Node<E> leftTreeRoot = TLeft.getRoot();
      n.setLeft(leftTreeRoot);
      leftTreeRoot.setParent(n);
      leftTreeRoot.setLevel(attachmentNodeLevel);
      setDescendentLevels(leftTreeRoot);
    }
    if (!TRight.isEmpty()) {
      Node<E> rightTreeRoot = TRight.getRoot();
      n.setRight(rightTreeRoot);
      rightTreeRoot.setParent(n);
      rightTreeRoot.setLevel(attachmentNodeLevel);
      setDescendentLevels(rightTreeRoot);
    }       
  }

  // Print the elements stored in each node
  public void printList(NodeList<Node<E>> nl) throws EmptyListException, 
  InvalidNodeException, BoundaryException {
    ListNode<Node<E>> nextNode = nl.getFirst();
    for (int i = 0; i < nl.getNLSize() -1; i++) {     
      System.out.print( nextNode.getElement().getElement() + " " );
      nextNode = nl.getNext(nextNode);
    } 
    System.out.print( nextNode.getElement().getElement() + " " );
    System.out.println();
  }

  // Main use is to print level of each node, when used after calling 
  // getSubtreeLevelsList, although could be used whenever argument of
  // NodeList<E> is applicable (compare to NodeList<Node<E>> for printList) 
  public void printLevelList(NodeList<E> nl) throws EmptyListException, 
  InvalidNodeException, BoundaryException {
    ListNode<E> nextNode = nl.getFirst();
    //if (nl.getNLSize() == 1) { System.out.print( nextNode.getElement() + " " ); };
    for (int i = 0; i < nl.getNLSize() -1; i++) { // Only operates for size > 1;    
      System.out.print( nextNode.getElement() + " " );
      nextNode = nl.getNext(nextNode);
    } 
    System.out.print( nextNode.getElement() + " ");
    System.out.println();
  }

  // Prints a diagram of tree, or subtree, including non-complete trees.
  // Requires a full screen, and is tidiest for trees with only single digit 
  // elements, e.g. as in common usage of holding binary digits 1, 0.
  // Only shows 6 levels from root or declared node
  public void printDiagram(BinaryTree<E> t, Node<E> n) throws
  InvalidNodeException, EmptyTreeException, EmptyListException, EmptyListException,
  BoundaryException  {
    System.out.println("This diagram is for full screen run only");
    System.out.println("Only displays the top 6 levels of tree or subtree");      
    NodeList<Node<E>> nl = t.getTreeDiagramListFromNode(n);
    t.printList(nl);
    int height = t.getBTHeight();    
    int level = n.getLevel(); 
    int diagramHeight;
    if ((height < 7) || (height - level < 5)) diagramHeight = height - level + 1;
    else diagramHeight = 6;  // Max no. diagram levels 6  
    NodeList<Node<E>> level1 = new NodeList<Node<E>>();
    NodeList<Node<E>> level2 = new NodeList<Node<E>>();
    NodeList<Node<E>> level3 = new NodeList<Node<E>>();
    NodeList<Node<E>> level4 = new NodeList<Node<E>>();
    NodeList<Node<E>> level5 = new NodeList<Node<E>>();
    NodeList<Node<E>> level6 = new NodeList<Node<E>>();
    int lev;
    int size = nl.getNLSize();
    ListNode<Node<E>> lndn = nl.getFirst(); // is root of printed tree (or subtree)
    Node<E> dn;    
    for (int i = 1; i < size; i++) {
      lndn = nl.getNext(lndn);
      dn = lndn.getElement();
      lev = dn.getLevel() - level + 1;//e.g.root lev 10, next lev 11-10 + 1 =2       
      switch (lev) {
        case 2:  level2.addLast(dn);  break;
        case 3:  level3.addLast(dn);  break;
        case 4:  level4.addLast(dn);  break;
        case 5:  level5.addLast(dn);  break;
        case 6:  level6.addLast(dn);  break;
        default: ;  break;
      }
    }// end for           
    for (int proxyLev = 1; proxyLev < diagramHeight + 1; proxyLev++) {        
      switch (proxyLev) { 
        case 1:  processRoot(n); break;
        case 2:  processLevel2(level2); break;
        case 3:  processLevel3(level3); break;
        case 4:  processLevel4(level4); break;
        case 5:  processLevel5(level5); break;
        case 6:  processLevel6(level6); break; 
        default: ;  break;
      } 
    }
    System.out.println();
  } // end printDiagramGenericized

  public void processRoot(Node<E> n) throws InvalidNodeException {
    E e = n.getElement();
    int pos = n.getPosition();    
    if (pos == 1) { // i.e. if pos is not null
      printSpaces(79);
      System.out.print(e);
      System.out.println();
    }
  }// end processRoot

  public void processLevel2(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int tally = 0;
    int size = nl.getNLSize();
    if (size == 0) return;
    int posDeficit = 0;
    int posDefPrev = 0;
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();     
    // process first node
    if (pos == 1) tally += printSpaces(39); 
    else tally += printSpaces(119 - tally);
    System.out.print(e);
    tally++;
    // process second node if exists
    if (size == 2) {
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition(); 
      if (pos == 2) {
        tally += printSpaces(119 - tally);
        System.out.print(e);
      }
    }
    System.out.println();    
  }// end processLevel2

  public void processLevel3(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int tally = 0;
    int size = nl.getNLSize();
    if (size == 0) return;
    int posDeficit = 0;
    int posDefPrev = 0;
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
     
    // process first node
    switch (pos) {
      case 1:  tally += printSpaces(19);  break;
      case 2:  tally += printSpaces(59 - tally);  break;
      case 3:  tally += printSpaces(99 - tally);  break;
      case 4:  tally += printSpaces(139 - tally);  break;
      default: ;  break;
    }
    System.out.print(e);
    tally++;
    // process all nodes after first
    for (int i = 1; i < size; i++) { 
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition(); 
      switch (pos) {
        case 2:  tally += printSpaces(59 - tally);  break;
        case 3:  tally += printSpaces(99 - tally);  break;
        case 4:  tally += printSpaces(139 - tally);  break;
        default: ;  break;
      }  
      System.out.print(e);
      tally++;
    }// end for
    System.out.println();    
  }// end processLevel3

  public void processLevel4(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int tally = 0;
    int size = nl.getNLSize();
    if (size == 0) return;
    int posDeficit = 0;
    int posDefPrev = 0;
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
     
    // process first node
    switch (pos) {
      case 1:  tally += printSpaces(9);  break;//8
      case 2:  tally += printSpaces(29 - tally);  break;
      case 3:  tally += printSpaces(49 - tally);  break;
      case 4:  tally += printSpaces(69 - tally);  break;
      case 5:  tally += printSpaces(89 - tally);  break;
      case 6:  tally += printSpaces(109 - tally);  break;
      case 7:  tally += printSpaces(129 - tally);  break;
      case 8:  tally += printSpaces(149 - tally);  break;
      default: ;  break;
    }
    System.out.print(e);
    tally++;
    // process all nodes after first
    for (int i = 1; i < size; i++) { 
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition();
      switch (pos) {
        case 2:  tally += printSpaces(29 - tally);  break;
        case 3:  tally += printSpaces(49 - tally);  break;
        case 4:  tally += printSpaces(69 - tally);  break;
        case 5:  tally += printSpaces(89 - tally);  break;
        case 6:  tally += printSpaces(109 - tally);  break;
        case 7:  tally += printSpaces(129 - tally);  break;
        case 8:  tally += printSpaces(149 - tally);  break;
        default: ;  break;
      }  
      System.out.print(e);
      tally++;
    }// end for
    System.out.println();    
  }// end processLevel4

  public void processLevel5(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int tally = 0;
    int size = nl.getNLSize();
    if (size == 0) return;
    int posDeficit = 0;
    int posDefPrev = 0;
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
     
    // process first node
    switch (pos) {      
      case 1:  tally += printSpaces(4);  break;
      case 2:  tally += printSpaces(14 - tally);  break;
      case 3:  tally += printSpaces(24 - tally);  break;
      case 4:  tally += printSpaces(34 - tally);  break;
      case 5:  tally += printSpaces(44 - tally);  break;
      case 6:  tally += printSpaces(54 - tally);  break;
      case 7:  tally += printSpaces(64 - tally);  break;
      case 8:  tally += printSpaces(74 - tally);  break;
      case 9:  tally += printSpaces(84 - tally);  break;
      case 10:  tally += printSpaces(94 - tally);  break;
      case 11:  tally += printSpaces(104 - tally);  break;
      case 12:  tally += printSpaces(114 - tally);  break;
      case 13:  tally += printSpaces(124 - tally);  break;
      case 14:  tally += printSpaces(134 - tally);  break;
      case 15:  tally += printSpaces(144 - tally);  break;
      case 16:  tally += printSpaces(154 - tally);  break;
      default: ;  break;
    }
    System.out.print(e);
    tally++;
    // process all nodes after first
    for (int i = 1; i < size; i++) { 
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition(); 
      switch (pos) {
        case 2:  tally += printSpaces(14 - tally);  break;
        case 3:  tally += printSpaces(24 - tally);  break;
        case 4:  tally += printSpaces(34 - tally);  break;
        case 5:  tally += printSpaces(44 - tally);  break;
        case 6:  tally += printSpaces(54 - tally);  break;
        case 7:  tally += printSpaces(64 - tally);  break;
        case 8:  tally += printSpaces(74 - tally);  break;
        case 9:  tally += printSpaces(84 - tally);  break;
        case 10:  tally += printSpaces(94 - tally);  break;
        case 11:  tally += printSpaces(104 - tally);  break;
        case 12:  tally += printSpaces(114 - tally);  break;
        case 13:  tally += printSpaces(124 - tally);  break;
        case 14:  tally += printSpaces(134 - tally);  break;
        case 15:  tally += printSpaces(144 - tally);  break;
        case 16:  tally += printSpaces(154 - tally);  break;
        default: ;  break;
      }  
      System.out.print(e);
      tally++;
    }// end for
    System.out.println();    
  }// end processLevel5

  public void processLevel6(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int tally = 0;
    int size = nl.getNLSize();
    if (size == 0) return;
    int posDeficit = 0;
    int posDefPrev = 0;
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
     
    // process first node
    switch (pos) {      
      case 1:  tally += printSpaces(2);  break;
      case 2:  tally += printSpaces(6 - tally);  break; //6
      case 3:  tally += printSpaces(12 - tally);  break;//10
      case 4:  tally += printSpaces(16 - tally);  break;//14
      case 5:  tally += printSpaces(22 - tally);  break;//18
      case 6:  tally += printSpaces(26 - tally);  break;//22
      case 7:  tally += printSpaces(32 - tally);  break;//26
      case 8:  tally += printSpaces(36 - tally);  break;//30
      case 9:  tally += printSpaces(42 - tally);  break;//34
      case 10:  tally += printSpaces(46 - tally);  break;//38
      case 11:  tally += printSpaces(52 - tally);  break;//42
      case 12:  tally += printSpaces(56 - tally);  break;//46
      case 13:  tally += printSpaces(62 - tally);  break;//50
      case 14:  tally += printSpaces(66 - tally);  break;//54
      case 15:  tally += printSpaces(72 - tally);  break;//58
      case 16:  tally += printSpaces(76 - tally);  break;//62
      case 17:  tally += printSpaces(82 - tally);  break;//66
      case 18:  tally += printSpaces(86 - tally);  break;//70
      case 19:  tally += printSpaces(92 - tally);  break;//74
      case 20:  tally += printSpaces(96 - tally);  break;//78
      case 21:  tally += printSpaces(102 - tally);  break;//82
      case 22:  tally += printSpaces(106 - tally);  break;//86
      case 23:  tally += printSpaces(112 - tally);  break;//90
      case 24:  tally += printSpaces(116 - tally);  break;//94
      case 25:  tally += printSpaces(122 - tally);  break;//98
      case 26:  tally += printSpaces(126 - tally);  break;//102
      case 27:  tally += printSpaces(132 - tally);  break;//106
      case 28:  tally += printSpaces(136 - tally);  break;//110
      case 29:  tally += printSpaces(142 - tally);  break;//114
      case 30:  tally += printSpaces(146 - tally);  break;//118
      case 31:  tally += printSpaces(152 - tally);  break;//122
      case 32:  tally += printSpaces(156 - tally);  break;//126
      default: ;  break;
    }
    System.out.print(e);
    tally++;
    // process all nodes after first
    for (int i = 1; i < size; i++) { 
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition(); 
      switch (pos) {
        case 2:  tally += printSpaces(6 - tally);  break;
        case 3:  tally += printSpaces(12 - tally);  break;
        case 4:  tally += printSpaces(16 - tally);  break;
        case 5:  tally += printSpaces(22 - tally);  break;
        case 6:  tally += printSpaces(26 - tally);  break;
        case 7:  tally += printSpaces(32 - tally);  break;
        case 8:  tally += printSpaces(36 - tally);  break;
        case 9:  tally += printSpaces(42 - tally);  break;
        case 10:  tally += printSpaces(46 - tally);  break;
        case 11:  tally += printSpaces(52 - tally);  break;
        case 12:  tally += printSpaces(56 - tally);  break;
        case 13:  tally += printSpaces(62 - tally);  break;
        case 14:  tally += printSpaces(66 - tally);  break;
        case 15:  tally += printSpaces(72 - tally);  break;
        case 16:  tally += printSpaces(76 - tally);  break;
        case 17:  tally += printSpaces(82 - tally);  break;
        case 18:  tally += printSpaces(86 - tally);  break;
        case 19:  tally += printSpaces(92 - tally);  break;
        case 20:  tally += printSpaces(96 - tally);  break;
        case 21:  tally += printSpaces(102 - tally);  break;
        case 22:  tally += printSpaces(106 - tally);  break;
        case 23:  tally += printSpaces(112 - tally);  break;
        case 24:  tally += printSpaces(116 - tally);  break;
        case 25:  tally += printSpaces(122 - tally);  break;
        case 26:  tally += printSpaces(126 - tally);  break;
        case 27:  tally += printSpaces(132 - tally);  break;
        case 28:  tally += printSpaces(136 - tally);  break;
        case 29:  tally += printSpaces(142 - tally);  break;
        case 30:  tally += printSpaces(146 - tally);  break;
        case 31:  tally += printSpaces(152 - tally);  break;
        case 32:  tally += printSpaces(156 - tally);  break;
        default: ;  break;
      }  
      System.out.print(e);
      tally++;
    }// end for
    System.out.println();    
  }// end processLevel6

  public int printSpaces(int number){
    int t = 0;
    for (int j = 0; j < number; j++) { 
      System.out.print(" ");
      t++;
    }
    return t;
  }// end printSpaces

  public void printDiagramNodeListE(NodeList<Node<E>> nl) 
  throws EmptyListException, InvalidNodeException, BoundaryException {
    ListNode<Node<E>> nextListNode = nl.getFirst();
    Node<E> nextNode = nextListNode.getElement();
    if (nl.getNLSize() == 1) { 
      System.out.print( "El: " + nextNode.getElement() + " " );
      System.out.print( "Lev: " + nextNode.getLevel() + " " );
      System.out.print( "Pos: " + nextNode.getPosition() + " " );
      System.out.println();
    }
    for (int i = 0; i < nl.getNLSize() -1; i++) { // Only operates for size > 1;    
      System.out.print( "El: " + nextNode.getElement() + " " );
      System.out.print( "Lev: " + nextNode.getLevel() + " " );
      System.out.print( "Pos: " + nextNode.getPosition() + "   " );
      nextListNode = nl.getNext(nextListNode);
      nextNode = nextListNode.getElement();
    }
    if (nl.getNLSize() != 1) {
      System.out.print( "El: " + nextNode.getElement() + " " );
      System.out.print( "Lev: " + nextNode.getLevel() + " " );
      System.out.print( "Pos: " + nextNode.getPosition() + " " );
      System.out.println(); 
    }
  }  

}// End class BinaryTree


class Node<E> {
  private E element;
  private int level;
  private int position; // Define node's position on its level, left to right
  // with some positions null for non-complete trees
  private Node<E> parent, left, right;   
  public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
    setElement(e);
    setParent(p);
    setLeft(l);    
    setRight(r); 
  }
  public E getElement() {return element;}       
  public Node<E> getParent() {return parent;}
  public Node<E> getLeft() {return left;}
  public Node<E> getRight() {return right;}
  public int getLevel() {return level;}
  public int getPosition() {return position;}
  public void setParent(Node<E> p) {parent = p;}
  public void setLeft(Node<E> l) {left = l;}
  public void setRight(Node<E> r) {right = r;}
  public void setElement(E e) {element = e;}
  public void setLevel(int i) {level = i;}
  public void setPosition(int i) {position = i;}
}// End class Node<E>
 

class ListNode<E> {
  private E element;
  private ListNode<E> previous, next;
  public ListNode(E e, ListNode<E> p, ListNode<E> n) {
    element = e;
    previous = p;
    next = n;
  }
  public E getElement() throws InvalidNodeException {
    if ((previous == null) && (next == null)) 
      throw new InvalidNodeException("Invalid node");
    return element;
  }
  public ListNode<E> getPrevious() {return previous;}
  public ListNode<E> getNext() {return next;}  
  public void setPrevious(ListNode<E> p) {previous = p;}
  public void setNext(ListNode<E> l) {next = l;}
  public void setElement(E e) {element = e;}  
}// End class ListNode<E>


class NodeList<E> {
  private int nlSize;
  private ListNode<E> head, tail;
    
  public NodeList() {
    nlSize = 0;
    head = new ListNode<E>(null, null, null);
    tail = new ListNode<E>(null, head, null);
    head.setNext(tail);
  }
  public int getNLSize() {return nlSize;}

  public ListNode<E> getFirst() throws EmptyListException {
    if (isEmpty()) throw new EmptyListException("Empty list");
    return head.getNext();
  }
    
  public ListNode<E> getLast() throws EmptyListException {
    if (isEmpty()) throw new EmptyListException("Empty list");
    return tail.getPrevious();
    }
    
  public ListNode<E> getPrevious(ListNode<E> n) throws BoundaryException {
    ListNode<E> prv = n.getPrevious(); 
    if (prv == head) 
      throw new BoundaryException("attempt to access before start of list");
    return prv;
  }

  public ListNode<E> getNext(ListNode<E> n) throws BoundaryException {
    ListNode<E> nxt = n.getNext(); 
    if (nxt == tail) 
      throw new BoundaryException("attempt to access after end of list");
    return nxt;
  }
    
  public void addFirst(E e) {
    nlSize ++;
    ListNode<E> n = new ListNode<E>(e, head, head.getNext());
    head.getNext().setPrevious(n);
    head.setNext(n);
  }
    
  public void addLast(E e) {
    nlSize ++;
    ListNode<E> n = new ListNode<E>(e, tail.getPrevious(), tail);
    tail.getPrevious().setNext(n);
    tail.setPrevious(n);      
  }
    
  //more to do

  public boolean isEmpty() {return (nlSize == 0);}

}// End class NodeList<E>


class InvalidNodeException extends Exception {
  public InvalidNodeException(String s){
    System.out.println(s);
  }
}

class EmptyTreeException extends Exception {
  public EmptyTreeException(String s){
    System.out.println(s);
  }
}

class EmptyListException extends Exception {
  public EmptyListException(String s){
    System.out.println(s);
  }
}

class BoundaryException extends Exception {
  public BoundaryException(String s){
    System.out.println(s);
  }
}

class TreeNotEmptyException extends Exception {
  public TreeNotEmptyException(String s){
    System.out.println(s);
  }
}

