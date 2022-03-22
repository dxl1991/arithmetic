import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

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

    public static void main(String[] args) throws Exception{
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
        //System.out.println(hammingWeight(00001011));
        //testFor();
       // testTimeZone();
//        testCPU50Per();
//        Map<String, String> content = new HashMap<>();
//        content.put("zh","");
//        System.out.println(content.values().iterator().next());
        System.out.println(new HashMap<Integer, Integer>(){
            {
                put(2,2);
            }
        }); //相当于创建的是一个HashMap的子类对象(内部类)，且该子类中有实例代码块做一个初始化赋值操作

        System.out.println(Pattern.matches("^(?!\\s)(?!.*\\s$)(?!.*\\s{2,}.*)[^.]{1,14}$", "顶顶 !顶顶 顶顶顶顶顶顶顶"));
    }

    public static void testTimeZone() throws ParseException {
        // 获取 JVM 启动时获取的时区
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(TimeZone.getDefault());
        System.out.println(ZoneId.systemDefault());
        // 获取任意指定区域的时区
        String[] zoneIDs = TimeZone.getAvailableIDs();
        Set<Integer> sets = new HashSet<>();
        for(String zoneID: zoneIDs) {
            TimeZone timeZone = TimeZone.getTimeZone(zoneID);
            System.out.println(timeZone);
            sets.add(timeZone.getRawOffset());
        }
        System.out.println(sets);
        System.out.println(sets.size());
        Date date = new SimpleDateFormat("yyyy_MM_ddZ").parse("2021_12_2-1200");
        System.out.println(date);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int k=0;k<n - 1;k++){
            int[] temp = new int[n - 2*k];
            if(temp.length <= 1){
                return;
            }
            int index = 0;
            int jj = n - 1 - k;
            int ii = k;
            for(int m=n-1-k;m>=k;m--){
                temp[index++] = matrix[m][jj];
                matrix[m][jj] = matrix[ii][m];
            }
            jj = k;
            ii = k;
            for(int m=n-1-k;m>=k;m--){
                matrix[ii][m] = matrix[n - 1 - m][jj];
            }
            jj = k;
            ii = n - 1 - k;
            for(int m=k;m<=n-1-k;m++){
                matrix[m][jj] = matrix[ii][m];
            }
            ii = n - 1 - k;
            for(int m=n-1-k;m>=k;m--){
                matrix[ii][m] = temp[--index];
            }
        }
    }

    static class TempInt{
        int i = 3;
        int size(){
            System.out.println("call size()");
            return i;
        }
    }

    public static void testFor(){
        TempInt tempInt = new TempInt();
        for(int i=0,length = tempInt.size();i<length;i++){
            System.out.println("--------"+Double.MAX_VALUE);
        }
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static String countAndSay(int n) {
        String oldStr = "1";
        String str = "";
        for(int i=1;i<n;i++){
            int m = 0;
            char c = oldStr.charAt(0);
            for(int j=0;j<oldStr.length();j++){
                char k = oldStr.charAt(j);
                if(k != c){
                    str += m + "" + c;
                    m = 0;
                }
                m++;
                c = k;
            }
            str += m + "" + c;
            oldStr = str;
            str = "";
        }
        return oldStr;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<candidates.length;i++){
            if(candidates[i] > target){
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(candidates[i]);
            int temp = candidates[i];
            while (temp <= target){
                if(temp == target){
                    res.add(list);
                    break;
                }
                for(int j=0;j<candidates.length;j++){
                    List<Integer> list1 = new ArrayList<>(list);
                    int temp1 = temp;
                    if(i == j){
                        continue;
                    }
                    temp1 += candidates[j];
                    if(temp1 > target){
                        break;
                    }
                    list1.add(candidates[j]);
                    if(temp1 == target){
                        res.add(list1);
                        break;
                    }
                }
                temp += candidates[i];
                list.add(candidates[i]);
            }
        }
        return res;
    }
    public static int totalMoney(int n) {
        int sum = 0;
        int week = (n - 1) / 7 + 1;
        int day = n % 7;
        for(int i = 1;i<=week;i++){
            int m = i == week ? day : 7;
            for(int j=1;j<=m;j++){
                sum += j + i - 1;
            }
        }
        return sum;
    }
    public static int divide(int dividend, int divisor) {
        long absDividend = dividend;
        absDividend = Math.abs(absDividend);
        long absDivisor = divisor;
        absDivisor = Math.abs(absDivisor);
        long count = 0;
        long number = 0;
        while (true){
            number += absDivisor;
            if(number > absDividend){
                break;
            }
            count++;
        }
        if(count == 0){
            return 0;
        }
        if(count > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0){
            return -(int)count;
        }
        return (int)count;
    }

    static class Vec2{
        float x;
        float y;
        Vec2(float x,float y){
            this.x = x;
            this.y = y;
        }
    }

    public static float threePointAngle(Vec2 cen, Vec2 first,Vec2 second){
        float dx1, dx2, dy1, dy2;
        float angle;

        dx1 = first.x - cen.x;
        dy1 = first.y - cen.y;

        dx2 = second.x - cen.x;
        dy2 = second.y - cen.y;

        float c = (float) (Math.sqrt(dx1 * dx1 + dy1 * dy1) * Math.sqrt(dx2 * dx2 + dy2 * dy2));

        if (c == 0) return -1;

        angle = (float)Math.acos((dx1 * dx2 + dy1 * dy2) / c);
        return angle;
    }

    public static int strStr(String haystack, String needle) {
        for(int i=0;i<haystack.length();i++){
            int p = 0;
            for(p = 0;p<needle.length();p++){
                if(needle.charAt(p) != haystack.charAt(p+i)){
                    break;
                }
            }
            if(p == needle.length()){
                return i;
            }
        }
        return -1;
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = ++digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static int hammingWeight(int n) {
        int j =1;
        int res=0;
        for(int i=0;i<32;i++){
            if((n & j) != 0){
                res++;
            }
            j = j <<1;
        }
        return res;
    }

    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Stack<Character> sets = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                sets.push(c);
            }else{
                if(sets.isEmpty()){
                    return false;
                }
                char temp = sets.pop();
                switch (c){
                    case ')':
                        if(temp != '('){
                            return false;
                        }
                        break;
                    case ']':
                        if(temp != '['){
                            return false;
                        }
                        break;
                    case '}':
                        if(temp != '{'){
                            return false;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return sets.isEmpty();
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
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("coreCount = "+coreCount);
        for (int i = 0; i < coreCount; i++) {
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
