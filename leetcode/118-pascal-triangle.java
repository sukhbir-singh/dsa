import java.util.*;

class Solution {
    private List<Integer> generateRow(int row) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        for (int i=1; i<row; i++) {
            int ans = list.get(i-1) * (row-i);
            ans = ans / i;
            list.add(ans);
        }

        return list;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0; i<numRows; i++) {
            list.add(generateRow(i+1));
        }
        return list;
    }
}