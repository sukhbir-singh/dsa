import java.util.*;
// very nice pattern for priority queue: reprocess elements using a temporary list.
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int len = hand.length;
        if (len % groupSize > 0) {
            return false;
        }

        // freq map
        Map<Integer, Integer> mp = new HashMap<>();
        Queue<Integer> pq = new PriorityQueue<>();
        for (int h: hand) {
            int cur = mp.getOrDefault(h, 0);
            if (cur == 0) {
                mp.put(h, 1);
                pq.add(h);
            } else {
                mp.put(h, cur + 1);
            }
        }

        // processing for each hand in each iteration
        while (!pq.isEmpty()) {
            int t = groupSize;
            List<Integer> temp = new ArrayList<>();
            int prev = pq.peek() - 1;

            // System.out.print("making batch: ");
            while (t > 0 && !pq.isEmpty()) {
                int num = pq.remove();
                // System.out.print(num + " ");
                if (prev + 1 != num) {
                    return false;
                }
                int newF = mp.get(num) - 1;
                mp.put(num, newF);

                if (newF > 0) {
                    temp.add(num);
                }
                prev = num;
                t--;
            }
            // System.out.println();

            // case when we are not able to make exactly size of group
            if (t > 0) {
                return false;
            }

            // add temp back to pq
            for (int temp1: temp) {
                pq.add(temp1);
            }
        }

        return true;
    }
}