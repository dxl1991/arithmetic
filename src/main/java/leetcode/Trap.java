package leetcode;
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 示例 1：
//
//
//
//
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//
//
// 示例 2：
//
//
//输入：height = [4,2,0,3,2,5]
//输出：9
//
//
//
//
// 提示：
//
//
// n == height.length
// 1 <= n <= 2 * 104
// 0 <= height[i] <= 105
//
// Related Topics 栈 数组 双指针 动态规划 单调栈
// 👍 2763 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/19 10:00
 */
public class Trap {
    public static void main(String[] args) {
        System.out.println(trap3(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    /**
     * 对于下标 *i*，下雨后水能到达的最大高度 = 下标 *i* 两边的最大高度的最小值.
     * 下标 *i* 处能接的雨水量 = 下标 *i* 处的水能到达的最大高度 - height[i] 。
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int count = 0;
        for(int i=1;i<height.length - 1;i++){
            int maxLeft = height[0];
            int maxRight = height[height.length - 1];
            for(int j = 0;j<height.length;j++){
                if(j < i && height[j] > maxLeft){
                    maxLeft = height[j];
                }
                if(j > i && height[j] > maxRight){
                    maxRight = height[j];
                }
            }
            int h = Math.min(maxLeft,maxRight);
            if(h > height[i]){
                count += (h - height[i]);
            }
        }
        return count;
    }
    /**
     * 动态规划做法：把每个下标的左边最大值和右边最大值求出来，并且存起来。然后按照上面的方法计算
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        for(int i=1;i<height.length;i++){
            leftMax[i] = Math.max(leftMax[i - 1],height[i]);
        }
        int[] rightMax = new int[height.length];
        rightMax[height.length - 1] = height[height.length - 1];
        for(int i=height.length - 2;i>=0;i--){
            rightMax[i] = Math.max(rightMax[i + 1],height[i]);
        }
        int count = 0;
        for(int i= 1;i<height.length - 1;i++){
            count += Math.min(leftMax[i],rightMax[i]) - height[i];
        }
        return count;
    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        int count = 0;
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        while (left < right){
            maxLeft = Math.max(maxLeft,height[left]);
            maxRight = Math.max(maxRight,height[right]);
            if(height[left] > height[right]){
                count += maxRight - height[right];
                right--;
            }else {
                count += maxLeft - height[left];
                left++;
            }
        }
        return count;
    }
}
