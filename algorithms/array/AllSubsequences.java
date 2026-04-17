package algorithms.array;

import java.util.ArrayList;
import java.util.List;

// How to generate all subsequences
public class AllSubsequences {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        generate(0, arr, new ArrayList<>());
    }

    // Method1: Recursion
    public static void generate(int index, int[] arr, List<Integer> current) {
        if (index == arr.length) {
            System.out.println(current);
            return;
        }
        // Pick the current element
        current.add(arr[index]);
        generate(index + 1, arr, current);
        
        // Backtrack: Remove and don't pick the current element
        current.remove(current.size() - 1);
        generate(index + 1, arr, current);
    }

    // Method2: Powerset
    public static void powerSet(int[] arr) {
        int n = arr.length;
        int total = (1 << n); // 2^n

        for (int i = 0; i < total; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if j-th bit is set
                if ((i & (1 << j)) != 0) {
                    sub.add(arr[j]);
                }
            }
            System.out.println(sub);
        }
    }

    // Method3: Cascading
    public static List<List<Integer>> iterativeSubsequences(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with empty subsequence

        for (int num : arr) {
            int n = result.size();
            for (int i = 0; i < n; i++) {
                List<Integer> newSub = new ArrayList<>(result.get(i));
                newSub.add(num);
                result.add(newSub);
            }
        }
        return result;
    }
}
