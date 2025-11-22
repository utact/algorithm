import java.io.*;
import java.util.*;

//DP
public class Main {

	static int[] dp;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        dp = new int[n+1];
        //기본식 
        dp[1] = 1;
        if(n>=2) dp[2] = 2;
        
        //점화식 
        for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
        
        System.out.println(dp[n]);
        
    }
}