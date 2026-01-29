import java.util.*;
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int p1 = 0, p2 = 0;
        int c = 0;
        while (p1 != players.length && p2 != trainers.length) {
            if (trainers[p2] >= players[p1]) {
                p1++;
                p2++;
                c++;
            } else {
                p2++;
            }
        }

        return c;
    }
}