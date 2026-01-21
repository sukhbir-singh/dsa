import java.util.*;
class Solution {
    // code does not look clean
    // good thought process => numOfReplacements = len - maxFreq
    public int characterReplacement(String s, int k) {
        if (s.length() == 1) {
            return 1;
        }

        Map<Character, Integer> mp = new HashMap<>();
        int l = 0, r = 0, maxLen = 0;
        int highestFreq = 0, charForHighestFreq = ' ';

        // I will keep track of character having maximum frequency
        while (r < s.length()) {
            char ch = s.charAt(r);
            int newFreq = mp.getOrDefault(ch, 0) + 1;
            mp.put(ch, newFreq);
            if (newFreq > highestFreq) {
                highestFreq = newFreq;
                charForHighestFreq = ch;
            }

            int replacementRequired = r-l+1 - highestFreq;
            // if not valid substring, move l to make it valid
            while (replacementRequired > k) {
                char removedChar = s.charAt(l);
                l++;
                mp.put(removedChar, mp.getOrDefault(removedChar, 0) - 1);

                if (removedChar == charForHighestFreq) {
                    highestFreq--;
                    // recalculate highest frequencies
                    for (var entry : mp.entrySet()) {
                        if (entry.getValue() > highestFreq) {
                            highestFreq = entry.getValue();
                            charForHighestFreq = entry.getKey();
                        }
                    }
                }
                
                replacementRequired = r-l+1 - highestFreq;
            }

            if (replacementRequired <= k) {
                // valid substring so calculate max len
                maxLen = Math.max(r-l+1, maxLen);
            }

            r++; // remember to put this - i keep on forgetting this
        }

        return maxLen;
    }
}