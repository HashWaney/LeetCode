package cn.hash.leetcode.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */

public class leetcode_03_无重复字符的最长子串 {
    public static void main(String[] args) {
        String s = "abcbeddacd";
        int n = s.length();
        String maxstr = "";
        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = i; j < n; j++) {
                if (!stringBuilder.toString().contains(s.charAt(j) + "")) {
                    stringBuilder.append(s.charAt(j));
                    maxstr = stringBuilder.length() - maxstr.length() > 0 ? stringBuilder.toString() :
                            maxstr;
                } else {
                    break;
                }

            }
        }
        System.err.println("max str :" + maxstr);
        System.err.println("最长子串 的长度:"+returnNonRepeatCharLen("abcdabc"));
    }

    // 滑动窗口
    public static int returnNonRepeatCharLen(String string) {
        int n = string.length();
        int len = 0;
        int start = 0;
        // 固定start不变，
        // 偏移end
        // 遇到重复字符，更新start
        // 计算len = end - start +1
        // 定义角标与字符的对应关系，散列表 HashMap.通过散列表比较遍历到的元素是否和之前存储的元素重复
        // 如果重复，更新start，重新计算len，继续遍历
        // 如果没有，继续遍历
        Map<Character, Integer> hash = new HashMap<>();
        for (int end = 0; end < n; end++) {
            if (hash.containsKey(string.charAt(end))) {
                start = Math.max(start, hash.get(string.charAt(end)));
            }
            len = Math.max(len, end - start + 1);
            hash.put(string.charAt(end), end + 1);
        }

       return len;
    }
}
