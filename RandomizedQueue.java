import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
  private Item[] arr;
  private int size;
  
  public RandomizedQueue() {
    this.size = 0;
    this.arr = (Item[]) new Object[2];
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  private void resize(int n) {
    Item[] newArr = (Item[]) new Object[n];
    int min = Math.min(arr.length, n);
    for (int i = 0; i < min; ++i) {
      newArr[i] = arr[i];
    }
    arr = newArr;
  }
  
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException();
    if (arr.length == size) {
      resize(2*size);
    }
    arr[size++] = item;
  }
  
  public Item dequeue() {
    if (size == 0) throw new NoSuchElementException();
    int idx = StdRandom.uniform(size);
    Item x = arr[idx];
    if (idx != size - 1) {
      arr[idx] = arr[size - 1];
    }
    arr[--size] = null;
    if (size > 0 && size == arr.length/4) {
      resize(arr.length/2);
    }
    return x;
  }
  
  public Item sample() {
    if (size == 0) throw new NoSuchElementException();
    return arr[StdRandom.uniform(size)];
  }
  
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  
  private class ListIterator implements Iterator<Item> {
    
    private final Item[] shuffledArr;
    private int ptr;
    
    public ListIterator() {
      this.shuffledArr = (Item[]) new Object[arr.length];
      for (int i = 0; i < arr.length; ++i) {
        shuffledArr[i] = arr[i];
      }
      this.ptr = 0;
      StdRandom.shuffle(this.shuffledArr, 0, size);
    }
    
    public boolean hasNext() {
      return ptr < size;
    }
    
    public Item next() {
      if (ptr >= size) throw new NoSuchElementException();
      return shuffledArr[ptr++];
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  public static void main(String[] args) {
    RandomizedQueue<Integer> x = new RandomizedQueue<Integer>();
    x.enqueue(418);
    x.enqueue(86);
    x.enqueue(142);
    x.dequeue();
    x.dequeue();
    
    for (int a: x) {
      System.out.println(a);
    }
  }
  

}
