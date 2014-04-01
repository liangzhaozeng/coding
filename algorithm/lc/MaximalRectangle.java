package algorithm.lc;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 * 
 */
public class MaximalRectangle {
  
  // O(mn) space, O(mn^2) time, where m is the width and n is the height
  public class Solution { 
    public int maximalRectangle(char[][] matrix) {
          // IMPORTANT: Please reset any member data you declared, as
          // the same Solution instance will be reused for each test case.
      if (matrix.length == 0 || matrix[0].length == 0) {
        return 0;
      }
      
      int maxArea = 0;
      // record the number of continuous 1's ends at row i column j
      int[][] continuousOnes = new int[matrix.length][matrix[0].length];
      for (int i = 0; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[i].length; ++j) {
          if (matrix[i][j] == '1') {
            if (j == 0) {
              continuousOnes[i][j] = 1;
            } else {
              continuousOnes[i][j] = continuousOnes[i][j - 1] + 1;
            }
            int curHeight = i;
            int minWidth = continuousOnes[i][j];
            while (curHeight >= 0) { // find the height of the square that use i, j as the bottom right
              if (continuousOnes[curHeight][j] == 0) {
                break;
              }
              else { // grow up
                minWidth = Math.min(minWidth, continuousOnes[curHeight][j]);
                int area = (i - curHeight + 1) * minWidth;
                maxArea = Math.max(maxArea, area);
              }
              --curHeight;
            }
          }
        }
      }

      return maxArea;
    }
  }

}