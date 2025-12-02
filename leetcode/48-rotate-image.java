class Solution {
    // rotate = tranpose + reverse each row
    public void rotate(int[][] matrix) {
        // transpose
        for (int i = 0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                if (i>=j) {
                    continue;
                }
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reflect
        int len = matrix.length;
        for (int i = 0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len-1-j];
                matrix[i][len-1-j] = temp;
            }
        } 
    }
}