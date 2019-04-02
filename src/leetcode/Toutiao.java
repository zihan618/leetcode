package leetcode;

import java.util.*;

public class Toutiao {
    static class Interval {
        public int start;
        public int end;
    }

    int res1, res2;
    public static void main(String[] args) {
        int[] a = new int[]{6,11,14,16,18,22,24,27,29,32,33,35,36,40,42,44,47,50,51,52,54,58,60,62,64,67,70,73,76,79,82,84,87,88,91,94,97,99,101,102};
        //System.out.println(new Solution().Find(14, ));
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public class Solution {
        boolean[][] visited;
        int[][] a = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int mRows = 0, mCols = 0;
        char[] mStr;
        Stack<Character> stack = new Stack<>();
        char[] mMatrix;
        public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
        {
            visited = new boolean[rows][cols];
            mStr = str;
            mRows = rows;
            mCols = cols;
            mMatrix = matrix;
            visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (canReach(i, j)){
                        return true;
                    }
                }
            }
            return false;

        }

        private boolean canReach(int row, int col) {
            if (row < 0 || row >= mRows || col < 0 || col >= mCols || visited[row][col]||mMatrix[row * mCols + col] != mStr[stack.size()]) {
                return false;
            }

            if (mMatrix[row * mCols + col] == mStr[stack.size()]){
                stack.push(mStr[stack.size()]);
                for (int[] ints : a) {
                    if (canReach(row + ints[0], col + ints[1])) {
                        return true;
                    }
                }
                stack.pop();
            }
            return false;
        }


    }

}
