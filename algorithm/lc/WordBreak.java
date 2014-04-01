package algorithm.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 */
public class WordBreak {

  // DFS, recursively divide a string into two parts to check.
  // 
  public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
          // IMPORTANT: Please reset any member data you declared, as
          // the same Solution instance will be reused for each test case.
      int start = 0, end = s.length();
      Map<String, Boolean> cache = new HashMap<String, Boolean>();
      return find(s, start, end, dict, cache);
    }
    
    private boolean find(String s, int start, int end, Set<String> dict, Map<String, Boolean> cache) {
      if (start >= end) {
        return true;
      }
      // check cache
      String curWord = s.substring(start, end);
      Boolean res = cache.get(curWord);
      if (res != null) {
        return res;
      }
      
      if (dict.contains(curWord)) { // check dict
        cache.put(curWord, true);
        return true;
      }
      else { // calculate from scratch
        for (int pos = start + 1; pos < end; ++pos) {
          if (find(s, start, pos, dict, cache) && find(s, pos, end, dict, cache)) {
            cache.put(curWord, true);
            return true;
          }
        }
        cache.put(curWord, false);
        return false;
      }
    }
  }

}
