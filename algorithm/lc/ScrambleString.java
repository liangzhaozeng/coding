package algorithm.lc;

import java.util.Arrays;

public class ScrambleString {
  
  public class Solution {
    public boolean isScramble(String s1, String s2) {
          // IMPORTANT: Please reset any member data you declared, as
          // the same Solution instance will be reused for each test case.
      
      // some cheap checks
      if (s1.length() != s2.length()) {
        return false;
      }
      
      if (s1.equals(s2)) {
        return true;
      }
      
      char[] arrFirst = s1.toCharArray();
      char[] arrSecond = s2.toCharArray();
      Arrays.sort(arrFirst);
      Arrays.sort(arrSecond);
      if (!Arrays.equals(arrFirst, arrSecond)) {
        return false;
      }
      
      int i = 1;
      while (i < s1.length()) {
        String firstLeft = s1.substring(0, i);
        String secondLeft = s2.substring(0, i);
        String firstRight = s1.substring(i);
        String secondRight = s2.substring(i);
        
        if (firstLeft.equals(secondLeft) && firstRight.equals(secondRight)) {
          return true;
        }
        
        boolean canScramble = isScramble(firstLeft, secondLeft) && isScramble(firstRight, secondRight);
        if (!canScramble) { // swap and try again
          secondLeft = s2.substring(0, s2.length() - i);
          secondRight = s2.substring(s2.length() - i);
          canScramble = isScramble(firstLeft, secondRight) && isScramble(firstRight, secondLeft);
        }
        
        if (canScramble) {
          return true;
        }
        ++i;
      }
      
      return false;
    }
      
  }

}
