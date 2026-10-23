import java.util.*;
class Solution {
    // task has to be schedule based on priority of their frequencies. why? because tasks having most frequency
    // should be schduled first - by greedy approach
    // read the comments, i have added nice comments for better understanding
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: tasks) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (var f: freq.entrySet()) {
            pq.add(f.getValue());
        }

        int time = 0;
        // iterating by cycle. cycle represents one sequence of tasks processed.
        while (!pq.isEmpty()) {
            int cycle = n + 1; // important

            // for avoiding processing of same task again
            List<Integer> temp = new ArrayList<>();

            // i represents number of tasks processed in this cycle
            int i = 0;
            while (i < cycle && !pq.isEmpty()) {
                int c = pq.remove();
                if (c > 1) {
                    temp.add(c-1);
                }
                time++;
                i++;
            }

            // add temp back to pq
            for (int t : temp) {
                pq.add(t);
            }

            // adding idle time
            if (!pq.isEmpty()) {
                time += cycle - i;
            }
        }

        return time;
    }
}

// Good thought process
// other solution - filling up the empty slots
// Example: A A A B B C, cooldown n = 2
// A _ _ A _ _ A
// A B C | A B idle | A
class Solution2 {
    public int leastInterval(char[] tasks, int n) {
        // Create a frequency array to keep track of the count of each task
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Sort the frequency array in non-decreasing order
        Arrays.sort(freq);
        // Calculate the maximum frequency of any task
        int maxFreq = freq[25] - 1;
        // Calculate the number of idle slots that will be required
        int idleSlots = maxFreq * n;

        // Iterate over the frequency array from the second highest frequency to the lowest frequency
        for (int i = 24; i >= 0 && freq[i] > 0; i--) {
            // Subtract the minimum of the maximum frequency and the current frequency from the idle slots
            idleSlots -= Math.min(maxFreq, freq[i]); // take min because freq[i] cannot be used as max freq[25]
        }

        // If there are any idle slots left, add them to the total number of tasks
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}