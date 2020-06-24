package com.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _02两数之和 {



    public static void main(String[] args) {
       int[] intes = new int[]{2,3,4,11};
        int[] ints = twoSum(intes, 13);
        ArrayList arrayList =new ArrayList();
        arrayList.add(1);

        arrayList.add(2);

        arrayList.add(3);

        arrayList.add(1);
        for (int i :ints)
        {
            System.err.println(i);

        }
        System.err.println();


    }

    public static int[] twoSum(int[] nums,int target){

        //1.创建一个HashMap空集合
        Map<Integer,Integer> map =new HashMap<>();
        //2.遍历集合
        for (int i=0; i<nums.length; i++){
            if (map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }

            //3.填充map集合，不过是是将数组中的value 作为key，角标作为value 存入map；
            //是为了在之后取出匹配的数组的角标。
            map.put(nums[i],i);
        }

        return null;
    }
}
