import java.util.*;

// In this problem, you have used 2 hashmaps and compared it everytime - this solution is okay
class Solution {
    private Map<Character, Integer> getMap(String s) {
        Map<Character, Integer> targetMap = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            int c = targetMap.getOrDefault(s.charAt(i), 0);
            targetMap.put(s.charAt(i), c+1);
        }
        return targetMap;
    }
    
    private boolean isMapSubset(Map<Character, Integer> parent, Map<Character, Integer> child) {
        for (Map.Entry<Character, Integer> entry: child.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();
            if (parent.getOrDefault(key, 0) < count) {
                return false;
            }
        }
        return true;
    }

    // this can be simplified
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        Map<Character, Integer> targetMap = getMap(t);
        int left = 0, right = 0, len = s.length();
        boolean condition = false;
        Map<Character, Integer> movingMap = new HashMap<>();
        movingMap.put(s.charAt(0), 1);
        
        String minWindow = "";
        int size = Integer.MAX_VALUE;

        while(right < len) {
            condition = isMapSubset(movingMap, targetMap);
            if (!condition) {
                // expand
                right++;
                if (right < len) {
                    movingMap.put(s.charAt(right), 1+ movingMap.getOrDefault(s.charAt(right), 0));
                }

            } else {
                if (right-left+1 < size) {
                    size = right-left+1;
                    minWindow = s.substring(left, right+1);
                }
                
                // contract
                movingMap.put(s.charAt(left), movingMap.get(s.charAt(left)) - 1);
                left++;
            }
        }
        
        return minWindow;
    }
}

// Better solution could be using single map, prefilled with t and then do sliding window and reduce it using s.
class Solution2 {
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> required = new HashMap<>();
        for (char character : t.toCharArray()) {
            required.put(character, required.getOrDefault(character, 0) + 1);
        }

        int left = 0;
        int matched = 0;
        int minStart = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char rightCharacter = s.charAt(right);
            if (required.containsKey(rightCharacter)) {
                if (required.get(rightCharacter) > 0) {
                    matched++;
                }
                required.put(rightCharacter, required.get(rightCharacter) - 1);
            }

            while (matched == t.length()) {
                int windowLength = right - left + 1;
                if (windowLength < minLength) {
                    minStart = left;
                    minLength = windowLength;
                }

                char leftCharacter = s.charAt(left++);
                if (required.containsKey(leftCharacter)) {
                    required.put(leftCharacter, required.get(leftCharacter) + 1);
                    if (required.get(leftCharacter) > 0) {
                        matched--;
                    }
                }
            }
        }

        return minLength == Integer.MAX_VALUE
                ? ""
                : s.substring(minStart, minStart + minLength);
    }
}