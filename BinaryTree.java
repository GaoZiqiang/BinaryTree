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
    if (hasLeft(n)) childList.addLast(n.getLeft().getElement());
    if (hasRight(n)) childList.addLast(n.getRight().getElement());
    return childList;
  }
  
  public NodeList<Node<E>> getTreeList() 
  throws InvalidNodeException, EmptyTreeException {
    NodeList<Node<E>> treeList = new NodeList<Node<E>>(); 
    if (btSize != 0) getSubtreeList(getRoot(), treeList);
    return treeList;
  }
  
  public void getSubtreeList(Node<E> n, NodeList<Node<E>> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n);
    if (hasLeft(n)) getSubtreeList(n.getLeft(), l); //use Node getLeft
    if (hasRight(n)) getSubtreeList(n.getRight(), l);// or BT getLeft?
  }
  /////////////////////////////////////////////////////////////////////////
  public void getSubtreeLevelsList(Node<E> n, NodeList<Integer> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n.getLevel());
    if (hasLeft(n)) getSubtreeLevelsList(n.getLeft(), l); 
    if (hasRight(n)) getSubtreeLevelsList(n.getRight(), l);
  }
  //////////////////////////////////////////////////////////////////////////
  // Augment level property in easch node of a tree by same amount
  public void augmentSubTreeLevels(Node<E> root, int aug) 
  throws InvalidNodeException {
    //Node<E> n = t.getRoot();
    root.level = root.getLevel() + aug;
    if (hasLeft(root)) augmentSubTreeLevels(root.getLeft(), aug); // Recursive
    if (hasRight(root)) augmentSubTreeLevels(root.getRight(), aug); // Recursive
  }  
  ////////////////////////////////////////////////////////////////////////
  public void printTreeDiagram(BinaryTree<E> t) {
    
  }
 
  public Node<E> setRoot(E e) 
  throws TreeNotEmptyException {
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
  
  // Attach two trees as subtrees of an external node
  // i.e. the roots of these trees become children of the node
  // NEED TO CONSIDER HEIGHT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public void attachTrees(Node<E> n, BinaryTree<E> TLeft, BinaryTree<E> TRight) 
  throws InvalidNodeException, EmptyTreeException {
    if (isInternal(n)) throw new InvalidNodeException("Node is not external");
    btSize = btSize + TLeft.getBTSize() + TRight.getBTSize();
    TLeft.augmentSubTreeLevels(TLeft.getRoot(), this.getBTHeight());
    TRight.augmentSubTreeLevels(TRight.getRoot(), this.getBTHeight());
    if (!TLeft.isEmpty()) {
      Node<E> leftTreeRoot = TLeft.getRoot();
      n.setLeft(leftTreeRoot);
      leftTreeRoot.setParent(n);
    }
    if (!TRight.isEmpty()) {
      Node<E> rightTreeRoot = TRight.getRoot();
      n.setRight(rightTreeRoot);
      rightTreeRoot.setParent(n);
    }   
  }
 
  // Print the integer elements stored in each node
  public static void printList(NodeList<Node<Integer>> nl) throws EmptyListException, 
  InvalidNodeException, BoundaryException {
    ListNode<Node<Integer>> nextNode = nl.getFirst();
    if (nl.getNLSize() == 1) { System.out.print( nextNode.getElement().getElement() + " " ); };
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
    if (nl.getNLSize() == 1) { System.out.print( nextNode.getElement() + " " ); };
    for (int i = 0; i < nl.getNLSize() -1; i++) { // Only operates for size > 1;    
      System.out.print( nextNode.getElement() + " " );
      nextNode = nl.getNext(nextNode);
    } 
    System.out.print( nextNode.getElement() + " " );
    System.out.println();
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
  }

  // MAIN METHOD- TEST PURPOSES ////////////////////////////////////////
  public static void main(String[] args) 
  throws EmptyTreeException, TreeNotEmptyException,
  InvalidNodeException, BoundaryException, EmptyListException {
    
    testRoutine1();   
    
    System.out.println();
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    bt.setRoot(0);
    Node<Integer> root = bt.getRoot();
    Integer rootEl = root.getElement();
    System.out.println("Root: " + rootEl);
    
    Node<Integer> leftCh = bt.setLeft(root, 1);
    Node<Integer> rightCh = bt.setRight(root, 2);
    System.out.println("Size: " + bt.getBTSize());

    BinaryTree<Integer> bt2 = new BinaryTree<Integer>();
    BinaryTree<Integer> bt3 = new BinaryTree<Integer>();
    Node<Integer> root2 = bt2.setRoot(10);
    bt2.setLeft(root2, 11);
    bt2.setRight(root2, 12);
    Node<Integer> root3 = bt3.setRoot(20);
    bt3.setLeft(root3, 21);
    bt3.setRight(root3, 22);
    bt.attachTrees(rightCh, bt2, bt3); // Attach trees
    System.out.println("BT Size: " + bt.getBTSize());

    NodeList<Node<Integer>> nList = bt.getTreeList(); // Get list
    System.out.println("List size: " + nList.getNLSize());
    
    System.out.print("BT list: ");
    printList(nList); // Print list
        
    Node<Integer> left1 = bt.getLeft(root);
    Node<Integer> right1 = bt.getRight(root);
    Node<Integer> rightLeft2 = bt.getLeft(right1);
    Integer left1int = left1.getElement();
    Integer rightLeft2int = rightLeft2.getElement();    
    System.out.println("left1: " + left1int);
    System.out.println("rightLeft2: " + rightLeft2int);
    

    NodeList<Integer> levelsList2 = new NodeList<Integer>();
    bt.getSubtreeLevelsList(bt.getRoot(), levelsList2);
    System.out.println("BT Size: " + bt.getBTSize());
    System.out.print("BT Levels: ");
    printList2(levelsList2);
    NodeList<Node<Integer>> nListA = bt.getTreeList(); // Get list
    System.out.print("BT list: ");
    printList(nListA); // Print list

    bt.augmentSubTreeLevels(bt.getRoot(), 5); 
    NodeList<Integer> levelsList3 = new NodeList<Integer>();
    bt.getSubtreeLevelsList(bt.getRoot(), levelsList3);
    System.out.print("BT Levels Augmented: ");
    printList2(levelsList3);


  }// End main


  static class Node<E> {
    private E element;
    private int level;
    private Node<E> parent, left, right;   
    public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
      setElement(e);
      setParent(p);
      setLeft(l);    
      setRight(r); 
    }
    public E getElement() {return element;} //throws InvalidNodeException {
      //if ((parent == null) && (left == null) && (right == null)) 
        //throw new InvalidNodeException("Invalid node");      
    public Node<E> getParent() {return parent;}
    public Node<E> getLeft() {return left;}
    public Node<E> getRight() {return right;}
    public int getLevel(){return level;}
    public void setParent(Node<E> p) {parent = p;}
    public void setLeft(Node<E> l) {left = l;}
    public void setRight(Node<E> r) {right = r;}
    public void setElement(E e) {element = e;}
    public void setLevel(int i) {level = i;}
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

/*interface NodeListInterface<E> extends Iterable<E> {
  public Iterator<E> iterator();
}*/


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
