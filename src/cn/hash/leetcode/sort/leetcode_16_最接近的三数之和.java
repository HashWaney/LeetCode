package cn.hash.leetcode.sort;

/**
 * 给定一个包括n个整数的数组nums和一个目标值target,找出nums中的三个整数，使得他们的和与target最接近。返回这个三个数的和
 * 输入： nums = [-1,2,-3,-4] target =4;
 * 输出： -1+2+-3=-2
 * <p>
 * <p>https://leetcode-cn.com/problems/3sum-closest/solution/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/
 * 解题思路：
 * 标签：排序和双指针
 * 1. 首先对数组进行排序，时间复杂度O(nlogn)
 * 2. 在数组nums中，进行遍历，每遍历一个值利用其下表i，记录固定的nums[i];
 * 3. 在使用前指针指向start=i+1处，后指针end=nums.length-1,也就是结尾处。
 * 4. 根据sum=nums[i]+nums[start]+nums[end]的结果，与执行数组前三个元素相加的值source进行比较，
 * 如果sum <source,将sum值赋值给source，说明在后续的相加过程中还有比前三个元素相加小的值
 * <p>
 * 5. 判断sum与target的大小关系，why？如果sum>target 说明sum的元素组合还是较大，应该将后指针前移，如果sum<target，说明sum的元素组合还是较小，那么应该将前指针start后移
 * 如果sum和target相等，说明sum的组合刚好符合target之间的距离，
 * <p>
 * <p>
 * <p>
 * 遍历过程 固定的值为n次，双指针n次O（n2）
 * O(nlogn)+O(n2)=O(n2);
 */
public class leetcode_16_最接近的三数之和 {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int a = threeSumCloseSet(new int[]{-3, 2, -2, 1}, 1);
        System.err.println( "结果：" + a);

    }


    public static int threeSumCloseSet(int[] num, int target) {
        int sum = num[0] + num[1] + num[2];
        for (int i = 0; i < num.length; i++) {
            int startPtr = i + 1;
            int endPtr = num.length - 1;
            while (startPtr < endPtr) {
                int abs = num[i] + num[startPtr] + num[endPtr];
                if (Math.abs(target - abs) < Math.abs(target - sum)) {
                    sum = abs;
                }
                if (abs > target) {
                    endPtr--;
                } else if (abs < target) {
                    startPtr++;
                } else {
                    return sum;
                }

            }
        }
        return sum;
    }
}
