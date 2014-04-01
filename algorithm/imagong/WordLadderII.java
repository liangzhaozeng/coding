package algorithm.imagong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) fromstart to end, such that:
 * 
 * Only one letter can be changed at a time
 * 
 * Each intermediate word must exist in the dictionary
 * 
 * For example,
 * 
 * Given:
 * start = "hit"
 * end = "cog"
 * 
 * dict = ["hot","dot","dog","lot","log"]
 * 
 * Return:
 * 
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * 
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 */
public class WordLadderII {

  public static class Solution {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
      // Start typing your Java solution below
      // DO NOT write main() function
      ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
      // record all paths, key: the end of the path; value: the path
      HashMap<String, ArrayList<ArrayList<String>>> records = new HashMap<String, ArrayList<ArrayList<String>>>();
      ArrayList<String> iniList = new ArrayList<String>();
      iniList.add(start);
      ArrayList<ArrayList<String>> iniLists = new ArrayList<ArrayList<String>>();
      iniLists.add(iniList);
      records.put(start, iniLists);
      HashMap<String, ArrayList<ArrayList<String>>> newRecords = new HashMap<String, ArrayList<ArrayList<String>>>();
      dict.add(end);

      while (records.size() > 0 && dict.size() > 0) {
        HashSet<String> toBeRemoved = new HashSet<String>();
        for (String cur : records.keySet()) {
          HashSet<String> nextLadder = nextLadderWord(cur, dict);
          ArrayList<ArrayList<String>> curLists = records.get(cur);
          updateIndex(newRecords, nextLadder, curLists);
          toBeRemoved.addAll(nextLadder);
        }
        dict.removeAll(toBeRemoved);
        records = newRecords;
        newRecords = new HashMap<String, ArrayList<ArrayList<String>>>();
        if (records.containsKey(end)) {
          return records.get(end);
        }
      }
      
      return results;
    }

    public void updateIndex(HashMap<String, ArrayList<ArrayList<String>>> newRecords, HashSet<String> nextLadder, ArrayList<ArrayList<String>> curLists) {
      for (String s : nextLadder) {
        for (ArrayList<String> curList : curLists) {
          ArrayList<String> path = new ArrayList<String>(curList);
          path.add(s);
          if (newRecords.containsKey(s)) {
            newRecords.get(s).add(path);
          } else {
            ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
            paths.add(path);
            newRecords.put(s, paths);
          }
        }
      }
    }

    public HashSet<String> nextLadderWord(String str, HashSet<String> dict) {
      HashSet<String> nextL = new HashSet<String>();
      for (int i = 0; i < str.length(); i++) {
        char curLetter = str.charAt(i);
        for (char c = 'a'; c <= 'z'; c++) {
          char[] tmp = str.toCharArray();
          if (curLetter != c) {
            tmp[i] = c;
            String tmpStr = new String(tmp);
            if (dict.contains(tmpStr)) {
              nextL.add(tmpStr);
            }
          }
        }
      }
      return nextL;
    }
    
  }

}
