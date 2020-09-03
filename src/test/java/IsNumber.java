import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.*;

/**
 * @author tian
 * @date 2020/9/2
 */

public class IsNumber {
    @Test
    public void test1() {
        System.out.println(isPalindrome(121));
    }




    public boolean isPalindrome(int x) {
        int result = 0;
        int start = x;
        if (x < 0) return false;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result == start) return true;
        else return false;

    }

    public int reverse(int x) {
        int ret = 0;
        while (x != 0) {
            int tmp = x % 10;      //末尾数字
            if (ret > Integer.MAX_VALUE / 10 || (ret == Integer.MAX_VALUE / 10 && tmp > Integer.MAX_VALUE % 10))
                return 0;
            if (ret < Integer.MIN_VALUE / 10 || (ret == Integer.MIN_VALUE / 10 && tmp < Integer.MIN_VALUE % 10))
                return 0;
            ret = ret * 10 + tmp; //反转逻辑
            x /= 10;            //更新x
        }
        return ret;
    }


    public int lengthOfLongestSubstring(String s) {
        int num = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < s.length(); end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            num = Math.max(num, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return num;
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[2];
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

//        List<Integer> list1 = new ArrayList<>();
//        while(l1.next!=null){
//            list1.add(l1.val);
//            l1=l1.next;
//        }
//        String s1 = list1.stream()
//                .map(s -> String.valueOf(s))
//                .collect(Collectors.joining());
//        int i1 = Integer.parseInt(s1);

        StringBuffer l1Buffer = new StringBuffer();
        while (l1 != null) {
            l1Buffer.append(l1.val);
            l1 = l1.next;
        }
        int i1 = Integer.parseInt(l1Buffer.toString());

        StringBuffer l2Buffer = new StringBuffer();
        while (l2 != null) {
            l2Buffer.append(l2.val);
            l2 = l2.next;
        }
        int i2 = Integer.parseInt(l2Buffer.toString());

        int i = i1 + i2;


        String value = String.valueOf(i);
        char[] chars = value.toCharArray();
        ListNode node = null;
        for (int j = 0; j < chars.length; j++) {
            node = new ListNode(chars[j]);
            node.next = node;
        }
        return node;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
