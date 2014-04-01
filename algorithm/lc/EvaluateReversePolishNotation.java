package algorithm.lc;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * 
 * Some examples: 
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 
 * ["4", "13","5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 */
public class EvaluateReversePolishNotation {

  // O(n) space, O(n) time
  public class Solution {
    public int evalRPN(String[] tokens) {
      // IMPORTANT: Please reset any member data you declared, as
      // the same Solution instance will be reused for each test case.
      Stack<Integer> stack = new Stack<Integer>();
      for (String token : tokens) {
        if (token.equals("+") || token.equals("-") || token.equals("*")
            || token.equals("/")) {
          int second = stack.pop();
          int first = stack.pop();
          int res = 0;
          if (token.equals("+")) {
            res = first + second;
          } else if (token.equals("-")) {
            res = first - second;
          } else if (token.equals("*")) {
            res = first * second;
          } else {
            res = first / second;
          }
          stack.push(res);
        } else {
          stack.push(Integer.parseInt(token));
        }
      }

      return stack.pop();
    }
  }
}
