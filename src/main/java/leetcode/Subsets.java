package leetcode;

import java.util.ArrayList;
import java.util.List;
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// nums 中的所有元素 互不相同
//
// Related Topics 位运算 数组 回溯算法
// 👍 1212 👎 0
/**
 * @author dcsz
 * @date 2021/6/9 11:17
 * @version 1.0
 */
public class Subsets {

    public static void main(String[] args) {
        subsets(new int[]{1,2,3});
        System.out.println(results);
    }

    static List<List<Integer>> results = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        backtrack(0, nums, new ArrayList<>());
        return results;
    }

    private static void backtrack(int i, int[] nums, ArrayList<Integer> tmp) {
        results.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
