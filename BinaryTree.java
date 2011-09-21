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
    //if (l == null) throw new BoundaryException("No left child");
    // Do not throw any exception, OK to return a null
    return l;
  }

  public Node<E> getRight(Node<E> n) {
    Node<E> r = n.getRight();
    //if (r == null) throw new BoundaryException("No right child");
    // Do not throw any exception, OK to return a null
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
  
  public NodeList<Node<E>> getTreeList() 
  throws InvalidNodeException, EmptyTreeException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeList(getRoot(), treeList);
    return treeList;
  }// Above method employs the one below  
  public void getSubtreeList(Node<E> n, NodeList<Node<E>> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n);
    if (hasLeft(n)) getSubtreeList(n.getLeft(), l); //use Node getLeft
    if (hasRight(n)) getSubtreeList(n.getRight(), l);// or BT getLeft?
  }
  
  public void getSubtreeLevelsList(Node<E> n, NodeList<Integer> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n.getLevel());
    if (hasLeft(n)) getSubtreeLevelsList(n.getLeft(), l); 
    if (hasRight(n)) getSubtreeLevelsList(n.getRight(), l);
  }

  // CATER FOR NON COMPLETE TREE, DISESTABLISH DIAGRAMNODE
  public NodeList<Node<E>> getTreeDiagramList() throws InvalidNodeException,
  EmptyTreeException, EmptyListException, BoundaryException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeDiagramList(getRoot(), treeList, 1);
    return treeList;
  }
  public NodeList<Node<E>> getTreeDiagramListFromNode(Node<E> n) 
  throws InvalidNodeException, EmptyTreeException, EmptyListException,
  BoundaryException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeDiagramList(n, treeList, 1);
    return treeList;
  }// Above two methods employs the one below  
  public void getSubtreeDiagramList(Node<E> n, NodeList<Node<E>> dl, 
  int pos) throws InvalidNodeException, EmptyTreeException {
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
  
  /*  /////REPLACE?????//////////////////
  public NodeList<DiagramNode<E>> getDiagramList() throws InvalidNodeException,
  EmptyTreeException, EmptyListException, BoundaryException {
    NodeList<DiagramNode<E>> diagramList = new NodeList<DiagramNode<E>>();
    NodeList<Node<E>> treeList = getTreeList();  
    int size = treeList.getNLSize();
    int pos=0, pos1=0, pos2=0, pos3=0, pos4=0, pos5=0, pos6=0;
    boolean levelInBounds = true;

    ListNode<Node<E>> listNode = treeList.getFirst();
    Node<E> node = listNode.getElement();
    E element = node.getElement();
    int level = 1;
    DiagramNode<E> dn = new DiagramNode<E>(element, level, 1);
    diagramList.addLast(dn);
  
    for (int i = 1; i < size; i++) {
      listNode = treeList.getNext(listNode);
      node = listNode.getElement();
      element = node.getElement();
      level = node.getLevel();      
      switch (level) {
        case 1:  pos1++;  pos = pos1;  break;
        case 2:  pos2++;  pos = pos2;  break;
        case 3:  pos3++;  pos = pos3;  break;
        case 4:  pos4++;  pos = pos4;  break;
        case 5:  pos5++;  pos = pos5;  break;
        case 6:  pos6++;  pos = pos6;  break;
        default: levelInBounds = false;  break;
      }
      if (levelInBounds) {        
        dn = new DiagramNode<E>(element, level, pos);
        diagramList.addLast(dn);
      }    
    }// end for    
    return diagramList;    
  } */
  
  //////////////////////////////////////////////////////////////////////////
  ////NOT REQUIRED?????????????????????????????????????????????
  public NodeList<Node<E>> sortDiagramList(NodeList<Node<E>> nl) 
  throws EmptyListException, InvalidNodeException, BoundaryException {
    NodeList<Node<E>> level2 = new NodeList<Node<E>>();
    NodeList<Node<E>> level3 = new NodeList<Node<E>>();
    NodeList<Node<E>> level4 = new NodeList<Node<E>>();
    NodeList<Node<E>> level5 = new NodeList<Node<E>>();
    NodeList<Node<E>> level6 = new NodeList<Node<E>>();
    int level;

    int size = nl.getNLSize();
    ListNode<Node<E>> lndn = nl.getFirst();
    Node<E> dn = lndn.getElement();
    level2.addLast(dn); // Can put root in level2, as root is always first
    for (int i = 1; i < size; i++) {
      lndn = nl.getNext(lndn);
      dn = lndn.getElement();
      level = dn.getLevel();      
      switch (level) {
        case 2:  level2.addLast(dn);  break;
        case 3:  level3.addLast(dn);  break;
        case 4:  level4.addLast(dn);  break;
        case 5:  level5.addLast(dn);  break;
        case 6:  level6.addLast(dn);  break;
      }
    }// end for
    NodeList<Node<E>> sorted = new NodeList<Node<E>>();
    //COMBINE LEVEL2 .. LEVEL6 CONTENTS INTO SORTED
    int size2 = level2.getNLSize();
    int size3 = level3.getNLSize();
    int size4 = level4.getNLSize();
    int size5 = level5.getNLSize();
    int size6 = level6.getNLSize();
    
    //level5 = sortLevelList(level5);/////////////////////TEST//////////
  /* 
    ListNode<Node<E>> lnlev2 = level2.getFirst();
    Node<E> nlev2 = lnlev2.getElement();
    sorted.addLast(nlev2);    
    for (int i = 0; i < size; i++) {
      lnlev2 = level2.getNext(lnlev2);
      nlev2 = lnlev2.getElement();
      sorted.addLast(nlev2); 
    } */

     return level5;///////////////////////////////TEMP TEST
    //return sorted;
  } // end sortDiagramList

  public NodeList<Node<E>> sortLevelList(NodeList<Node<E>> nl)
  throws EmptyListException, InvalidNodeException, BoundaryException {
    int size = nl.getNLSize();
    int min, max;
    Node<E> m, n;
    ListNode<Node<E>> lm, ln;
    NodeList<Node<E>> sortLevel = new NodeList<Node<E>>();
    
    lm = nl.getFirst();
    m = lm.getElement();    
    min = m.getPosition();    
    ln = nl.getNext(lm);
    n = ln.getElement();
    max = n.getPosition();
    if (min < max) {sortLevel.addLast(m); //sortLevel.addLast(n);
    } else {
      sortLevel.addLast(n); //sortLevel.addLast(m);
      max = min;
      n = m;
    }
    for (int i = 1; i < size/2; i++) {
      ln = nl.getNext(lm);
      lm = nl.getNext(ln);
      m = lm.getElement();    
      min = m.getPosition(); 
      if (min < max) {sortLevel.addLast(m); sortLevel.addLast(n);
      } else {
        sortLevel.addLast(n); sortLevel.addLast(m);
        max = min;
        n = m;
      }
    } 
    return sortLevel;
  }


  public void printTreeDiagram(BinaryTree<E> t) throws 
  InvalidNodeException, EmptyTreeException, EmptyListException {
    int height = this.getBTHeight();
    
    NodeList<Node<E>> list = this.getTreeList();
    ListNode<Node<E>> root = list.getFirst();
    Integer rootEl = (Integer) root.getElement().getElement(); 
    int spaces = 64;
    for (int i = 0; i < spaces; i++) {
      System.out.print(" ");
    }
    System.out.println(rootEl);
    
    // NEED TO ITERATE IN CONJUCTION WITH sortDiagramList
    //for (int i = 1; i < size; i++) {
      spaces = spaces/2;
      
    //}
  }

  
  public void iterateDiagram(Node<E> n, int pos, int offset, int spaces)
  throws InvalidNodeException {
    Node<E> l = null;
    Node<E> r = null;
    Integer lEl = null;
    Integer rEl = null;
    boolean leftFirst = false;
    if (hasLeft(n)) l = n.getLeft();
    if (hasRight(n)) r = n.getRight();
    if (l != null) {
      lEl = (Integer) l.getElement();
      for (int i = 0; i < pos - offset; i++) {
        System.out.print(" ");
      }
      System.out.print(lEl);
      leftFirst = true;
    }
    if (r != null) {
      int ofst = offset;
      if (leftFirst) ofst = 2*offset - pos; //If leftFirst then
      rEl = (Integer) r.getElement(); // i < pos + offset -(pos -offset)
      for (int i = 0; i < pos + ofst -2; i++) { // = pos + 2*offset -pos =2*offset,
        System.out.print(" "); // pos + ofst = 2*offset,
      } // ofst = 2*offset - pos
    System.out.print(rEl);
    }
    if (spaces == 0) {} else {
      for (int i = 0; i < spaces; i++) {
        System.out.print(" ");
      }
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
      leftTreeRoot.setLevel(attachmentNodeLevel);/////////////////////
      setDescendentLevels(leftTreeRoot);
      //augmentSubTreeLevels(leftTreeRoot, attachmentNodeLevel);
    }
    if (!TRight.isEmpty()) {
      Node<E> rightTreeRoot = TRight.getRoot();
      n.setRight(rightTreeRoot);
      rightTreeRoot.setParent(n);
      rightTreeRoot.setLevel(attachmentNodeLevel);/////////////////////
      setDescendentLevels(rightTreeRoot);
      //augmentSubTreeLevels(rightTreeRoot, attachmentNodeLevel);
    }       
  }

   // Print the integer elements stored in each node
  public static void printList(NodeList<Node<Integer>> nl) throws EmptyListException, 
  InvalidNodeException, BoundaryException {
    ListNode<Node<Integer>> nextNode = nl.getFirst();
    for (int i = 0; i < nl.getNLSize() -1; i++) { // Only operates for size > 1;    
      System.out.print( nextNode.getElement().getElement() + " " );
      nextNode = nl.getNext(nextNode);
    } 
    System.out.print( nextNode.getElement().getElement() + " " );
    System.out.println();
  }

  // Version of above method with Genericized Nodes
  public void printListE(NodeList<Node<E>> nl) throws EmptyListException, 
  InvalidNodeException, BoundaryException {
    ListNode<Node<E>> nextNode = nl.getFirst();
    for (int i = 0; i < nl.getNLSize() -1; i++) { // Only operates for size > 1;    
      System.out.print( nextNode.getElement().getElement() + " " );
      nextNode = nl.getNext(nextNode);
    } 
    System.out.print( nextNode.getElement().getElement() + " " );
    System.out.println();
  }


  // Print the level property stored in each node
  public static void printList2(NodeList<Integer> nl) throws EmptyListException, 
  InvalidNodeException, BoundaryException {
    ListNode<Integer> nextNode = nl.getFirst();
    //if (nl.getNLSize() == 1) { System.out.print( nextNode.getElement() + " " ); };
    for (int i = 0; i < nl.getNLSize() -1; i++) { // Only operates for size > 1;    
      System.out.print( nextNode.getElement() + " " );
      nextNode = nl.getNext(nextNode);
    } 
    System.out.print( nextNode.getElement() + " ");
    System.out.println();
  }

  // Print the level property stored in each node
  public void printList2E(NodeList<E> nl) throws EmptyListException, 
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

  /////////////////////////////////////////////////////////////////////
  // NEED TO GENERICIZE THIS METHOD
  public void printDiagramDemo(BinaryTree<E> t) throws
  InvalidNodeException, EmptyTreeException, EmptyListException {
    System.out.println("This rough diagram must be run on a full screen");
    System.out.println("and is tidiest for trees with only single digit elements");
    System.out.println("e.g. as in common usage of holding binary digits 1,0");
    System.out.println("Only shows top 6 levels"); // Consider enabling diagram
    int height = getBTHeight(); // for subtrees
    Node<E> root = getRoot();
    Node<E> left = root.getLeft();
    Node<E> right = root.getRight();

    Node<E> ll = left.getLeft();
    Node<E> lr = left.getRight();
    Node<E> rl = right.getLeft();
    Node<E> rr = right.getRight();

    Node<E> lll = ll.getLeft();
    Node<E> llr = ll.getRight();
    Node<E> lrl = lr.getLeft();
    Node<E> lrr = lr.getRight();
    Node<E> rll = rl.getLeft();
    Node<E> rlr = rl.getRight();
    Node<E> rrl = rr.getLeft();
    Node<E> rrr = rr.getRight();

    Node<E> llll = lll.getLeft();////
    Node<E> lllr = lll.getRight();
    Node<E> llrl = llr.getLeft();
    Node<E> llrr = llr.getRight();
    Node<E> lrll = lrl.getLeft();
    Node<E> lrlr = lrl.getRight();
    Node<E> lrrl = lrr.getLeft();
    Node<E> lrrr = lrr.getRight();
    Node<E> rlll = rll.getLeft();
    Node<E> rllr = rll.getRight();
    Node<E> rlrl = rlr.getLeft();
    Node<E> rlrr = rlr.getRight();
    Node<E> rrll = rrl.getLeft();
    Node<E> rrlr = rrl.getRight();
    Node<E> rrrl = rrr.getLeft();
    Node<E> rrrr = rrr.getRight();
  
    Integer el = (Integer) root.getElement();
    for (int i = 0; i < 64; i++) {
      System.out.print(" ");
    }
    System.out.println(el);

    iterateDiagram(root, 64, 32, 0);
    System.out.println();
    iterateDiagram(left, 32, 16, 14);
    iterateDiagram(right, 32, 16, 0);
    System.out.println();

    iterateDiagram(ll, 16, 8, 7);
    iterateDiagram(lr, 16, 8, 7);
    iterateDiagram(rl, 16, 8, 7);
    iterateDiagram(rr, 16, 8, 7);
    System.out.println();

    iterateDiagram(lll, 8, 4, 3);
    iterateDiagram(llr, 8, 4, 4);
    iterateDiagram(lrl, 8, 4, 3);
    iterateDiagram(lrr, 8, 4, 4);
    iterateDiagram(rll, 8, 4, 3);
    iterateDiagram(rlr, 8, 4, 4);
    iterateDiagram(rrl, 8, 4, 3);
    iterateDiagram(rrr, 8, 4, 0);
    System.out.println();

    iterateDiagram(llll, 4, 2, 1);
    iterateDiagram(lllr, 4, 2, 2);
    iterateDiagram(llrl, 4, 2, 1);
    iterateDiagram(llrr, 4, 2, 3);
    iterateDiagram(lrll, 4, 2, 1);
    iterateDiagram(lrlr, 4, 2, 2);
    iterateDiagram(lrrl, 4, 2, 1);
    iterateDiagram(lrrr, 4, 2, 3);
    iterateDiagram(rlll, 4, 2, 1);
    iterateDiagram(rllr, 4, 2, 2);
    iterateDiagram(rlrl, 4, 2, 1);
    iterateDiagram(rlrr, 4, 2, 3);
    iterateDiagram(rrll, 4, 2, 1);
    iterateDiagram(rrlr, 4, 2, 2);
    iterateDiagram(rrrl, 4, 2, 1);
    iterateDiagram(rrrr, 4, 2, 0);
    System.out.println();
  }





//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
  public void printDiagramGenericized(BinaryTree<E> t, Node<E> n) throws
  InvalidNodeException, EmptyTreeException, EmptyListException, EmptyListException,
  BoundaryException  {
    System.out.println("This diagram must be run on a full screen and is tidiest");
    System.out.println("for trees with only single digit elements, e.g. as in");
    System.out.println("common usage of holding binary digits 1, 0.");
    System.out.println("Only shows top 6 levels"); // Consider enabling diagram      
    NodeList<Node<E>> nl = t.getTreeDiagramListFromNode(n);
    System.out.println("PRINTING NODELIST GENERATED IN PDG METHOD:");
    t.printListE(nl);
    int height = t.getBTHeight();    // for subtrees    
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
      }
    }// end for    
       
    for (int proxyLev = 1; proxyLev < diagramHeight + 1; proxyLev++) {        
      switch (proxyLev) { 
        case 1:  processRoot(n); break;
        case 2:  processLevel2(level2); break;
        case 3:  processLevel3(level3); break;
        case 4:  processLevel4(level4); break;
        case 5:  processLevel5(level5); break;
        case 6:  processLevel6(level6); System.out.print("GENERICIZED"); break; 
        default: ;  break;
      } 
    }// end for 

      System.out.println();
  } // end printDiagramGenericized

  public void processRoot(Node<E> n) throws InvalidNodeException {
    E e = n.getElement();
    int pos = n.getPosition();
    for (int i = 0; i < 64; i++) {
      System.out.print(" ");
    }
    if (pos == 1) System.out.print(e);// i.e. if pos is not null
    System.out.println();
  }
  
  public void processLevel2(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int size = nl.getNLSize();
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
    for (int i = 0; i < 32; i++) {
      System.out.print(" ");
    }
    if (pos == 1) System.out.print(e);
    else {
      System.out.print(" "); // Space signifying no node
      for (int i = 0; i < 32*(pos -1); i++) { // at lev 2, pos can be 1 or 2 only
        System.out.print(" ");
      }
      System.out.print(e);
    }
   
    for (int i = 1; i < size; i++) {
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition();      
      for (int j = 0; j < 62; j++) { 
        System.out.print(" ");
      }
      if (pos == 2) System.out.print(e);       
    }// end for
    System.out.println();
  }

  public void processLevel3(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int size = nl.getNLSize();
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
    for (int i = 0; i < 16; i++) {
        System.out.print(" ");
      }
    if (pos == 1) System.out.print(e);
    else {
      System.out.print(" "); // Space signifying no node
      for (int i = 0; i < 32*(pos -1); i++) { 
        System.out.print(" ");
      }
      System.out.print(e);
    }
   
    for (int i = 1; i < size; i++) {
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition();      
      for (int j = 0; j < 30; j++) { 
        System.out.print(" ");
      }
      if (pos == i + 1) System.out.print(e); 
      else {
        System.out.print(" "); // Space signifying no node
        for (int k = 0; k < 30*(pos -1); k++) { 
          System.out.print(" ");
        }
        System.out.print(e);
      }// end else  
    }// end for
    System.out.println();
  }

  public void processLevel4(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int size = nl.getNLSize();
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
    for (int i = 0; i < 8; i++) {
      System.out.print(" ");
    }
    if (pos == 1) System.out.print(e);
    else {
      System.out.print(" "); // Space signifying no node
      for (int i = 0; i < 14*(pos -1); i++) { 
        System.out.print(" ");
      }
      System.out.print(e);
    }
   
    for (int i = 1; i < size; i++) {
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition();  
      if (i%2 == 0) System.out.print(" "); // Extra space for even intervals    
      for (int j = 0; j < 14; j++) { 
        System.out.print(" ");
      }
      if (pos == i + 1) System.out.print(e); 
      else {
        System.out.print(" "); // Space signifying no node
        for (int k = 0; k < 14*(pos -1); k++) { 
          System.out.print(" ");
        }
        System.out.print(e);
      }// end else  
    }// end for
    System.out.println(); 
  }

  public void processLevel5(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int size = nl.getNLSize();
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
    for (int i = 0; i < 4; i++) {
      System.out.print(" ");
    }
    if (pos == 1) System.out.print(e);
    else {
      System.out.print(" "); // Space signifying no node
      for (int i = 0; i < 6*(pos -1); i++) { 
        System.out.print(" ");
      }
      System.out.print(e);
    }
   
    for (int i = 1; i < size; i++) {
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition();  
      if (i%2 == 0) System.out.print(" "); // Extra space for even intervals
      if (i%4 == 0) System.out.print(" ");  
      for (int j = 0; j < 6; j++) { 
        System.out.print(" ");
      }
      if (pos == i + 1) System.out.print(e); 
      else {
        System.out.print(" "); // Space signifying no node
        for (int k = 0; k < 6*(pos -1); k++) { 
          System.out.print(" ");
        }
        System.out.print(e);
      }// end else  
    }// end for
    System.out.println();
  }

  public void processLevel6(NodeList<Node<E>> nl) throws InvalidNodeException, 
  EmptyListException, BoundaryException {
    int size = nl.getNLSize();
    ListNode<Node<E>> ln = nl.getFirst();
    Node<E> node = ln.getElement();
    E e = node.getElement();
    int pos = node.getPosition();
    for (int i = 0; i < 2; i++) {
      System.out.print(" ");
    }
    if (pos == 1) System.out.print(e);
    else {
      System.out.print(" "); // Space signifying no node
      for (int i = 0; i < 2*(pos -1); i++) { 
        System.out.print(" ");
      }
      System.out.print(e);
    }
   
    for (int i = 1; i < size; i++) {
      ln = nl.getNext(ln);
      node = ln.getElement();
      e = node.getElement();
      pos = node.getPosition();  
      if (i%2 == 0) System.out.print(" "); // Extra space for even intervals
      if (i%4 == 0) System.out.print(" "); 
      if (i%8 == 0) System.out.print(" "); 
      for (int j = 0; j < 2; j++) { 
        System.out.print(" ");
      }
      if (pos == i + 1) System.out.print(e); 
      else {
        System.out.print(" "); // Space signifying no node
        for (int k = 0; k < 2*(pos -1); k++) { 
          System.out.print(" ");
        }
        System.out.print(e);
      }// end else  
    }// end for   

    System.out.println();
  }


  public void printDiagramDemo2(BinaryTree<E> t) throws
  InvalidNodeException, EmptyTreeException, EmptyListException {
    System.out.println("This rough diagram must be run on a full screen");
    System.out.println("and is tidiest for trees with only single digit elements");
    System.out.println("e.g. as in common usage of holding binary digits 1,0");
    System.out.println("Only shows top 6 levels"); // Consider enabling diagram
    int height = getBTHeight(); // for subtrees
    Node<E> root = getRoot();
    Node<E> left = root.getLeft();
    Node<E> right = root.getRight();

    Node<E> ll = left.getLeft();
    Node<E> lr = left.getRight();
    Node<E> rl = right.getLeft();
    Node<E> rr = right.getRight();

    Integer el = (Integer) root.getElement();
    for (int i = 0; i < 64; i++) {
      System.out.print(" ");
    }
    System.out.println(el);

    iterateDiagram(root, 64, 32, 0);
    System.out.println();
    iterateDiagram(left, 32, 16, 14);
    iterateDiagram(right, 32, 16, 0);
    System.out.println();

    iterateDiagram(ll, 16, 8, 7);
    iterateDiagram(lr, 16, 8, 7);////////////
    iterateDiagram(rl, 16, 8, 7);
    iterateDiagram(rr, 16, 8, 7);
    System.out.println();
  }


  public static void printDiagramNodeList(NodeList<Node<Integer>> nl) 
  throws EmptyListException, InvalidNodeException, BoundaryException {
    ListNode<Node<Integer>> nextListNode = nl.getFirst();
    Node<Integer> nextNode = nextListNode.getElement();
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


  // MAIN METHOD- TEST PURPOSES ////////////////////////////////////////
  public static void main(String[] args) 
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
    bt2.setLeft(root4, 7);
    bt2.setRight(root4, 8);
    Node<Integer> root5 = bt5.setRoot(9);
    bt3.setLeft(root5, 0);
    bt3.setRight(root5, 1);
    Node<Integer> root6 = bt6.setRoot(2);
    bt2.setLeft(root6, 3);
    bt2.setRight(root6, 4);
    Node<Integer> root7 = bt7.setRoot(5);
    bt3.setLeft(root7, 6);
    bt3.setRight(root7, 7);
    Node<Integer> root8 = bt8.setRoot(8);
    bt2.setLeft(root8, 9);
    bt2.setRight(root8, 0);
    Node<Integer> root9 = bt9.setRoot(1);
    bt3.setLeft(root9, 2);
    bt3.setRight(root9, 3);
  
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
    bt70.attachTrees(lrCh, bt4, bt5); // Attach trees
    bt70.attachTrees(rlCh, bt6, bt7); // Attach trees
    bt70.attachTrees(rrCh, bt8, bt9); // Attach trees
 
    Node<Integer> lll = llCh.getLeft();
    Node<Integer> llr = llCh.getRight();
    Node<Integer> lrl = lrCh.getLeft();///
    Node<Integer> lrr = lrCh.getRight();
    Node<Integer> rll = rlCh.getLeft();
    Node<Integer> rlr = rlCh.getRight();
    Node<Integer> rrl = rrCh.getLeft();
    Node<Integer> rrr = rrCh.getRight();
   
    Node<Integer> llll = lll.getLeft();
    Node<Integer> lllr = lll.getRight();
    Node<Integer> llrl = llr.getLeft();
    Node<Integer> llrr = llr.getRight();
    Node<Integer> lrll = lrl.getLeft();///
    Node<Integer> lrlr = lrl.getRight();///
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
    bt70.attachTrees(lllr, bt82, bt83); // attach trees
    bt70.attachTrees(llrl, bt84, bt85); // attach trees
    bt70.attachTrees(llrr, bt86, bt87); // attach trees
    bt70.attachTrees(lrll, bt88, bt89); // attach trees//
    bt70.attachTrees(lrlr, bt90, bt91); // attach trees
    bt70.attachTrees(lrrl, bt92, bt93); // attach trees
    bt70.attachTrees(lrrr, bt94, bt95); // attach trees
    bt70.attachTrees(rlll, bt96, bt97); // attach trees
    bt70.attachTrees(rllr, bt98, bt99); // attach trees
    bt70.attachTrees(rlrl, bt100, bt101); // attach trees
    bt70.attachTrees(rlrr, bt102, bt103); // attach trees
    bt70.attachTrees(rrll, bt104, bt105); // attach trees
    bt70.attachTrees(rrlr, bt106, bt107); // attach trees
    bt70.attachTrees(rrrl, bt108, bt109); // attach trees
    bt70.attachTrees(rrrr, bt110, bt111); // attach trees
    System.out.println("MARKER 1");
    //bt70.printDiagramDemo(bt70);
    //System.out.println();
   
    bt70.printDiagramGenericized(bt70, bt70.getRoot());
   
    Node<Integer> lllll = llll.getRight();
    Integer l5El = lllll.getLevel();
    System.out.println("lllll level: " + l5El); 

    int h70 =  bt70.getBTHeight();
    System.out.println("BT70 Height: " + h70);
      
    NodeList<Integer> levelsList70 = new NodeList<Integer>();
    bt70.getSubtreeLevelsList(bt70.getRoot(), levelsList70);
    System.out.print("BT70 Levels Augmented: ");
    printList2(levelsList70);
    System.out.println("MARKER REPEAT WITH NODE E: ");
    System.out.print("BT70 Levels Augmented: ");
    bt70.printList2E(levelsList70);
    
    NodeList<Node<Integer>> nListA = bt70.getTreeList(); // Get list
    System.out.print("BT70 list: ");
    printList(nListA); // Print list

    /*NodeList<Node<Integer>> dn = bt70.getDiagramList();
    int sz = dn.getNLSize();
    System.out.println("DN Size: " + sz); //Should be 63 for full 6 levels
    printDiagramNodeList(dn);
    */
    ///////////////////////////////////////////////////////////////////////
    NodeList<Node<Integer>> nListB = bt70.getTreeDiagramList(); // Get list
    System.out.print("BT70 list using Diagram method: ");
    printList(nListB); // Print list
    printDiagramNodeList(nListB);
    System.out.println("MARKER 2");
    bt70.printTreeDiagram(bt70);

    NodeList<Node<Integer>> level5 = bt70.sortDiagramList(nListB);
    printDiagramNodeList(level5);  
     
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
    bt700.printDiagramDemo2(bt700);
  
    int h700 =  bt700.getBTHeight();
    System.out.println("BT700 Height: " + h700);

    System.out.println("MARKER 21 MARKER 21 MARKER 21 MARKER 21 MARKER 21 ");
    bt700.printDiagramGenericized(bt700, bt700.getRoot());
    bt700.printDiagramGenericized(bt700, bt700.getRoot().getLeft());
    bt700.printDiagramGenericized(bt700, bt700.getRoot().getLeft().getRight());
  
    bt700.removeNode(lrCh7);
    nListB = bt700.getTreeDiagramList();
    level5 = bt700.sortDiagramList(nListB); 

    System.out.println("MARKER 3");  
    bt700.printDiagramDemo2(bt700);
    printDiagramNodeList(level5);
   
  }// End main



  static class Node<E> {
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
  }// End internal class Node<E>
  

  static class ListNode<E> {
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
  }// End internal class ListNode<E>


  static class NodeList<E> {
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

  }// End internal class NodeList<E>

}// End class BinaryTree


// Must keep Exception classes external: a generic class cannot 
// extend java.lang.Throwable

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
