package cn.hash.leetcode.sort;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 「暴力算法」是基础，「动态规划」必须掌握，「中心扩散」方法要会写；
 * <p>
 * 1.根据回文子串的定义，枚举所有长度大于等于2的子串，依次判断他们是否是回文
 * 2.在具体实现，可以只针对大于当前得到的最长回文子串长度的子串进行回文验证
 * 3.在记录最长回文子串的时候，可以只记录当前子串的起始位置和子串的长度，不必做截取，这个在遍历完成之后最后做处理即截取的就是最长的回文子串；
 */
public class leetcode_05_最长回文子串 {

    public static void main(String[] args) {
        String aa = BruteForce("aaaabmaaaa");
        System.err.println(aa);
    }

    public static String BruteForce(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 0; // 定义一个最长回文子串的长度变量
        int begin = 0;//定义最长回文子串的起始位置，因为了有了起始位置和最长回文子串的长度，就可以通过substring来截取对应的子串

        char[] charArray = s.toCharArray();
        // 枚举所有长度大于1（2以上）的子串charArray[i..j]
        for (int i = 0; i < charArray.length; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                // 大于1
                if (j - i + 1 > maxLen && isHuiWen(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);

    }

    // 验证子串s[left..right]是否为回文串 比如 aaabaaa
    // a[left] = a[right] 好好体会一下， 就以aaabaaa这个为例
    private static boolean isHuiWen(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            right--;
            left++;
        }
        return true;
    }


}
