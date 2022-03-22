package leetcode;

import java.util.ArrayList;
import java.util.List;
//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯
// 👍 1602 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/28 15:46
 */
public class Permute {
    public static void main(String[] args) {
        System.out.println(new Permute().permute2(new int[]{1,2,3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();//排列的题又是一个回溯算法的题
        boolean[] used=new boolean[nums.length];  //我们定义的used是和原数组一样大小的，用于判断哪个数被用过了（因为每次的每个数只能用一次）
        backtracking(result,nums,new ArrayList<>(),used);   //直接上backtracking函数
        return result;
    }
    public void backtracking(List<List<Integer>>result,int[] nums,List<Integer>path,boolean[] used){
        if(path.size()==nums.length){
            result.add(new ArrayList<>(path));   //定义返回条件，当前的path长度等于数组长度时，将该path加入到result中
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;   //定义了去重的变量，如果重复直接跳过
            path.add(nums[i]);                //path吞一个数
            used[i]=true;                       //吞进数的值改为true
            System.out.println("递归之前 => " + path);
            backtracking(result,nums,path,used);   //直接递归
            path.remove(path.size()-1);  //吐出来
            used[i]=false;               //吐出的数改为false
            System.out.println("递归之后 => " + path);
        }
    }
    public List<List<Integer>> permute2(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (nums.length == 0) {
            return res;
        }

        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();

        dfs(nums,0, path, used, res);
        return res;
    }

    //如果在每一个 **非叶子结点** 分支的尝试，都创建 **新的变量** 表示状态，那么
    //在回到上一层结点的时候不需要「回溯」；
    //在递归终止的时候也不需要做拷贝。
    //这样的做法虽然可以得到解，但也会创建很多中间变量，这些中间变量很多时候是我们不需要的，会有一定空间和时间上的消耗。为了验证上面的说明，我们写如下代码进行实验：
    private void dfs(int[] nums, int depth,List<Integer> path, boolean[] used,List<List<Integer>> res) {
        if (depth == nums.length) {
            // 3、不用拷贝，因为每一层传递下来的 path 变量都是新建的
            res.add(path);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 1、每一次尝试都创建新的变量表示当前的"状态"
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(nums[i]);

                boolean[] newUsed = new boolean[nums.length];
                System.arraycopy(used, 0, newUsed, 0, nums.length);
                newUsed[i] = true;

                dfs(nums, depth + 1, newPath, newUsed, res);
                // 2、无需回溯
            }
        }
    }
}
