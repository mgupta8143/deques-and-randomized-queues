import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
  private class Node {
    private Item val;
    private Node next;
    private Node before;
  }
  
  private Node first;
  private Node last;
  private int size;
  
  public Deque() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }
  
  public boolean isEmpty() {
    return this.size == 0;
  }
  
  public int size() {
    return this.size;
  }
  
  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException();
    Node x = new Node();
    x.val = item;
    if (size == 0) {
      first = x;
      last = x; 
    } else {
      x.next = first;
      first.before = x;
      first = x;
    }
    ++size;
  }
  
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException();
    Node x = new Node();
    x.val = item;
    if (size == 0) {
      first = x;
      last = x;
    } else {
      last.next = x;
      x.before = last;
      last = x;
    }
    ++size;
  }
  
  public Item removeFirst() {
    if (size == 0) throw new NoSuchElementException();
    Node oldfirst = first;
    if (size == 1) {
      --size;
      first = null;
      last = null;
      return oldfirst.val;
    }
    --size;
    first = first.next;
    first.before = null;
    return oldfirst.val;
  }
  
  public Item removeLast() {
    if (size == 0) throw new NoSuchElementException();
    Node oldlast = last;
    if (size == 1) {
      --size;
      first = null;
      last = null;
      return oldlast.val;
    }
    --size;
    last = last.before;
    last.next = null;
    return oldlast.val;
  }
 
  
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  
  private class ListIterator implements Iterator<Item> {
    
    private Node ptr = first;
    
    public boolean hasNext() {
      return ptr != null;
    }
    
    public Item next() {
      if (ptr == null) throw new NoSuchElementException();
      Item x = ptr.val;
      ptr = ptr.next;
      return x;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  
  public static void main(String[] args) {
    System.out.println("Yello");
  }
 
  
  
  
  
  
  
}
