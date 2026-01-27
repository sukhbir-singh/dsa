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