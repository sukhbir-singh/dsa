package algorithms.array;

import java.util.Arrays;
import java.util.Stack;

public class PreviousGreaterElement {
    
    public static int[] findPreviousGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {
            
            // Pop elements from the stack while they are smaller than or equal 
            // to the current element, as they cannot be a "greater element" for future elements.
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, no greater element exists to the left
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                // The top of the stack is the nearest greater element on the left
                result[i] = stack.peek();
            }

            // Push the current element onto the stack for upcoming elements
            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        // int[] arr = {10, 4, 2, 20, 40, 12, 30};
        int[] arr = {7, 2, 1, 3, 3, 1, 8, 2};
        int[] pge = findPreviousGreater(arr);

        System.out.println("Input Array:  " + Arrays.toString(arr));
        System.out.println("PGE Array:    " + Arrays.toString(pge));
    }
}
