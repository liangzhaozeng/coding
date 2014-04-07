package algorithm.lc;

import java.util.Arrays;

public class ScrambleString {
  
  public class Solution {
 	public boolean isScramble(String s1, String s2) {
    int n=s1.length();
        boolean[][][] dp=new boolean[n][n][n+1];
        
        for(int i=n-1; i>=0; i--)
            for(int j=n-1; j>=0; j--)
                for(int k=1; k<=n-Math.max(i,j);k++)
                {
                    if(s1.substring(i,i+k).equals(s2.substring(j,j+k)))
                        dp[i][j][k]=true;
                    else
                    {
                        for(int l=1; l<k; l++)
                        {
                            if ((dp[i][j][l] && dp[i+l][j+l][k-l]) || (dp[i][j+k-l][l] && dp[i+l][j][k-l]))
                            {
                                dp[i][j][k]=true;
                                break;
                            }
                        }
                    }
                }
        
        return dp[0][0][n];
    }


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
