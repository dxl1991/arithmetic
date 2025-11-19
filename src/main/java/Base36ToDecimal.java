/**
 * @author dengxinlong
 * @date 2025/6/30 15:09
 * @description TODO
 */
public class Base36ToDecimal {
    public static void main(String[] args) throws Exception{
        System.out.println(base36ToDecimal("3bqhih60ap"));
    }

    public static long base36ToDecimal(String base36) {
        long decimal = 0;
        int length = base36.length();
        for (int i = 0; i < length; i++) {
            char c = base36.charAt(i);
            long value;
            if (c >= '0' && c <= '9') {
                value = c - '0';
            } else if (c >= 'a' && c <= 'z') {
                value = c - 'a' + 10;
            } else {
                throw new IllegalArgumentException("Invalid base36 number");
            }
            decimal += value * (long) Math.pow(36, length - 1 - i);
        }
        return decimal;
    }
}
