import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author dengxinlong
 * @Date 2019/10/15
 */
public class TestClass {
    private static TestA a = new TestA("aaaaaaaaa");
    private TestA b = new TestA("bbbbbbbbb");

    TestClass() {
        System.out.println("cccccccc");
    }

    public static void main(String[] args) {
        //        new TestClass();
        //        int count = 1000;
        //        int max = 100000;
        //        int[] temp = new int[count];
        //        Random random = new Random();
        //        for(int i=0;i<count;i++){
        //            temp[i] = random.nextInt(max);
        //        }
        //        //        quik_sort(temp);
        //        //        heapSort(temp);
        //        //        int[] temp2 = topK3(temp, 4);
        //        RadixSort.radixSort(temp,max);
        //        for (int i : temp) {
        //            System.out.print(i + ",");
        //        }
        //        Executors.newCachedThreadPool();
//        System.out.println(Integer.toBinaryString(-3333));
//        writeVarInt32(-3333);
        //        new TestClass().testWait();
//        AtomicInteger conv = new AtomicInteger(Integer.MAX_VALUE);
//        System.out.println(conv.incrementAndGet());
//        System.out.println(conv.incrementAndGet());
//        testTreeMap();
//        testConcurrentHashMap();
//        System.out.println("邓新龙邓新龙邓新龙邓新龙".length());
//        sort();
//        testTreeMap();
//        setStatus(1,true,1);
//        testSchedule();
        //testArray();

       // merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
        //System.out.println(lengthOfLongestSubstring("pwwkew"));
        //System.out.println(longestCommonPrefix(new String[]{"aflow","afloor","afloat"}));
        //System.out.println(reverseWords("a good   example"));
        //System.out.println(threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
        //System.out.println(pivotIndex(new int[]{1, 2, 3}));
        //System.out.println(searchInsert(new int[]{1,3,5,6},7));
        //merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
    }

    public static int removeDuplicates(int[] nums) {
        int newCount = nums.length;
        for(int i=0;i<nums.length;i++){
            int j=i+1;
            while (j < nums.length && nums[j] == nums[i]){
                j++;
            }
            if(j > i+1){
                nums[i+1] = nums[j];
                newCount -= (j - i - 1);
            }
        }
        return newCount;
    }

    //区间合并
    public static int[][] merge(int[][] intervals) {
        int count = intervals.length;
        for(int i=0;i<intervals.length;i++){
            for(int j=i+1;j<intervals.length;j++){
                if((intervals[j][0] <= intervals[i][1] && intervals[j][0] >= intervals[i][0]) ||
                        (intervals[j][1] <= intervals[i][1] && intervals[j][1] >= intervals[i][0]) ||
                        (intervals[j][1] >= intervals[i][1] && intervals[j][0] <= intervals[i][0])){
                    intervals[j][0] = Math.min(intervals[i][0],intervals[j][0]);
                    intervals[j][1] = Math.max(intervals[i][1],intervals[j][1]);
                    intervals[i][0] = Integer.MAX_VALUE;
                    count--;
                    break;
                }
            }
        }
        int[][] ans = new int[count][2];
        int index = 0;
        for(int i=0;i<intervals.length;i++){
            if(intervals[i][0] == Integer.MAX_VALUE){
                continue;
            }
            ans[index][0] = intervals[i][0];
            ans[index][1] = intervals[i][1];
            index++;
        }
        return ans;
    }

