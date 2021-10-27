package leetcode;
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums 是一个非递减数组
// -109 <= target <= 109
//
// Related Topics 数组 二分查找
// 👍 1248 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/14 18:02
 */
public class SearchRange {
    //二分查找
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > target){
                right = mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                int l = mid;
                int r = mid;
                while (l >= 0 && nums[l] == target){
                    l--;
                }
                while (r < nums.length && nums[r] == target){
                    r++;
                }
                res[0] = l + 1;
                res[1] = r - 1;
                break;
            }
        }
        return res;
    }
}
