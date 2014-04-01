package algorithm.lc;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return 1 since the palindrome partitioning
 * ["aa","b"] could be produced using 1 cut.
 * 
 */
public class PalindromePartitionII {

  // DP, res[i] records the min number of partitions between i and n
  // also the palindrome isPalindrome is used to keep track that whether s[i..j] is palindrome
  public class Solution {  
    public int minCut(String s) {  
      // Note: The Solution object is instantiated only once and is reused by each test case. 
      int n = s.length();  
      int[] res = new int[n + 1]; // keep min number of partitions from in range s[i..n]
      for(int i = 0 ; i < res.length; ++i) {  
        res[i] = n - i;  // initial as maximal possible partitions
      }  
      boolean[][] isPalindrome = new boolean[n][n]; // use 2D DP to record palindrome
      for(int i = n - 1; i >= 0; --i) {  
        for(int j = i; j < n ; ++j){  
          if(s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalindrome[i + 1][j - 1])){  // s[i...j] is palindrome
            isPalindrome[i][j] = true;  
            // if s[i...j] is palindrome, then maximal partition of s[i...n] is at most 1 + maximal partition of s[j + 1...n]
            res[i] = Math.min(res[i], res[j + 1] + 1);  
          }  
        }  
      }  
      return res[0] - 1;  
    }  
    
  };  
  
  /**
   * O(2^n) time. 
   *
   */
  public class SolutionDFS {
    public int minCut(String s) {
          // IMPORTANT: Please reset any member data you declared, as
          // the same Solution instance will be reused for each test case.
      int[] res = new int[1];
      res[0] = Integer.MAX_VALUE;
      
      int start = 0;
      int curCut = -1;
      cut(start, s, curCut, res);
      return res[0];
    }
    
    private void cut(int start, String s, int curCut, int[] res) {
      if (start == s.length()) {
        res[0] = Math.min(res[0], curCut);
        return;
      }
      
      for (int end = start; end < s.length(); ++end) {
        if (isPalindrome(s, start, end)) {
          cut(end + 1, s, curCut + 1, res);
        }
      }
    }
    
    private boolean isPalindrome(String s, int start, int end) {
      while (start < end) {
        if (s.charAt(start) != s.charAt(end)) {
          return false;
        }
        ++start;
        --end;
      }
      return true;
    }
  }
  
}
