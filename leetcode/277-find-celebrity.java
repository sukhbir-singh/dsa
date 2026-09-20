/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i=0; i<n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        // either candidate is celebrity or no one is
        return isCelebrity(candidate, n) ? candidate : -1;
    }

    private boolean isCelebrity(int c, int n) {
        for (int i=0; i<n; i++) {
            if (i==c) continue;
            if (!knows(i, c) || knows(c, i)) { // satisfy both conditions
                return false;
            }
        }
        return true;
    }
}