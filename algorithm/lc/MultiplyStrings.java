package algorithm.lc;

import java.util.Arrays;

/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 */
// O(m+n) space, O(n * n) time
public class MultiplyStrings {

  public class Solution {
    public String multiply(String num1, String num2) {
          // IMPORTANT: Please reset any member data you declared, as
          // the same Solution instance will be reused for each test case.
     int[] res = new int[num1.length() + num2.length()];
     
     for (int i = num2.length() - 1; i >= 0; --i) {
       int carry = 0;
       for (int j = num1.length() - 1; j >= 0; --j) {
         res[i + j + 1] += (num2.charAt(i) - '0') * (num1.charAt(j) - '0') + carry;
         carry = res[i + j + 1] / 10;
         res[i + j + 1] %= 10;
       }
       res[i] = carry; // put the last carry
     }
     
     int i = 0;
     while (i < res.length - 1 && res[i] == 0) { 
       ++i;  // remove the leading 0's but kept the last 0
     }
     
     StringBuilder sb = new StringBuilder();
     for (int j = i; j < res.length; ++j) {
       sb.append(res[j]);
     }
     
     return sb.toString();
   }
  }
  
}
