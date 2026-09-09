package leetcode.arr;

/**
 * 螺旋矩阵II
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 */
public class Solution59 {

    public static void main(String[] args) {
        int n = 3;
        int[][] res = new Solution59().solution(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();
        }
    }
    public int[][] solution(int n) {
        int[][] res = new int[n][n];
        int left = 0;
        int right = n;
        int up = 0;
        int down = n;
        // 左右，右左，上下，下上
        int lr = 1, rl = -1, ud = 2, du = -2;
        int direction = lr;
        int count = 1;
        // 这里一定注意要加1，因为count是从1开始的
        while (count < n * n + 1) {
            // 从左到右
            if (direction == lr) {
                for (int i = left; i < right; i++) {
                    res[left][i] = count++;
                }
                up++;
                direction = ud;
                continue;
            }
            // 从上到下
            if (direction == ud) {
                for (int i = up; i < down; i++) {
                    res[i][right - 1] = count++;
                }
                right--;
                direction = rl;
                continue;
            }
            // 从右到左
            if (direction == rl) {
                for (int i = right - 1; i >= left; i--) {
                    res[down - 1][i] = count++;
                }
                down--;
                direction = du;
                continue;
            }
            // 从下到上
            for (int i = down - 1; i >= up; i--) {
                res[i][left] = count++;
            }
            left++;
            direction = lr;
        }
        return res;
    }
}
