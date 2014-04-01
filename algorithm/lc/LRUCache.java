package algorithm.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It
 * should support the following operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. set(key, value) - Set or insert the
 * value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new
 * item.
 * 
 */
public class LRUCache {

  class Node {
    int key;
    int value;
    Node prev;
    Node next;
    
    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
  
  class DoubleLinkedList {
    Node head;
    Node tail;
    
    public DoubleLinkedList() {
      head = null;
      tail = null;
    }
    
    /**
     *  Insert a new key-value pair to the list.
     */
    public void pushBack(int key, int value) {
      Node node = new Node(key, value);
      pushBack(node);
    }
    
    /**
     *  Remove a node from the list.
     */
    public Node delete(Node node) {
      if (node == null) {
        return null;
      }
      
      if (node.prev == null) {
        head = node.next;
      }
      else {
        node.prev.next = node.next;
      }
      
      if (node.next == null) {
        tail = node.prev;
      }
      else {
        node.next.prev = node.prev;
      }
      
      node.next = null;
      node.prev = null;
      
      return null;
    }
    
    /**
     *  Append a node to the end of list.
     */
    public Node pushBack(Node node) {
      if (head == null) {
        head = node;
        tail = node;
      }
      else {
        tail.next = node;
        node.prev = tail;
        tail = node;
      }
      
      return node;
    }
    
    /**
     *  Remove a node from the head.
     */
    public Node popFront() {
      if (head == null) {
        return null;
      }
      
      Node ret = head;
      head = head.next;
      ret.next = null;
      
      if (head != null) {
        head.prev = null;
      }
      
      return ret;
    }
    
  }
  
  private int capacity;
  private int size;

  private Map<Integer, Node> cache = new HashMap<Integer, Node>();
  private DoubleLinkedList list = new DoubleLinkedList();
    
  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.size = 0;
  }
   
  public int get(int key) {
    // check whether the key exists from cache
    Node node = cache.get(key);
    if (node == null) {
      return -1;
    }
    else {
      // update list
      list.delete(node);
      list.pushBack(node);
      return node.value;
    }
  }
    
  public void set(int key, int value) {
    // check whether the key exists
    Node node = cache.get(key);
    if (node != null) {  // reorder the nodes and update the value
      list.delete(node);
      list.pushBack(node);
      node.value = value;
    }
    else {
      Node newNode = new Node(key, value);
      
      // remove the oldest one
      if (this.size == this.capacity) {
        // remove oldest from the storage
        Node oldestNode = list.popFront();
        cache.remove(oldestNode.key);
        --this.size;
      }
      
      // add new node to storage
      cache.put(key, newNode);
      list.pushBack(newNode);
      ++this.size;
    }
  }
  
}
