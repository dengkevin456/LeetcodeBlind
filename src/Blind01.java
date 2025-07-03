import java.util.*;
public class Blind01 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbbbb"));
    }

    private static int lengthOfLongestSubstring(String s) {
        int left = 0, maxLen = 0;
        Map<Character, Integer> seen = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (seen.containsKey(s.charAt(i)) && seen.get(s.charAt(i)) >= left) {
                left = seen.get(s.charAt(i)) + 1;
            }
            seen.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }
}