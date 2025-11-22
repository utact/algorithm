import java.io.*;

public class Main_박서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			
			for (int i = 1; i < N+1; i++) {
				if(i == 1) {
					dp[i] = 1;
					continue;
				}
				if(i == 2) {
					dp[i] = 2;
					continue;
				}
				if(i == 3) {
					dp[i] = 4;
					continue;
				}
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			System.out.println(dp[N]);
		}
	}
}