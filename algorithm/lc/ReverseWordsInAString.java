package algorithm.lc;

/**
 * Given an input string, reverse the string word by word. 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 * 
 */
public class ReverseWordsInAString {

  // O(n) time, O(n) space
  public class Solution {
    
    public String reverseWords(String s) {
      StringBuilder sb = new StringBuilder();
      String[] tokens = s.split(" ");
      for (int i = tokens.length - 1; i >= 0; --i) {
        if (tokens[i] != null && tokens[i].length() > 0) {
          sb.append(tokens[i]);
          sb.append(" ");
        }
      }

      if (sb.length() == 0) {
        return "";
      }

      return sb.substring(0, sb.length() - 1);
    }
  }

}
