package ch.coll.utilities;

public class COLLMath {
    public static long leastCommonMultiple(long[] nums) {
        if(nums.length == 0) {
            return -1;
        } else if(nums.length == 1) {
            return nums[0];
        }

        boolean isCommon = false;
        long test = nums[0];

        while(!isCommon) {
            isCommon = true;
            for(int i = 1; i < nums.length; i++) {
                if(test % nums[i] != 0) {
                    isCommon = false;
                }
            }

            if(!isCommon) {
                test += nums[0];
            }
        }

        return test;
    }
}
