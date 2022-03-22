package leetcode;
//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
//
// 假设你总是可以到达数组的最后一个位置。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 示例 2:
//
//
//输入: nums = [2,3,0,1,4]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 104
// 0 <= nums[i] <= 1000
//
// Related Topics 贪心 数组 动态规划
// 👍 1133 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/27 11:19
 */
public class Jump {
    public static void main(String[] args) {
        Jump jump = new Jump();
        System.out.println(jump.jump2(new int[]{2,3,1,1,4}));
    }
    private int minNum = Integer.MAX_VALUE;
    public int jump(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        dfs(nums,0,0);
        return minNum;
    }

    public void dfs(int[] nums,int index,int num){
        num++;
        for(int i=1;i<=nums[index] && index + i < nums.length;i++){
            if(index + i == nums.length - 1){
                minNum = Math.min(minNum,num);
                return;
            }
            dfs(nums,index + i,num);
        }
    }
    public int jump2(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        return dfs2(nums,nums.length - 1,0);
    }

    public int dfs2(int[] nums,int target,int num){
        num++;
        for(int i=0;i<target;i++){
            if(nums[i] + i >= target){
                if(i == 0){
                    return num;
                }
                return dfs2(nums,i,num);
            }
        }
        return -1;
    }
}
