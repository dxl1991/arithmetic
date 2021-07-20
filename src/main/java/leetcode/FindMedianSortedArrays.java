package leetcode;
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
//
//
// 示例 1：
//
//
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
//
// 示例 2：
//
//
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
// 示例 3：
//
//
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
//
//
// 示例 4：
//
//
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
//
//
// 示例 5：
//
//
//输入：nums1 = [2], nums2 = []
//输出：2.00000
//
//
//
//
// 提示：
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
//
//
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
// Related Topics 数组 二分查找 分治算法
// 👍 4148 👎 0

/**
 * @author dcsz
 * @date 2021/6/3 17:12
 * @version 1.0
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,4,5};
        int[] nums2 = new int[]{3,4,6,7};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int index1 = 0,index2 = 0;
        int result1 = 0,result2 = 0;
        for(int i=0;i<=length/2;i++){
            if(index1 >= nums1.length){
                if(i == length / 2 - 1){
                    result1 = nums2[index2];
                }else if(i == length / 2){
                    result2 = nums2[index2];
                }
                index2++;
                continue;
            }
            if(index2 >= nums2.length){
                if(i == length / 2 - 1){
                    result1 = nums1[index1];
                }else if(i == length / 2){
                    result2 = nums1[index1];
                }
                index1++;
                continue;
            }
            if(nums1[index1] <= nums2[index2]){
                if(i == length / 2 - 1){
                    result1 = nums1[index1];
                }else if(i == length / 2){
                    result2 = nums1[index1];
                }
                index1++;
            }else{
                if(i == length / 2 - 1){
                    result1 = nums2[index2];
                }else if(i == length / 2){
                    result2 = nums2[index2];
                }
                index2++;
            }
        }
        return length % 2 == 1 ? result2 : (result1 + result2) / 2d;
    }
}
