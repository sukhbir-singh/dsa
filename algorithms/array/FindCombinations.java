package algorithms.array;

import java.util.*;

// A simple way to remember it:
// Duplicate numbers can appear together in one combination, but they should not independently start identical branches at the same recursion level.

// Time:  O(n × 2^n) including printing
//       O(2^n) if combination output cost is ignored.  -> why? because eventually it is picked or not picked for each element.
// Space: O(n), excluding output
public class FindCombinations {
    
    static void findCombinations(int[] candidates, int index, int target, List<Integer> current) {
        if (target == 0) {
            System.out.println(current);
            return;
        }

        // select a element to be picked for this recursion call
        for (int i = index; i < candidates.length; i++) {
            // Skip duplicate choices at this recursion level
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            current.add(candidates[i]);

            // i + 1 because each array element can be used only once
            findCombinations(candidates, i + 1, target - candidates[i], current);

            current.removeLast();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] arr = new int[]{1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 7, 8, 9, 9, 9};
        findCombinations(arr, 0, 5, new ArrayList<>());
    }
}
