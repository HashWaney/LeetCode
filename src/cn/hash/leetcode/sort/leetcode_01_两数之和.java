package cn.hash.leetcode.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组num 和一个目标target，在数组中找出和为目标值的两个整数，并且返回
 * 他们的数组下标
 * <p>
 * num =[1,2,3,4] target=7
 * num[2]+num[3] =3+4=7
 * [2,3]
 */
public class leetcode_01_两数之和 {

    static int[] a = new int[]{2, 5, 5,11};
    static int target = 10;

    public static void main(String[] args) {
//        int[] aa = twoSumIndex(a, target);
        int[] aa = doubleHashModifyTwoSum(a, target);
//        int[] aa = singleBetterHashModifyTwoSum(a, target);
        System.out.println("aa: index " + aa[0] + "," + aa[1]);//aa: index 0,2

    }

    // 1. 暴力解法:遍历每个元素x，并查找是否存在一个值与target-x相等的目标元素
    // 时间复杂度O（n2）
    // 对于每个元素（n个元素），试图通过遍历数组的其余部分来寻找它所对应的目标元素，将耗费O(n)的时间，
    // 因此时间复杂度为O(n2)
    // 空间复杂度O(1)
    public static int[] twoSumIndex(int[] num, int tareget) {
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] == tareget - num[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("error to find index");
    }


    /**
     * 时间复杂度：O(N);
     * 包含有n个元素的列表遍历两次，由于哈希表将查找时间缩短到O(1),所以时间复杂度为O(n)
     * 空间复杂度：O(N);
     * 所需的额外空间取决于哈希表中的存储的元素数量，那么该表中存储了n个元素。
     */
    public static int[] doubleHashModifyTwoSum(int[] nums, int target) {
        //1.定义一个HashMap ,使用HashMap根据nums[i]来存储对应的角标i
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            hashMap.put(nums[i], i);

        }
        System.out.println("hashMap size:"+hashMap.size()); // 可知，如果有重复元素，则会把前一个角标覆盖。
        //2.再次遍历数组，找到target-nums[i]的元素是否在map中存在，如果存在，此时可以记录当前遍历到的
        //  index以及根据hash.get(target-nums[i])取出在1步中存储的角标
        for (int i = 0; i < nums.length; i++) {
            int com = target - nums[i];
            // 不等于i说明不是同一个角标
            if (hashMap.containsKey(com) && hashMap.get(com) != i) {
                return new int[]{i, hashMap.get(com)};
            }
        }
        throw new IllegalArgumentException("error to find the index");

    }

    //一遍哈希表：
    // 一次完成，在进行迭代并将元素插入到表中的同时，还会检查表中是否已经存在当前元素所对应的目标元素
    // 如果存在，找到对应的角标，返回即可。
    // 时间复杂度：O(N)
    //  只遍历了包含有n个元素的列表一次，在表中进行的每次查找只花费了O(1)的时间
    // 空间复杂度：O(N)
    //  所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储n个元素。
    //
    public static int[] singleBetterHashModifyTwoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int com = target - nums[i];
            if (hash.containsKey(com)) { // 这里不需要判断hash.get(com)!=i 因为这个i是在if之后存进去的，
                // 所以这里处理的hash.get(com)和i肯定是不同的
                return new int[]{hash.get(com), i};
            }
            hash.put(nums[i], i);
        }
        throw new IllegalArgumentException("error to find the index");
    }


}

