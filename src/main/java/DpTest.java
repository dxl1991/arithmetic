/**
 * 一个动态规划算法的例子：一种货币有三种面值：1、5、11。写一个函数计算出凑足n需要的最少货币张数
 * 例如：15需要的最少货币张数是3。解题思路：先分别每种面值货币拿出一张，然后分别算出凑足剩下数量的货币最少张数，并取最少值，最终结果就是这个最少值加1
 * 推理公式 f(n) = min{f(n - 1),f{n - 5},f{n - 11}} + 1
 * 适合用动态规划结界的问题：能将大问题拆成几个小问题，且满足无后效性、最优子结构性质的问题
 * 无后效性：如果给定某一阶段的状态，则在这一阶段以后过程的发展不受这阶段以前各段状态的影响
 * 最优子结构：大问题的最优解可以由小问题的最优解推出
 * @author dengxinlong
 * @date 2024/3/20 9:48
 */
public class DpTest {

    public static void main(String[] args) {
//        System.out.println(dpCount(22));
//        int[] nums = new int[]{226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124};
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(rob(nums));
    }

    private static int dpCount(int n){
        if(n == 0){
            return 0;
        }
        if(n >= 11){
            return Math.min(dpCount(n - 1),Math.min(dpCount(n - 11),dpCount(n - 5))) + 1;
        }else if (n >= 5){
            return Math.min(dpCount(n - 1),dpCount(n - 5)) + 1;
        }
        return dpCount(n - 1) + 1;
    }

    /**
     * 动态规划例子2：
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * f(n) = max(f(n-1),f(n-2)+n)
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int[] result = new int[nums.length];
        return rob(nums,nums.length - 1,result);
    }

    private static int rob(int[] nums,int index,int[] result){
        if(index < 0){
            return 0;
        }
        if(index == 0){
            return nums[index];
        }
        if(index == 1){
            return Math.max(nums[0],nums[1]);
        }
        if(result[index] > 0){
            return result[index];
        }
        int ret = rob(nums,index - 1,result);
        if(nums[index] > 0){
            ret = Math.max(ret,rob(nums,index - 2,result) + nums[index]);
        }
        result[index] = ret;
        return ret;
    }
}
