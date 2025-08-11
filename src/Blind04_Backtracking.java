import java.util.*;

public class Blind04_Backtracking {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(subsets(new int[]{1, 2, 3}).toArray()));
    }

    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsetsBacktrack(list, temp, nums, 0);
        return list;
    }
    private static void subsetsBacktrack(List<List<Integer>> list, List<Integer> tempList,
                                         int[] nums, int start) {
        if (start >= nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(nums[start]);
        subsetsBacktrack(list, tempList, nums, start + 1);
        tempList.removeLast();
        subsetsBacktrack(list, tempList, nums, start + 1);
    }
}
