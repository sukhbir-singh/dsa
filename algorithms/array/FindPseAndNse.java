package algorithms.array;

import java.util.Arrays;
import java.util.Stack;

/* 
The Single-Iteration Logic
1. We maintain a stack of indices in a strictly increasing order of their corresponding values.
2. When we encounter an element that is smaller than the element at the top of the stack, 
it means the current element is the NSE for that top element.
3. We then pop the top element. Its PSE is simply the new top element remaining in the stack 
(since the stack is kept monotonic).

Dry Run Example
For arr = [2, 1, 5, 6, 2, 3]:
• At i = 1 (value 1): 1 is smaller than 2 (stack top). We pop 2. Its NSE is 1. 
  The stack becomes empty, so its PSE is -1.
• At i = 4 (value 2): We encounter 2. The stack contains indices for [1, 5, 6].
	• 2 is smaller than 6, so 6 is popped. Its NSE is 2, its PSE is 5 (the new top).
	• 2 is smaller than 5, so 5 is popped. Its NSE is 2, its PSE is 1 (the new top).
*/
public class FindPseAndNse {
    public static void findPseAndNse(int[] arr) {
        int n = arr.length;
        
        // Arrays to store the actual values of PSE and NSE
        int[] pse = new int[n];
        int[] nse = new int[n];
        
        // Initialize all values to -1 (default if no smaller element exists)
        Arrays.fill(pse, -1);
        Arrays.fill(nse, -1);
        
        // Stack to store indices of the array elements. Monotonic Stack.
        Stack<Integer> stack = new Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {
            // While stack is not empty and current element is smaller than stack's top element
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int poppedIdx = stack.pop();
                
                // 1. Current element is the Next Smaller Element for the popped index
                nse[poppedIdx] = arr[i];
                
                // 2. The new top of the stack is the Previous Smaller Element for the popped index
                if (!stack.isEmpty()) {
                    pse[poppedIdx] = arr[stack.peek()];
                }
            }
            // Push the current index onto the stack
            stack.push(i);
        }
        
        // Empty the remaining indices in the stack after the loop ends
        while (!stack.isEmpty()) {
            int poppedIdx = stack.pop();
            // nse[poppedIdx] remains -1 as initialized
            if (!stack.isEmpty()) {
                pse[poppedIdx] = arr[stack.peek()];
            }
        }

        // Print results
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("PSE:   " + Arrays.toString(pse));
        System.out.println("NSE:   " + Arrays.toString(nse));
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        findPseAndNse(arr);
    }
}
