package algorithm.lc;

/**
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors. What is the minimum candies you must give?
 * 
 */
public class Candy {
  
  // O(n) space, O(n) time
  // one scan from left to right, get the relative height compare with left element,
  // another scan from right to left, get the relative height compare with right element
  public class Solution {
    public int candy(int[] ratings) {
          // Note: The Solution object is instantiated only once and is reused
      // scan from left to right, find the lower bound from the left
      int[] candyArr = new int[ratings.length];
      candyArr[0] = 1;
      int candy = 1;
      for (int i = 1; i < ratings.length; ++i) {
        if (ratings[i] > ratings[i - 1]) {
          ++candy;
        }
        else {
          candy = 1;
        }
        candyArr[i] = candy;
      }
      
      int total = 0;
      total += candyArr[candyArr.length - 1];
      // scan from right to left, find the lower bound from the right
      for (int i = ratings.length - 2; i >= 0; --i) {
        if (ratings[i] > ratings[i + 1]) {
          candyArr[i] = Math.max(candyArr[i + 1] + 1, candyArr[i]);
        }
        total += candyArr[i];
      }
      
      return total;
    }
  }

}
