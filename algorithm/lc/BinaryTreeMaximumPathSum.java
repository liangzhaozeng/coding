package algorithm.lc;

/**
 * Given a binary tree, find the maximum path sum.
 * 
 * The path may start and end at any node in the tree.
 * 
 * For example: Given the below binary tree,
 * 
 *      1 
 *     / \ 
 *    2   3
 * 
 * Return 6.
 */
// O(1) space, O(n) time
public class BinaryTreeMaximumPathSum {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public class Solution {
    public int maxPathSum(TreeNode root) {
          // IMPORTANT: Please reset any member data you declared, as
          // the same Solution instance will be reused for each test case.
      int[] max = new int[1];
      max[0] = Integer.MIN_VALUE;
      maxPath(root, max);
      return max[0];
    }
    
    /**
     *  Store the maximize path and return the max extensible path.
     */
    private int maxPath(TreeNode node, int[] max) {
      if (node == null) {
        return 0;
      }
      int leftExt = maxPath(node.left, max);
      int rightExt = maxPath(node.right, max);
      
      int maxExt = Math.max(Math.max(leftExt, rightExt), 0) + node.val;
      max[0] = Math.max(Math.max(maxExt, max[0]), leftExt + rightExt + node.val);
      return maxExt;
    }
  }

}