    //二分查找,返回target应该插入有序数组的下标
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while(left <= right){
            mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }
        return left;
    }

    //寻找中心下标，下标左边元素相加等于右边元素相加
    public static int pivotIndex(int[] nums) {
        int left = 0;
        int right = Arrays.stream(nums).sum();
        for(int i=0;i<nums.length;i++){
            right += nums[i];
        }
        for(int i=0;i<nums.length;i++){
            if(i > 0){
                left += nums[i-1];
            }
            right -= nums[i];
            if(right == left){
                return i;
            }
        }
        return -1;
    }

    //找出所有三数之和为0的不重复组合
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int l, r, sum, n = nums.length;
        Arrays.sort(nums);

        for(int i = 0; i < n; i++){
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            else if(nums[i] > 0) return result;
            else{
                l = i + 1;
                r = n - 1;
                while(l < r){
                    sum = nums[i] + nums[l] + nums[r];
                    if(sum == 0){
                        result.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                        while(l < r && nums[l] == nums[l - 1]) l++;
                        while(l < r && nums[r] == nums[r + 1]) r--;
                    }
                    else if(sum < 0) l++;
                    else r--;
                }
            }
        }
        return result;
    }

    //给定一个字符串，逐个翻转字符串中的每个单词,并去掉多余空格
    public static String reverseWords(String s) {
        if(s==null || (s=s.trim()).isEmpty()) return "";
        StringBuilder result = new StringBuilder(s).reverse();
        int start = 0;
        int end = 0;
        int i , j;
        for(i = 0;i<result.length();i++){
            if(result.charAt(i) == ' '){
                j = i + 1;
                while (result.charAt(j) == ' '){
                    j++;
                }
                result.delete(i+1,j);
                end = i-1;
                revSingleWord(result,start,end);
                start = i + 1;
            }
        }
        revSingleWord(result,start,i-1);
        return result.toString();
    }
    private static void revSingleWord(StringBuilder sb,int start,int end){
        while (start < end){
            char temp = sb.charAt(start);
            sb.setCharAt(start++,sb.charAt(end));
            sb.setCharAt(end--,temp);
        }
    }

    //查找字符串公共前缀
    public static String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        // 空数组
        if(len==0)
            return "";
        // 数组中只有一个字符串时
        if(len==1)
            return strs[0];
        //先默认第一个字符串为公共前缀 然后依次减少一个字符比较
        String s_prefix = strs[0];
        // 否则依次比较判断 选第一个字符串与剩下的依次比较
        for(int i=0;i<len;i++){
            // 当与后面的字符串匹配不符合时，截取s_prefix
            //如果与第二个没有匹配到前缀，后面的字符串就不要再匹配了
            if(!strs[i].startsWith(s_prefix)){
                s_prefix = s_prefix.substring(0,s_prefix.length()-1);
                i--;
            }
        }
        return s_prefix;
    }

    //查找最长非重复子串长度
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        HashSet set = new HashSet<Integer>();
        for(int i=0;i<s.length();i++){
            set.clear();
            set.add(s.charAt(i));
            for(int j=i+1;j<s.length();j++){
                char m = s.charAt(j);
                if(set.contains(m)){
                    break;
                }
                set.add(m);
            }
            max = Math.max(max,set.size());
        }
        return max;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int begin = 0;
        for(int i=0;i<n;i++){
            int num = nums2[i];
            for(int j=begin;j<m+n;j++){
                if(nums1[j] == 0){
                    nums1[j] = num;
                    break;
                }
                if(nums1[j] >= num){
                    begin = j+1;
                    for(int k= m+n-1;k>j;k--){
                        nums1[k] = nums1[k-1];
                    }
                    nums1[j] = num;
                    break;
                }
            }
        }
    }

    /**
     * Cache是由很多个cache line组成的。每个cache line通常是64字节，并且它有效地引用主内存中的一块儿地址。一个Java的long类型变量是8字节，因此在一个缓存行中可以存8个long类型的变量。
     * CPU每次从主存中拉取数据时，会把相邻的数据也存入同一个cache line。
     * 在访问一个long数组的时候，如果数组中的一个值被加载到缓存中，它会自动加载另外7个。因此你能非常快的遍历这个数组。事实上，你可以非常快速的遍历在连续内存块中分配的任意数据结构。
     * 下面的例子是测试利用cache line的特性和不利用cache line的特性的效果对比。
     */
    public static void testArray(){
        long[][] arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i+=1) {
            for(int j =0; j< 8;j++){
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");//50ms

        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i+=1) {
            for(int j =0; j< 1024 * 1024;j++){
                sum = arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms"); //140ms
    }

    public static void testSchedule(){
        ScheduleExecutor frameSchedule = new ScheduleExecutor("frame", 1);
        AtomicInteger i = new AtomicInteger();
        frameSchedule.scheduleAtFixedRate(()->{
            try {
                Thread.sleep(60);
                System.out.println(i.incrementAndGet());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1000, 50, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置某一位的值为0或者1
     * @return
     */
    public static void setStatus(int status ,boolean flag, int index) {
        if (flag) {
            status = status | (0x1 << index);
        } else {
            status = status & (~(0x1 << index));
        }
        System.out.println("status = "+status);
    }
    /**
     * 中文、英文、数字、下划线、[]、-
     * @param username
     * @return
     */
    public static boolean checkUserName(String username) {
        String regExp = "^[\\u4E00-\\u9FA5A-Za-z0-9_\\-\\[\\]]+$";
        if (username.matches(regExp)) {
            return true;
        }
        return false;
    }
    public static void sort(){
        List<Test> testList = new ArrayList<>();
        for(int i=0;i<6;i++){
            testList.add(new Test(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(testList);
        for(Test test : testList){
            System.out.println(test);
        }
    }
    static class Test implements Comparable<Test>{
        int i;
        long time;
        Test(int i){
            this.i = i;
            this.time = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return "Test{" + "i=" + i + ", time=" + time + '}';
        }

        @Override
        public int compareTo(Test o) {
            return (int) (o.time - time);
        }
    }

    public static void testConcurrentHashMap(){
        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
        for(int i=0;i<10000;i++){
            map.put(i,""+i);
        }
        System.out.println(map.size());
        for(String s : map.values()){
            map.remove(Integer.valueOf(s));
        }
        System.out.println(map.size());
    }

    public static void testTreeMap(){
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(3,"3");
        map.put(4,"4");
        map.put(2,"2");
        map.put(1,"1");
        map.put(6,"6");
        map.put(9,"9");
        Collection<String> temp = map.subMap(9,map.lastKey()).values();
        for(String s : temp){
            System.out.println(s);
        }
    }

    public static void writeVarInt32(int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                byte b = (byte) value;
                System.out.println(Integer.toBinaryString(value));
                return;
            } else {
                byte b = (byte) ((value & 0x7F) | 0x80);
                System.out.println(Integer.toBinaryString((value & 0x7F) | 0x80));
                value >>>= 7;
                //        new TestClass().testWait();
                //        long updateInterval = TimeUnit.SECONDS.toMillis(30);
                //        System.out.println(updateInterval);
            }
        }
    }

    //多重循环，才能用lable
    private static void testLable(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        listLoop:
        for(int i : list){
            if(i % 2 == 0){
                continue;
            }
            for(int j : list){
                if(j == 4){
                    continue listLoop;
                }
                System.out.println(i + "," + j);
            }
        }
    }

    private void testWait() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            System.out.println("线程池执行1");
        });
        executorService.submit(() -> {
            System.out.println("线程池执行2");
        });
        new Thread(() -> {
            while (true) {
            }
        }, "thread-1").start();
        new Thread(() -> {
            LockSupport.park();
        }, "thread-2").start();
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Object o = new Object();
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    private void testDeadLock() {
        new Thread(() -> {
            lock1.lock();
            try {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lock();
                lock2.unlock();
            } finally {
                lock1.unlock();
            }

        }, "thread-1").start();
        new Thread(() -> {
            lock2.lock();
            try {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lock();
                lock1.unlock();
            } finally {
                lock2.unlock();
            }

        }, "thread-2").start();
    }

    private void testLock() {
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (o) {
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-1").start();
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (o) {
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-2").start();
    }

    private static void testCPU50Per() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                long time_start;
                int fulltime = 100;
                int runtime = 50;

                while (true) {
                    time_start = System.currentTimeMillis();
                    while ((System.currentTimeMillis() - time_start) < runtime) {
                    }
                    try {
                        Thread.sleep(fulltime - runtime);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }).start();
        }
    }
}
