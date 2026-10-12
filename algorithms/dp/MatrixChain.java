package algorithms.dp;

public class MatrixChain {
    private int solve(int i, int j,int arr[]) {
        if(i >= j)return 0;

        int ans=Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++) {
            int tempAns = solve(i,k,arr) + solve(k+1,j,arr) + arr[i-1]*arr[k]*arr[j];
            ans=Math.min(ans,tempAns);                        
        }
        return ans;
    }

    public int matrixMultiplication(int N, int arr[]){
        return solve(1, N-1, arr);
    }
}
