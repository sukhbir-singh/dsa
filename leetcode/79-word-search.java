// implemented using DFS with backtracking
class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (search(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, int r, int c, String word, int index) {
        if (index == word.length()) { // important to place it at first and needs to be called in recursion
            return true;
        }
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == '#' || board[r][c] != word.charAt(index)) {
            return false;
        }

        // mark visited
        board[r][c] = '#';

        // neighbours
        int[][] nbs = { {0,1}, {1,0}, {0,-1}, {-1,0} };

        boolean matched = false;
        for (int[] nb: nbs) {
            int nr = r + nb[0];
            int nc = c + nb[1];

            // intensionally not checking the coordinates here, so that we can mark it as true if index reaches last
            if (search(board, nr, nc, word, index+1)) {
                matched = true;
                break;
            }
        }

        board[r][c] = word.charAt(index);
        return matched;
    }
}