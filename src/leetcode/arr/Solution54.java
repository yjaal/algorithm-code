package leetcode.arr;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * <p>
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Solution54 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> solution = new Solution54().spiralOrder(matrix);
        for (int i : solution) {
            System.out.print(i + ",");
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length;
        int up = 0;
        int down = matrix.length;
        int len = right * down;
        List<Integer> res = new ArrayList<>(len);
        int count = 0;
        // 左右，右左，上下，下上
        int lr = 1, rl = -1, ud = 2, du = -2;
        int direction = lr;
        while (count < len) {
            // 从左到右
            if (direction == lr) {
                for (int i = left; i < right; i++) {
                    res.add(count++, matrix[left][i]);
                }
                up++;
                direction = ud;
                continue;
            }
            // 从上到下
            if (direction == ud) {
                for (int i = up; i < down; i++) {
                    res.add(count++, matrix[i][right - 1]);
                }
                right--;
                direction = rl;
                continue;
            }
            // 从右到左
            if (direction == rl) {
                for (int i = right - 1; i >= left; i--) {
                    res.add(count++, matrix[down - 1][i]);
                }
                down--;
                direction = du;
                continue;
            }
            // 从下到上
            for (int i = down - 1; i >= up; i--) {
                res.add(count++, matrix[i][left]);
            }
            left++;
            direction = lr;
        }
        return res;
    }
}
