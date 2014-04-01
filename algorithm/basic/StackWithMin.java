package algorithm.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a stack that supports getMin with O(1). 
 *
 */
public class StackWithMin {

  private List<Integer> collection;
  private List<Integer> mins;
  
  public StackWithMin() {
    collection = new ArrayList<Integer>();
    mins = new ArrayList<Integer>();
  }
  
  public void push(int val) {
    collection.add(val);
    if (mins.size() == 0 || val <= mins.get(mins.size() - 1)) {
      mins.add(val);
    }
  }
  
  public int pop() {
    if (collection.size() == 0) {
      return -1;
    }
    
    int top = collection.get(collection.size() - 1);
    collection.remove(collection.size() - 1);
    if (top == mins.get(mins.size() - 1)) {
      mins.remove(mins.size() - 1);
    }
    return top;
  }
  
  public int getMin() {
    if (collection.size() == 0) {
      return -1;
    }
    return mins.get(mins.size() - 1);
  }
  
}
