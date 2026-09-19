class Solution {
    public void solveSudoku(char[][] board) {
        placeDigit(board, 0, 0);
    }

    private boolean placeDigit(char[][] board, int r, int c) {
        if (r == board.length) {
            return true;
        }

        if (board[r][c] != '.') { // skip to next
            int[] nextP = nextPoint(board, r, c);
            if (nextP[0] == -1) {
                return true;
            }
            return placeDigit(board, nextP[0], nextP[1]);
        }
        
        // set -> recurse -> unset
        for (int i=1; i<=9; i++) {
            if (!canPlace(board, r, c, i)) {
                continue;
            }

            board[r][c] = (char)('0' + i);

            int[] nextP = nextPoint(board, r, c);
            if (placeDigit(board, nextP[0], nextP[1])) {
                return true; // important - this is needed to be returned, so that board is not reset to .
            }
            
            board[r][c] = '.'; 
        }
        return false;
    }

    private int[] nextPoint(char[][] board, int r, int c) {
        int nc = c+1;
        if (nc < board[0].length) {
            return new int[] {r, nc};
        }
        if (r == board.length) {
            return new int[] {-1, -1};
        }
        return new int[] {r+1, 0};
    }

    private boolean canPlace(char[][] board, int r, int c, int num) {
        char ch = (char)('0' + num);

        // check all cols
        for (int i=0; i<board[0].length; i++) {
            if (board[r][i] == ch) {
                return false;
            }
        }

        // check all rows
        for (int i=0; i<board.length; i++) {
            if (board[i][c] == ch) {
                return false;
            }
        }
        
        // check entire grid - Interesting calculations
        int gridR = r/3;
        int gridC = c/3;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[3*gridR + i][3*gridC + j] == ch) {
                    return false;
                }
            }
        }
        
        return true;
    }
}