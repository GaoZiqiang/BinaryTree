import java.util.*;

public class BinaryTree<E> {
  
  protected int btSize;
  protected Node<E> root; 
  
  public BinaryTree() {
    btSize = 0;
    root = null;
  }
  
  public int getBTSize() {return btSize;}
  
  public Node<E> getRoot() throws EmptyTreeException {
    if (root == null) throw new EmptyTreeException("Empty tree");
    return root;
  }
  
  public Node<E> getParent(Node<E> n) throws BoundaryException {
    Node<E> p = n.getparent();
    if (p == null) throw new BoundaryException("No parent");
    return p;
  } 
 
  public Node<E> getLeft(Node<E> n) throws BoundaryException {
    Node<E> l = n.getLeft();
    if (l == null) throw new BoundaryException("No left child");
    return l;
  }

  public Node<E> getRight(Node<E> n) throws BoundaryException {
    Node<E> r = n.getRight();
    if (r == null) throw new BoundaryException("No right child");
    return r;
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
  //xxxxxxxxxxxxxxxxxxxxxxxxxxxxx
  public void getSubtreeList(Node<E> n, NodeList<Node<E>> l) 
  throws InvalidNodeException, EmptyTreeException {
    l.addLast(n);
    if (hasLeft(n)) getSubtreeList(n.getLeft(), l); //use Node getLeft
    if (hasRight(n)) getSubtreeList(n.getRight(), l);// or BT getLeft?
  }
 
  public Node<E> getParent(Node<E> n) {
    Node<E> p = n.getParent();
    return p;
  }
  
  public E replaceElement(Node<E> n, E e) throws InvalidNodeException {
    E oldElement = n.getElement();
    n.setElement(e);
    return oldElement;
  }

  public static void main(String[] args) {
         
  }

  class Node<E> {
    private E element;
    private Node<E> parent, left, right;   
    public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
      setElement(e);
      setParent(p);
      setLeft(l);    
      setRight(r); 
    }
    public E getElement() throws InvalidNodeException {
      if ((parent == null) && (left == null) && (right == null)) 
        throw new InvalidNodeException("Invalid node");
      return element;
    }
    public Node<E> getParent() {return parent;}
    public Node<E> getLeft() {return left;}
    public Node<E> getRight() {return right;}
    public void setParent(Node<E> p) {parent = p;}
    public void setLeft(Node<E> l) {left = l;}
    public void setRight(Node<E> r) {right = r;}
    public void setElement(E e) {element = e;}
  }// End internal class Node<E>
  
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
  }

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
