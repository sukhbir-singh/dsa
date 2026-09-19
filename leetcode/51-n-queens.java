import java.util.*;

// I realize that if i kept char array in the starting, that would have simplified the solution. As i just have to set particular char cell
// for diagnols, you can also keep 2 arrays in recusion arguments and keep adding elements in them and later check if element is present in diagnol or not
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        List<String> maze = new ArrayList<>();
        String row = "";
        for (int i=0; i<n; i++) {
            row += ".";
        }
        for (int i=0; i<n ;i++) {
            maze.add(row);
        }

        placeQueen(maze, 0, n, res);
        return res;
    }

    private void placeQueen(List<String> maze, int row, int total, List<List<String>> res) {
        if (row == total) {
            List<String> cloned = new ArrayList<>();
            for (String s: maze) {
                cloned.add(s);
            }
            res.add(cloned);
            return;
        }

        // try all possibilities for column
        // set -> recuse -> unset
        for (int i=0; i<total; i++) {
            // check if we can place queeen in this column or not
            if (!canPlace(maze, row, i, total)) {
                continue;
            }

            String updatedRow = setChar(maze.get(row), i, 'Q');
            maze.set(row, updatedRow);

            placeQueen(maze, row+1, total, res);
            
            updatedRow = setChar(maze.get(row), i, '.');
            maze.set(row, updatedRow);
        }
    }

    private String setChar(String s, int p, char ch) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(p, ch);
        return sb.toString();
    }

    // core method to check conflicts between queens
    private boolean canPlace(List<String> maze, int row, int col, int total) {
        // check all rows
        for (int i=0; i<total; i++) {
            if (i != row && maze.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        // check all cols
        for (int i=0; i<total; i++) {
            if (i != col && maze.get(row).charAt(i) == 'Q') {
                return false;
            }
        }

        // check diagnols - INTERESTING
        // to simplify this, you can also just check upper left diagnol by just reducing row--, col-- until row>=0 and col >=0, because we are filling in this direction only
        int[][] nbs = { {1,1}, {1,-1}, {-1,1}, {-1,-1} }; // this sets direction. now to get actual coordinate, multiply this with i
        for (int i=1; i<total; i++) {
            for (int[] nb: nbs) {
                int nr = row + nb[0]*i;
                int nc = col + nb[1]*i;

                // if valid and queen present then return false
                if (nr >= 0 && nr < total && nc >= 0 && nc < total && maze.get(nr).charAt(nc) == 'Q') {
                    return false;
                }
            }
        }

        return true;
    }
}